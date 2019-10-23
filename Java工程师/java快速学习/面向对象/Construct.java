/**
 * 构造方法的this
 * this可以实现在一个构造函数中调用另一个构造函数
 */

public class Construct {
    public String name;
    public int moveSpeed;
    public float hp;
    public float armor;

    public static void Hero(String name,float hp){
        this.name=name;
        this.hp=hp;
    }
    public static void Hero(String name,float hp,float armor,int moveSpeed){
        this(name,fp);
        this.armor=armor;
        this.moveSpeed=moveSpeed;
    }
}