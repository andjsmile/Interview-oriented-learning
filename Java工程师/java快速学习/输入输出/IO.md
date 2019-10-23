
## java I/O


java的输入输出系统

**所有的数据都是通过流在各个设备上转运传输**


### 磁盘文件 File

主要处理文件及文件系统(目录)这个**比较特殊,不属于流式操作**


- 文件信息

isFile(),listFiles(),getName(),exists()

getPath(),getAbsolutePath(),getParent()


- 目录信息

isDirectory(),list()


**流式输入/输出建立在四个抽象类的基础上**

- 1.InputStream    字节流
- 2.OutputStream
- 3.Reader         字符流
- 4.Writer

处理字节或二进制对象使用 字节流

处理字符、字符串对象使用 字符流

### 字节流

InputStream,OutputStream

字节流的类使用装饰器来实现(decorator pattern)

- 1.FileInputStream(文件输入流)

```java
FileInputStream f1=new FileInputStream("/Usrs/local.bat");

// 这个实现更好，进一步的文件检查，公开读取
File f=new File("/Usrs/local.bat");
FileInputStream fis=new FileInputStream(f);

```


- 2.ByteArrayInputStream(字节数组输入流)

实现mark()和reset()方法

reset()在流的开始处设置指针


可以通过读取byte数组进行输入  f.write()方法

可以对 文件输入流、输入流进行输入   f.writeTo()方法

writeTo​(OutputStream out) 


- 3.FilterInputStream,FilterOutputStream(过滤字节流)

过滤字节流,是一个抽象的装饰者

装饰着装饰组件，为组件提供额外的功能


- 4.BufferedInputStream  (缓冲字节流)

提供缓冲的功能

缓冲长度默认是 8*1024 8kb

支持mark()和reset()方法


- 4.PrintStream  打印流

- 5.RandomAccessFile (随机访问文件类)



### 字符流

字节流只能实现处理ASCII码，不能对unicode直接操作

**编码、解码**

编码:将字符转换成字节

解码:将字节转换成字符

charset字符集
编码方式: 

GBK8,UTF-8,UTF-16be
GB231,UNICODE(uff-8,utf-16)

乱码:编码与解码使用不同的方式

java默认使用UTF-16be (big endian)大端法

**双字节编码**:中英文字符都是使用两个字节，使用char存储




- 1.Reader(流式字符输入模式的抽象类)


**String，byte[], char[]**

之间的转换
```java
String res="it's a transfer way"；

byte[] buffer_b=res.getBytes();

char[] buffer_c=new char[res.length()];
res.getChars(0,res.length(),buffer_c,0);

// 转化成String
// 直接使用new String() 输入即可

```

- FileInputStream 字节流读取中文

```java
public class TestStream {
   
    public static void main(String[] args) {
        File f = new File("E:\\project\\j2se\\src\\test.txt");
        try (FileInputStream fis = new FileInputStream(f);) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
   
            //文件中读出来的数据是
            System.out.println("文件中读出来的数据是：");
            for (byte b : all)
            {
                int i = b&0x000000ff;  //只取16进制的后两位
                System.out.println(Integer.toHexString(i));
            }
            System.out.println("把这个数字，放在GBK的棋盘上去：");
            String str = new String(all,"GBK");
            System.out.println(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   
    }
}
```


- 2.FileReader 字符流读取中文

读取得到字符，一定是把字节根据某种编码识别成了字符

FileReader使用编码方式是  Charset.defaultCharset()

需要设置编码，使用InputStreamReader来实现

```java

public class TestStream {
 
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        File f = new File("E:\\project\\j2se\\src\\test.txt");
        System.out.println("默认编码方式:"+Charset.defaultCharset());
        //FileReader得到的是字符，所以一定是已经把字节根据某种编码识别成了字符了
        //而FileReader使用的编码方式是Charset.defaultCharset()的返回值，如果是中文的操作系统，就是GBK
        try (FileReader fr = new FileReader(f)) {
            char[] cs = new char[(int) f.length()];
            fr.read(cs);
            System.out.printf("FileReader会使用默认的编码方式%s,识别出来的字符是：%n",Charset.defaultCharset());
            System.out.println(new String(cs));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //FileReader是不能手动设置编码方式的，为了使用其他的编码方式，只能使用InputStreamReader来代替
        //并且使用new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8")); 这样的形式
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8"))) {
            char[] cs = new char[(int) f.length()];
            isr.read(cs);
            System.out.printf("InputStreamReader 指定编码方式UTF-8,识别出来的字符是：%n");
            System.out.println(new String(cs));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
}
```


### 缓存流(Buffered***)

字节流和字符流的弊端，在读写时候访问硬盘，如果读写频率高,则性能不佳

缓存流:

缓存流必须建立在一个存在流的基础上

缓存流在读取的时候，会一次性读较多的数据到缓存中，每一次读取都是在缓存中访问，直到缓存的数据读取完毕

- BufferedReader

缓存流在写入的时候，先把数据写入缓存区，直到缓存区达到一定值，才把数据写入硬盘中，减少I/O操作


- PrintWriter

flush()方法，强制把缓存中的数据写入硬盘，不管缓存是否已满


### 数据流

- DataInputStream
- DataOutputStram

### 对象流(序列化)

对象流:可以直接将一个对象以流的形式传输给其他的介质

序列化:一个对象以流的形式进行传输，叫做序列化
      对象所对应的类，必须是实现Serializable接口


**只有实现serializable接口的对象，可以被序列化工具存储和恢复**


- ObjectOutput接口 继承DataOutput 并支持序列化

主要函数是 writeObject() 序列化一个对象


- ObjectOutputStream类

继承OutputStream 类和实现ObjectOutput 接口

负责向流写入对象



**ObjectOutputStream.writeObj()  序列化**

**ObjectInputStream.readObj()    反序列化**



### 其他

System.in 可以从控制台输入数据
System.out 常用的控制台输出数据


### NIO


NIO 提供高速，面向块的I/O

**I/O与NIO**
数据打包和传输方式的不同

- I/0 是流的方式处理数据 (stream)
- NIO 是块的方式处理数据 (channel)

**Channel**

通道来模拟原来的流

原来的流只能实现单向移动，通道是双向的(读，写，读写同时进行)

- FileChannel          文件读写数据
- DatagramChannel      通过 UDP 读写网络中数据
- SocketChannel        通过 TCP 读写网络中数据
- ServerSocketChannel  可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel。


**缓冲区**
通道读取数据也是通过缓冲区来实现


NIO 叫非阻塞I/O,实现I/O多路复用的reactor模型


常用于 客户端于服务器之间的链接传输数据






