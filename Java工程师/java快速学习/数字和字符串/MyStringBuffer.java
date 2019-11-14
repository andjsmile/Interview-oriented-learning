
/**
 *  练习接口MyStringBuffer
 */

public interface IStrinBuffer{
    public void append(String str);
    public void append(char c);

    public void insert(int pos,char b);
    public void insert(int pos,String b);

    public void delete(int start);
    public void delete(int start,int end);

    public void reverse();
    public void length();

}

public class MyStringBuffer implements IStrinBuffer{

    int capacity=16;
    int length=0;
    char[] value;

    public MyStringBuffer(){
        value=new char[capacity];
    }

    public MyStringBuffer(String str){
        this();
        if(null==str){
            return ;
        }
        if(capacity<str.length()){
            capacity=value.length*2;
            value=new char[capacity];
        }
        if(capacity>=str.length()){
            System.arraycopy(str.toCharArray(), 0, value, 0, str.length());
            length=str.length();
        }

    }

    public String toString(){
        char[] realValue=new char[length];

        System.arraycopy(value, 0, realValue, 0, length);

        return new String(realValue);
    }

    public void reverse(){
        for(int i=0;i<length/2;++i){
            char temp=value[i];
            value[i]=value[length-i-1];
            value[length-i-1]=temp;
        }
    }

    public void append(String str){
        insert(length,str);

    }
    public void append(char c){
        append(String.valueof(c));
    }

    public void insert(int pos,char b){
        insert(pos,String.valueOf(b));
    }

    public void delete(int start){

    }

    public void delete(int start,int end){
        if(start<0) return;
        if(start>length) return;
        if(end<0) return;
        if(end>length) return;
        if(start>=end) return;
        // 实现覆盖赋值
        System.arraycopy(value, end, value, start,length-end);
    }

    public void insert(int pos,String b){
        // 边界判定
        if(pos<0)
            return;
        if(pos>length)
            return;
        if(b==null)
            return;

        // 字符数组扩容
        
        while((length+b.length())>capacity){
            capacity=(int)((length+b.length())*1.5f);

            char[] newvalue=new char[capacity];
            System.arraycopy(value, 0, newvalue, 0, length);

            value=newvalue;

            char[] cs=b.toCharArray();
            // 将插入未知的数组向后移动
            System.arraycopy(value,pos,value,pos+length,length-pos);
            // 将数组插入到插入位置
            System.arraycopy(cs, 0, value, pos, cs.length);

            length=length+cs.length;
        }
    }

    public int length(){
        return length;
    }
}