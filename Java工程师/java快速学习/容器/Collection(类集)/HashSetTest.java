import java.util.HashSet;

/**
 * HashSet
 * 
 * 不保证set的迭代顺序，
 * 在不同条件下元素的顺序可能都不同
 */

public class HashSetTest{
    public static void main(String[]args){

        HashSet<Integer> num=new HashSet<>();

        num.add(10);
        num.add(9);
        num.add(32);
        
        System.out.println(num);
    }
}
