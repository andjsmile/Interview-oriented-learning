/**
 * 装饰器模式 
 * DecoratorPattern
 * 目的:给对象拓展功能，但是不改变对象结构
 * 优点:装饰器模式比实现子类更加灵活，子类在实现过程中
 *     会不断增加静态特征，造成子类的膨胀
 *     
 *     装饰类和被装饰类都可以独立发展，不会相互耦合
 */

// 为了代码方便，将所有代码放到一个类中

public interface Shape{
    public void draw(){

    }
}

public class Circle implements Shape{
    @Override
    public void draw(){
        System.out.println("shape::circle shape");
    }
}

public class Rectangle implements Shape{
    @Override
    public void draw(){
        System.out.println("shape::rectangle shape");
    }
}


public abstract class ShapeDecorator implements Shape{
    public Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape=decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}

public class RedShapeDecorator extends ShapeDecorator {
 
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);     
    }
 
    @Override
    public void draw() {
        decoratedShape.draw();         
        setRedBorder(decoratedShape);
    }
 
    private void setRedBorder(Shape decoratedShape){
       System.out.println("Border Color: Red");
    }
}


