import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/***
 * 序列化操作
 */

public class SerializableDemo{

    private static class ObjSerial implements Serializable{
        private int x;
        private String str;

        ObjSerial(int x,String y){
            this.x=x;
            this.str=str;
        }
        @Override
        public String toString(){
            return "x= "+x+","+"y= "+str;
        }
    }

    public static void main(String[] args)throws IOException,ClassNotFoundException{
        ObjSerial os1=new ObjSerial(123,"abc");
        String objectFile="/Users/dengshuodengshuo/Code/Interview/Interview-oriented learning/Java工程师/IO/Object/a1";

        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(objectFile));
        objectOutputStream.writeObject(os1);
        objectOutputStream.close();


        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(objectFile));
        ObjSerial os2=(ObjSerial)objectInputStream.readObject();

        objectInputStream.close();
        System.out.println(os2);

    }
}