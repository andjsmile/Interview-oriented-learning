import java.util.Arrays;

public class sortArray{

    public static int[][] constructArray(int len1,int len2){
        int [][]arr=new int[len1][len2];
        for(int i=0;i<len1;++i){
            for(int j=0;j<len1;++j){
                arr[i][j]=(int)(Math.random()*100);
            }
        }
        return arr;
    }

    public static int[] sortArray(int[][] arr){
        int len1=arr.length;
        int len2=arr[0].length;

        int[] newArr=new int[len1*len2];
        for(int i=0;i<len1;++i){
            System.arraycopy(newArr,arr[i].length *i,arr[i],0,a[i].length);
        }
        Arrays.sort(newArr);
        return newArr;
    }

    // 将一维数组转换为二维数组
    public static int[][] reConstruct(int[]){

    }

    public static void main(String[] args){
        int len1=5;
        int len2=8;
        int [][] arr=constructArray(len1,len2);
    }

    public static void main(String[] args){
        int a[][]=new int[5][8];
        for(int i=0;i<a.length;++i){
            for(int j=0;j<a[i].length;++j){
                a[i][j]=(int)(Math.random()*100);
            }
        }
        System.out.println("二位数组:");
        for(int[] item:a){
            System.out.println(Arrays.toString(item));
        }

        int b[]=new int[a.length*a[0].length];
        for(int i=0;i<a.length;++i){
            System.arraycopy(a[i], 0, b,i*a[i].length,a[i].length);
        }

        Arrays.sort(b);

        for(int i=0;i<a.length;++i){
            System.arraycopy(b, a[i].length*i, a[i], 0, a[i].length);
        }

        System.out.println("排序后数组:");

        for(int [] item:a){
            System.out.println(Arrays.toString(item));
        }
    }
}