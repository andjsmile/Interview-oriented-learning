import java.util.LinkedList;
import java.util.Stack;

/**
 * 
 */

public class MyStack implements Stack{
    LinkedList<Hero> stack=new LinkedList<>();

    public void push(Hero h){
        stack.addLast(h);
    }
    public Hero peek(){
        if(stack.size()!=0){
            return stack.getLast();
        }
    }
    public Hero poll(){
        if(stack.size()!=0){
            return stack.removeLast();
        }
    }
}