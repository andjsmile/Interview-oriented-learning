import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件简单加密算法
 */
public class FileEncodeTest{

    public static void encodeFile(File encodeFile,File resFile) throws IOException{
        FileReader fread=new FileReader(encodeFile);
        FileWriter fwrite=new FileWriter(resFile);
        char[] buff=new char[encodeFile.length()];

        char temp;
        int i=0;
        while((temp=fread.read())!=-1){
            if(Character.isDigit(temp)){
                if(temp-'0'==9){
                    temp='0';
                    buff[i++]=temp;
                }else{
                    temp=temp+1;
                    buff[i++]=temp;
                }
            }else if(Character.isLetter(temp)){
                buff[i++]=temp++;
            }else{
                buff[i++]=temp;
            }
        }

        fwrite(buff);
        fread.close();
        fwrite.close();


    }

    public static void main(String[] args){
        try{
            File encodeFile=new File(a.txt);
            File resFile=new File(b.txt);


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}