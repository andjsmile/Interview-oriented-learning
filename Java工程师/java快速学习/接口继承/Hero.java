

public class Hero {
        
    String name; //姓名
        
    float hp; //血量
        
    float armor; //护甲
        
    int moveSpeed; //移动速度

    public void kill(Mortal m){
        m.die();
    }
     
    public Hero(){
         
    }
 
    public static void main(String[] args) {
        Hero gareen=new Hero();
        gareen.name="盖伦";

        ADHero adhero=new ADHero();
        APHero aphero=new APHero();
        ADAPHero adaphero=new ADAPHero();

        gareen.kill(adhero);
        gareen.kill(aphero);
        gareen.kill(adaphero);
    }
      
}