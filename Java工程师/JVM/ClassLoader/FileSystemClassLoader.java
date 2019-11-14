import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件系统的自定义类加载器
 */

public class FileSystemClassLoader extends ClassLoader{

    private String rootDir;

    public FileSystemClassLoader(String name){
        this.rootDir=name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        byte[] classData=getClassData(name);

        if(classData ==null){
            throw new ClassNotFoundException();
        }else{
            return defineClass(name, classData, 0,classData.length);
        }

    }

    private byte[] getClassData(String ClassName){
        String path=ClassNameToPath(ClassName);

        try{
            InputStream is=new FileInputStream(path);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();

            int bufferSize=4096;
            byte[] buffer=new byte[bufferSize];

            int byteNumRead;
            while((byteNumRead=is.read(buffer))!=-1){
                baos.write(buffer, 0, byteNumRead);
            }
            return baos.toByteArray();

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String ClassNameToPath(String className){
        return rootDir+File.separatorChar+className.replace('.',File.separatorChar)+".class";
    }

}