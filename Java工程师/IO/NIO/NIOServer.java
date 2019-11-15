import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 套接字NIO实例
 */

public class NIOServer{
    public static void main(String[] args) throws IOException{

        //创建选择器
        Selector selector=Selector.open();

        // 将通道注册到选择器上
        ServerSocketChannel sschannel=ServerSocketChannel.open();
        sschannel.configureBlocking(false);
        // 注册，还需要指定具体事件
        sschannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket serverSocket=sschannel.socket();
        InetSocketAddress address=new InetSocketAddress("127.0.0.1",8888);
        serverSocket.bind(address);

        while(true){
            // 监听事件
            selector.select();
            Set<SelectionKey> keys=selector.selectedKeys();
            Iterator<SelectionKey> kIterator=keys.iterator();

            while(kIterator.hasNext()){
                SelectionKey key=kIterator.next();
                if(key.isAcceptable()){
                    ServerSocketChannel ssChannel1=(ServerSocketChannel)key.channel();
                    // 新连接创建一个socketChannel
                    SocketChannel sChannel=ssChannel1.accept();
                    sChannel.configureBlocking(false);
                    // 这个新连接主要用于从客户端读取数据
                    sChannel.register(selector, SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    SocketChannel sChannel=(SocketChannel)key.channel();
                    System.out.println(readDataFromSocketChannel(sChannel));
                    sChannel.close();
                }
                kIterator.remove();
            }
        }

    }
    private static String readDataFromSocketChannel(SocketChannel sChannel)throws IOException{

        ByteBuffer buffer=ByteBuffer.allocate(1024);
        StringBuilder data=new StringBuilder();
        while(ture){
            buffer.clear();
            int n=sChannel.read(buffer);
            if(n==-1){
                break;
            }
            buffer.flip();
            int limit=buffer.limit();

            char[] dst=new char[limit];
            for(int i=0;i<limit;++i){
                dst[i]=(char)buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }
     
}