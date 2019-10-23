/**
 * 选择排序
 * 在未实现排序的数组中查找到最小的数，
 * 并于未排序数组中第一个元素位置进行交换
*/
public class SelectSort{
    
    public static int[] sort(int[] arr){
        for(int i=0;i<arr.length-1;++i){
            int index=i;
            //int temp=arr[i];
            for(int j=i+1;j<arr.length;++j){
                if(arr[j]<arr[index]){
                    index=j;
                    //temp=arr[j];
                }
            }
            swap(arr,i,index);
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
            System.out.println(arr[i]);
        }
        System.out.println();
    }
    public static void main(String[]args){
        int[] arr={18,62,68,82,65,9};

        printArr(arr);
        int[] res=sort(arr);
        printArr(res);
    }
}