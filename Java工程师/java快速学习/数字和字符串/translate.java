public class translate{


    // 将数字转换为字符串
    int i=5;

    Integer int_i=i;  // 装箱
    String str=int_i.toString();

    // 或者

    String str2=String.valueOf(i);


    // 字符串转换为数字

    String s="999";

    int res=Integer.parseInt(s);

    Integer re=Integer.valueOf(s);

    int res2=re;


    double pi=3.14;
    String str_pi=String.valueOf(pi);


    double res3=Double.parseDouble(str_pi);
}