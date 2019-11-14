package unsynch;

import unsynch.Bank;

public class unsynchBankTest{
    public static final int ALLCOUNTS=100;
    public final static int INITIALBALANCE=10000;
    public static final int MAX_AMOUNT=10000;
    public static final int DEALY=100;    // 0.1秒


    public static void main(String[] args){
        Bank bank=new Bank(ALLCOUNTS,INITIALBALANCE);
        for(int i=0;i<ALLCOUNTS;++i){
            int fromCount=i;
            Runnable r=()->{
                try{
                    while(true){
                        int toAccount=(int)(bank.size()* Math.random());
                        double amout=MAX_AMOUNT * Math.random();
                        bank.transfer(fromCount,toAccount,amout);
                        Thread.sleep((int)(Math.random() * DEALY));
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            };
            Thread t=new Thread(r);
            t.start();
        }
    }
}