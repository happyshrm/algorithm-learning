package cn.hongrm.sorting;

/**
 * 快排
 * created by hongrm on 2018/4/1 14:40
 */
public class QuickSorting {
    public static void main(String[] args) {
        int[] array = new int[]{1,7,2};
        quickSort(array,0,array.length-1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public static void quickSort(int[] arr,int left,int right){
        if (left<right){
            int partition = partition(arr,left,right);
            quickSort(arr,left,partition-1);
            quickSort(arr,partition+1,right);
        }
    }
    public static int partition(int[] arr,int left,int right){
        int i = left,j=right+1;
        int part = left;

        while(true){
            while(arr[++i]<arr[part] && i<right  );
            while(arr[--j]>arr[part]);
            if (i>=j)
                break;
            swap(arr,i,j);
        }
        int tmp = arr[part];
        arr[part] = arr[j];
        arr[j] = tmp;
        return j;
    }
    static void swap(int[] arr,int left,int right){
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}
