

public class Hero {
        
    String name; //姓名
        
    float hp; //血量
        
    float armor; //护甲
        
    int moveSpeed; //移动速度

    static String copyright;
     
    public Hero(){
         
    }
     
    //回血
    public void huixue(int xp){
        hp = hp + xp;
        //回血完毕后，血瓶=0
        xp=0;
    }
      
    public Hero(String name,float hp){
        this.name = name;
        this.hp = hp;
    }

    // 参数传递
    public void revive(Hero h){
        h=new Hero("提莫",383);
        // 正确的情况是，在h指向teemo的时候直接更改数值
        h.hp=383;  // 实现复活
    }
 
    public static void main(String[] args) {
        //Hero teemo =  new Hero("提莫",383);
        
        //teemo.hp=teemo.hp-400;

        //teemo.revive(teemo);

        //System.out.println(teemo.hp);
        // 上面这个参数传递是错误的


        //血瓶，其值是100
        //int xueping = 100;
         
        //提莫通过这个血瓶回血
         
        //teemo.huixue(xueping);
         
        //System.out.println(xueping);
        Hero garen=new Hero();
        garen.name="garen";

        Hero.copyright="riot games";

        System.out.println("hero:"+Hero.copyright);

        garen.copyright="billzard";

        System.out.println("garen:"+garen.copyright);
        

        // static 变量是共享的，会导致新创建对象的改变
        Hero teemo=new Hero();

        teemo.name="teemo";

        System.out.println("teemo:"+teemo.copyright);



         
    }
      
}