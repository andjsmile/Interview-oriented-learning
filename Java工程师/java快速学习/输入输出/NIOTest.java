import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO
 * 
 */
public class NIOTest{

    public static void main(String []args) throws IOException{
        // 1.创建选择器
        Selector selector=Selector.open();
        // 2.将通道注册到选择器上
        ServerSocketChannel sschannel=ServerSocketChannel.open();
        //   通道为非阻塞模式
        sschannel.configureBlocking(false);
        // SelectionKey.OP_CONNECT  SelectionKey.OP_ACCEPT  
        // SelectionKey.OP_READ SelectionKey.OP_WRITE                        
        sschannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket serverSocket=sschannel.socket();
        InetSocketAddress address=new InetSocketAddress("127.0.0.1", 8888);
        serverSocket.bind(address);

        while(true){
            // 监听事件
            selector.select();
            Set<SelectionKey> setkey=selector.selectedKeys();
            Iterator<SelectionKey> keyIterator=setkey.iterator();

            while(keyIterator.hasNext()){
                SelectionKey key=keyIterator.next();
                if(key.isAcceptable()){
                    ServerSocketChannel sschannel1=(ServerSocketChannel)key.channel();
                    // 服务器会为每一个新连接创建一个 SocketChannel
                    SocketChannel schannel=sschannel1.accept();
                    schannel.configureBlocking(false);

                    // 建立链接从客户端读取数据
                    schannel.register(selector, SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    SocketChannel sChannel =(SocketChannel)key.channel();
                    System.out.println(readDataFromSocketChannel(sChannel));
                    sChannel.close();
                }
                keyIterator.remove();
            }
        }

    }
    // 缓冲区状态
    private static void readDataFromSocketChannel(SocketChannel sChannel) throws IOException{
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        StringBuilder data=new StringBuilder();

        while(true){
            buffer.clear();
            int n=sChannel.read(buffer);

            if(n==-1){
                break;
            }
            buffer.flip();
            int limit=buffer.limit();
            char[] dst=new char[limit];
            for(int i=0;i<limit;++i){
                dst[i]=(char)uffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }
}


/***
 * 下面是一个客户端申请文件
 */