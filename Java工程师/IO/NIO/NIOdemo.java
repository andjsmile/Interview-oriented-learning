import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO实现快速复制文件的实例
 */

public class NIOdemo{

    public static void fastCopy(String stc,String dest) throws IOException{
        
        // 源文件的输入字节流
        FileInputStream fis=new FileInputStream(src);

        // 输入字节流的文件通道
        FileChannel fc=fis.getChannel();

        // 获取目标文件的输出字节流
        FileOutputStream fos=new FileOutputStream(dest);

        // 输出字节流的文件通道
        FileChannel fout=fos.getChannel();

        // 缓冲区
        ByteBuffer buffer=ByteBuffer.allocateDirect(1024);

        while(true){
            // 输入通道读取数据
            int r=fc.read(buffer);
            
            // 读取结束
            if(r==-1){
                break;
            }

            // 切换读写
            buffer.flip();

            // 把缓冲区写入输出文件
            fout.write(buffer);

            // 清空缓冲区
            buffer.clear();
        }

    }

    public static void main(String[] args){

        File file=new File("/Users/dengshuodengshuo/Code/Interview/Interview-oriented learning/Java工程师/JUC/Atomic/ReorderingDemo.java");
        File out=new File("/Users/dengshuodengshuo/Code/Interview/Interview-oriented learning/Java工程师/IO/NIO/test.java");
        fastCopy(file, out);

    }
}