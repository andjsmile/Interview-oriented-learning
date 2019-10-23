/**
 * 冒泡排序:
 * 依次比较相邻的元素，进行对比进行交换
 * 将最大元素(最小元素)放到相应的位置
 */

public class BubbleSort{

    public static int[] sort(int[]arr){
        int len=arr.length;
        for(int i=0;i<len;++i){
            for(int j=len-1;j>i;--j){
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }
            }
        }
        return arr;
    } 

    public static void swap(int[]arr,int left,int right){
        int temp=arr[left];
        arr[left]=arr[right];
        arr[right]=temp;
    }

    public static void printArr(int[] arr){
        if(arr==null) return;
        for(int i=0;i<arr.length;++i){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void printFor(int[]arr){
        for(int each:arr){
            System.out.println(each);
        }
    }
    public static void main(String[]args){
        int[] arr={18,62,68,82,65,9};
        printArr(arr);
        int []res=sort(arr);
        printArr(res);
        printFor(res);
    }
}