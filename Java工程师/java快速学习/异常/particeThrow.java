/**
 * 异常的相关练习
 */

 public class Account{
     protected double balance;

     public Account(){

     }
     public Account(double balance){
         this.balance=balance;
     }

     public double getBalance(){
         return balance;
     }

     public void depoist(double amt){
         balance+=amt;

     }

     public void withdraw(double amt) throws OverdraftException{
         if(balance<amt){
             throw new OverdraftException("余额不足",amt-balance);
         }
         balance-=amt;
     }

     class OverdraftException extends Exception{
         private double deficit;

         public OverdraftException(){
            super("透支异常");
         }
         public OverdraftException(String message,double deficit){
            super(message);
            this.deficit=deficit;
         }

         public double getDeficit(){
             return deficit;
         }
     }
     public static void main(String[] args){

     }

 }