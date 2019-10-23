import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * 
 */

public class particeTest{

    public static void main(String[]args){
        String dirname="/java";
        File f=new File(dirname);
        //Returns an array of strings naming the files and directories 
        FilenameFilter fft=new onlyExt("html");

        String s[]=f.list(fft);

        for(int i=0;i<s.length;++i){
            System.out.println(s[i]);
        }

    }



    class onlyExt implements FilenameFilter{
        String ext;

        public onlyExt(String ext){
            this.ext="."+ext;
        }
        public boolean accept(File dir,String name){

            return name.endsWith(ext);
        }
    }

    // 实现文件的复制

    public static void copyFile(String src,String dest) throws IOException{
        FileInputStream fis=new FileInputStream(src);
        FileOutputStream fos=new FileOutputStream(dest);

        byte[] buffer=new byte[20*1024];
        int len;
        int cnt=0;
        while((cnt=fis.read(buffer,0,buffer.length)!=-1){
            fos.write(buffer,0,cnt);
        }
        fis.close();
        fos.close();
    }
}