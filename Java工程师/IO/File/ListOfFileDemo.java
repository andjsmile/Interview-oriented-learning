import java.io.File;

/**
 * 递归的列出一个目录下的所有文件
 */
public class ListOfFileDemo{

    public static void ListOfAllFile(File dir){
        if(dir==null || !dir.exists()) return;

        if(dir.isFile()){
            System.out.println(dir.getName());
            return;
        }
        for(File file:dir.listFiles()){
            ListOfAllFile(file);
        }
    }

    public static void main(String[] args){
        String pathname="/Users/dengshuodengshuo/Code/Interview/Interview-oriented learning/Java工程师/JVM";
        File path=new File(pathname);
        ListOfAllFile(path);
    }
}