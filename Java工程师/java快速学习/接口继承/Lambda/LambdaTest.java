/**
 * Lambda实现函数式接口
 * 允许将一个函数作为一个方法的参数，也就是可以函数作为参数进行传递
 * lambda表达式也称为闭包
 */

public class LambdaTest{
    public static void main(String[] args){
         
        LambdaTest tester=new LambdaTest();
        MathOperation addOperation=(int a,int b)->a+b;
        MathOperation subOperation=(a,b)-> a-b;
        MathOperation multiplyOperation=(int a,int b)->{return a*b;}
        MathOperation divisionOperation=(int a,int b)->a/b;

        System.out.println("11+2="+tester.operation(a,b,addOperation));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subOperation));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplyOperation));
         System.out.println("10 / 5 = " + tester.operate(10, 5, divisionOperation));
        
        // 不用括号
         GreetingService greetService1 = message ->
        System.out.println("Hello " + message);
        
        // 用括号
        GreetingService greetService2 = (message) ->
        System.out.println("Hello " + message);
        
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

    }
    interface MathOperation{
        int operation(int a,int b);
    }
    interface GreetingSerivce{
        void sayMessage(String message);
    }
    private int operation(int a,int b,MathOperation mathoperation){
        return mathoperation.operation(a,b);
    }

    
}