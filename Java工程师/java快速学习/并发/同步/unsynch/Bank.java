package unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank{
    private final double accounts[];

    //private void bankLock=new ReentrantLock();
    private Lock banLock;

    private Condition sufficientFunds;

    public Bank(int n,double InitialBalance){
        accounts=new double[n];
        Arrays.fill(accounts,InitialBalance);

        banLock=new ReentrantLock();
        sufficientFunds=banLock.newCondition();
    }

    public double getTotalBalance(){
        double sum=0;
        for(double temp:accounts){
            sum+=temp;
        }
        return sum;
    }

    public int size(){
        return accounts.length;
    }

    public void transfer(int from,int to,double amount){
        bankLock.lock();
        try{
            while(accounts[from]<amount){
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            System.out.println("account["+from+"]");
            accounts[from]-=amount;
            System.out.printf("%10.3f from %d to %d",amount,from,to);
            accounts[to]+=amount;

            // print the toatl money each time
            System.out.printf("total balance:%10.2f %n",getTotalBalance());

            sufficientFunds.signalAll();
        }
        finally{
            bankLock.unlock();
        }
        
    }
}