import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.MenuElement;

/**
 * 可中断的套接字
 * 程序演示如何去中断一个socket channel
 */

public class InterruptibleSocketTest{
    
    // 主方法
    public static void main(String[] args){
        EventQueue.invokeLater(()->{
            // 显示框架
            JFrame frame=new InterruptibleFrame();
            // 设置标题和实现关闭自动退出
            frame.setTitle("interruptibleSocketTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class InterruptibleFrame extends JFrame{
    private Scanner in;
    // 中断按钮和阻塞按钮
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private JTextArea messages;
    private TestServer server;

    private Thread connectThread;

    public InterruptibleFrame(){
        JPanel northPanel=new JPanel();
        add(northPanel,BorderLayout.NORTH);

        final int textROWS=20;
        final int texTCOLUMNS=60;

        messages=new JTextArea(textROWS,texTCOLUMNS);
        add(new JScrollPane(messages));

        interruptibleButton=new JButton("interruptible");
        blockingButton=new JButton("blocking");

        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);
        // 注册监听事件
        interruptibleButton.addActionListener(event->{
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread=new Thread(()->{
                try{
                    connectInterruptibly();
                }catch(IOException e){
                    messages.append("\n interruptibleSocketTest.connectInterruptibly:"+e);
                }
            });
            connectThread.start();
        });

        blockingButton.addActionListener(event->{
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread=new Thread(()->{
                try{
                    connectBlocking();
                }catch(IOException e){
                    messages.append("\n interruptibleSocketTest.connectBlocking:"+e);
                }
            });
            connectThread.start();
        });

        cancelButton=new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);
        cancelButton.addActionListener(event->{
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });

        server=new TestServer();
        new Thread(server).start();
        pack();
    }

    /**
     * Connect to the testServer,using interruptible I/O
     * @throws IOException
     */
    public void connectInterruptibly() throws IOException{
        messages.append("interruptible:\n");
        try(SocketChannel channel=SocketChannel.open(new InetSocketAddress("loaclhost", 8189)))
        {
            in=new Scanner(channel,"UTF-8");
            while(!Thread.currentThread().isInterrupted()){
                messages.append("Reading ");
                if(in.hasNextLine())
                {
                    String line=in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }

        }finally{
            EventQueue.invokeLater(()->{
                messages.append("channel closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    public void connectBlocking() throws IOException{
        messages.append("Blocking:\n");
        try(Socket socket=new Socket("localhost",8189))
        {
            in=new Scanner(socket.getInputStream(),"UTF-8");
            while(!Thread.currentThread().isInterrupted()){
                messages.append("reading ");
                if(in.hasNextLine()){
                    String line=in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }
        finally{
            EventQueue.invokeLater(()->{
                messages.append("socket closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    class TestServer implements Runnable{
        @Override
        public void run(){
            try(ServerSocket s=new ServerSocket(8189))
            {
                while(true){
                    Socket incoming=s.accept();
                    Runnable r=new TestServerHandle(incoming);
                    Thread t=new Thread(r);
                    t.start();
                }
            }catch(IOException e){
                messages.append("\n testServer.run:"+e);
            }
        }
    }

    class TestServerHandle implements Runnable{
        private Socket incoming;
        private int counter;

        public TestServerHandle(Socket i){
            incoming=i;
        }
        @Override
        public void run(){
            try{
                try{
                    OutputStream oStream=incoming.getOutputStream();
                    PrintWriter out=new PrintWriter(new OutputStreamWriter(oStream,"UTF-8"),true);
                    while(counter<10){
                        counter++;
                        if(counter<=10){
                            out.println(counter);
                        }
                        Thread.sleep(100);
                    }
                }catch(InterruptedException ee){
                    ee.printStackTrace();
                }
                finally{
                    incoming.close();
                    messages.append("closing server \n");
                }
            }catch(IOException e){
                messages.append("\n TestServerHandle.run"+e);
            }
        }
    }
}

