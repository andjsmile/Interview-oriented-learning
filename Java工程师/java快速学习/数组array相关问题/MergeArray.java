import java.util.*;

public class MergeArray{

    public static int[] mergeTwoArray(int[] arr1,int[] arr2){
        int len1=arr1.length;
        int len2=arr2.length;
        int[] mergeArr=new int[len1+len2];
        System.arraycopy(arr1, 0, mergeArr, 0, len1);
        System.arraycopy(arr2, 0, mergeArr, len1,len2);

        return mergeArr;

    }

    public static int[] constructArray(int len){
        int[] arr=new int[len];
        for(int i=0;i<len;++i){
            arr[i]=(int)(Math.random() *100);
        }
        return arr;
    }

    public static void printArr(int[] arr){
        for(int item:arr){
            System.out.print(item+" ,");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int len1=(int)(Math.random()*6+5);
        int len2=(int)(Math.random()*6+5);

        int[] arr1=constructArray(len1);
        int[] arr2=constructArray(len2);

        printArr(arr1);
        printArr(arr2);
        int[] res=mergeTwoArray(arr1, arr2);
        printArr(res);
    }
}

/***
 * out:
 * 39 ,90 ,24 ,93 ,71 ,56 ,33 ,
 * 41 ,68 ,11 ,75 ,57 ,
 * 39 ,90 ,24 ,93 ,71 ,56 ,33 ,41 ,68 ,11 ,75 ,57 ,
 */