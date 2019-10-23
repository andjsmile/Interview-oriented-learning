/**
 * 实现一个抽象类
 */

public abstract class Ainmal{

    protected int legs;

    protected Ainmal(int legs){
        this.legs=legs;
    }
    public void walk(){
        System.out.println("how to walk with "+legs+"legs");
    }

    public abstract void eat(){

    }
}

public class Spider extends Ainmal{
    public Spider(){
        super(8);
    }

    public eat(){

    }
}

public interface Pet{

    public String getName();

    public void SetName(String name);

    public void play();
}


public class Cat implements Pet extends Ainmal{

    private String name;

    public Cat(String name){
        super(4);
        this.name=name;

    }
    public Cat(){
        this("");

    }

    public String getName(){
        return name;
    }

    public void SetName(String name){
        this.name=name;
    }

    public void play(){

    }

    public void eat(){

    }
}


public class Fish implements Pet extends Ainmal{

    private String name;

    public Fish(){
        super(0);

    }
    @Override
    public void walk(){
        System.out.println("the fish no leg to walk");
    }

    public String getName(){
        return name;
    }
    public void SetName(String name){
        this.name=name;
    }
}