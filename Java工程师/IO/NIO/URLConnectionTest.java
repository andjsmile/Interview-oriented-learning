import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * web上实现URL连接
 */
public class  URLConnectionTest{

    public static void main(String[] args)
    {
        try
        {
            String urlName;
            if(args.length>0) urlName=args[0];
            else urlName="http://horstmann.com";

            // 新建URL
            URL url=new URL(urlName);
            URLConnection connection=url.openConnection();

            // set username and password
            if(args.length>2)
            {
                String userName=args[1];
                String password=args[2];
                String input=userName+":"+password;
                // 实现最基础的加密
                Base64.Encoder encoder=Base64.getEncoder();
                String encoding=encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
                connection.setRequestProperty("Authorization", "Basic"+encoding);
            }
            connection.connect();

            // print header filed
            Map<String ,List<String>> headers=connection.getHeaderFields();
            for(Map.Entry<String,List<String>> entry:headers.entrySet())
            {
                String key=entry.getKey();
                for(String value:entry.getValue())
                {
                    System.out.println(key+":"+value);
                }
            }

            // print convenience functions
            System.out.println("-----------");
            System.out.println("getContentType:"+connection.getContentType());
            System.out.println("getContentLength:"+connection.getContentLength());
            System.out.println("getContentEncoding:"+connection.getContentEncoding());
            System.out.println("getData:"+connection.getDate());
            System.out.println("getExpiration:"+connection.getExpiration());
            System.out.println("getLastModifed:"+connection.getLastModified());
            System.out.println("-----------");

            String encoding=connection.getContentEncoding();
            if(encoding==null) encoding="UTF-8";
            try(Scanner in=new Scanner(connection.getInputStream(), encoding))
            {
                // print the first ten lines of contents
                for(int n=1;in.hasNextLine() && n<=10;n++){
                    System.out.println(in.nextLine());
                }
                if(in.hasNextLine()) System.out.println("...");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}