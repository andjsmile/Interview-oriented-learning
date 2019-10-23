/**
 * 类的继承将重载
 */

public class Support extends Hero{
    public void heal(){
        System.out.print("no hero need treat");
    }

    public void heal(Hero h){
        
    }
}