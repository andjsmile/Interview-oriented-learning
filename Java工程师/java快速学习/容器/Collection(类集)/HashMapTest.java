import java.util.ArrayList;
import java.util.HashMap;

/**
 * HashMap
 */

public class HashMapTest{
    public static void main(String[] args){

        HashMap<String,String> dict=new HashMap<>();
        ArrayList<String> alist=new ArrayList<>();

        for(int i=0;i<300000;++i){
            String str=new String("name:"+(int)((Math.random()*9+1)*1000));
            alist.add(str);
        }
        long start=System.currentTimeMillis();
        ArrayList<String> res=new ArrayList<>();
        for(String cur:alist){
            if(cur.equals("1234")){
                res.add(cur);
            }
        }
        long end=System.currentTimeMillis();

        System.out.println(" use for ,find"+res.size()+"result of 1234"+",totally time:"+(end-start));
        
}