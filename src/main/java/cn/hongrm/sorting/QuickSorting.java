package cn.hongrm.sorting;

/**
 * 快排
 * created by hongrm on 2018/4/1 14:40
 */
public class QuickSorting {
    public static void main(String[] args) {
        int[] array = new int[]{1, 7, 2};
        quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partition = partition(arr, left, right);
            quickSort(arr, left, partition - 1);
            quickSort(arr, partition + 1, right);
        }
    }
    // 为了防止出现N^2的时间复杂度应该随机在arr取一个下标而不是取0的位置
    public static int partition(int[] arr, int left, int right) {
        // j = right + 1 ,这里为了统一对 j 进行 -- 操作吧
        int i = left, j = right + 1;
        int part = left;

        while (true) {
            while (arr[++i] < arr[part] && i < right) ;
            while (arr[--j] > arr[part]) ;
            if (i >= j)
                break;
            swap(arr, i, j);
        }
        int tmp = arr[part];
        arr[part] = arr[j];
        arr[j] = tmp;
        return j;
    }

    static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}
