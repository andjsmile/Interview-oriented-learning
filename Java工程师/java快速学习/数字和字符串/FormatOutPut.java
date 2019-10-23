import java.util.Scanner;

/**
 * Scanner 读取数据输出
 */


public class FormatOutPut{
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);

        String localplace=scan.nextLine();
        String typeCompany=scan.nextLine();
        String nameCompany=scan.nextLine();
        String boss=scan.nextLine();
        int money=scan.nextInt();
        // 读取数字之后，将换行符号读取
        String rn=scan.nextLine();
        String product=scan.nextLine();
        String unit=scan.nextLine();

        String sentenceFormat="%s最大%s %s倒闭了，王八蛋老板%s 吃喝嫖赌，欠下%d个亿，带着他的小姨子跑了!我们没有办法，拿着%s 抵工资!原价都是一%s多、两%s多、三%s多的钱包，现在全部只卖二十块，统统只要二十块!%s王八蛋，你不是人!我们辛辛苦苦给你干了大半年，你不发工资，你还我血汗钱，还我血汗钱!";

        System.out.printf(sentenceFormat,localplace,typeCompany,nameCompany,boss,money,product,unit,unit,unit,boss);
    }
}