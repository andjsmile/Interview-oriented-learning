
import java.util.LinkedList;

public class SlidingWindow{

    public static int[] getSlidingWindow(int[]arr,int w){
        if(arr==null || arr.length< w || w<1){
            return null;
        }
        int[] res=new int[arr.length - w + 1];
        LinkedList<Integer> dq=new LinkedList<Integer>();
        int index=0;
        for(int i=0;i<arr.length;++i){
            // 右窗口进行更新
            while(!dq.isEmpty() && arr[dq.peekLast()]<=arr[i]){
                dq.pollLast();
            }
            dq.addLast(i);
            // 左窗口进行右移动更新
            if(i - dq.peekFirst()==w){
                dq.pollFirst();
            }
            // 结果值的保存
            if(i>=w-1){
                res[index++]=arr[dq.peekFirst()];
            }
            
        }
        return res;
    }

    public static void printArray(int[]res){
        if(res==null){
            System.out.println("array is empty");
        }
        for(int i=0;i<res.length;i++){
            System.out.print(res[i]);
        }
        System.out.println();
    }
    public static void main(String[] args){
        int[]arr={4,3,5,4,3,3,6,7};
        int w=3;
        int []res=getSlidingWindow(arr,w);
        printArray(res);
    }
}