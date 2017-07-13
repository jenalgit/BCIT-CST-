package Lab05;

import java.util.Arrays;

/*
 * Kunlaya Kobunnoi
 * 
 */
public class Question2
{
    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1)
            return arr;
        else {
            int mid = arr.length / 2;
            int[] leftresult = mergeSort(Arrays.copyOfRange(arr, 0, mid));
            int[] rightresult = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
            return merge(leftresult, rightresult);
        }
    }
    
    public static int[] merge(int[] leftArray, int[] rightArray) {
        int i = 0, j = 0, k = 0;
        int[] resultArray = new int[leftArray.length + rightArray.length];
        
        while(i < leftArray.length && j < rightArray.length)
            resultArray[k++] = leftArray[i] < rightArray[j] ? leftArray[i++] : rightArray[j++];
            
        while(i < leftArray.length)
            resultArray[k++] = leftArray[i++];
        while(j < rightArray.length)
            resultArray[k++] = rightArray[j++];
            
        return resultArray;
    }
    
    public static void main(String[] args)
    {
        int arr[] = { 2,5,8,3,6,9,1,6,5 };

        int[] resultArray = mergeSort(arr);
        
        System.out.println("Before sorting:");
        for (int i = 0; i < arr.length; ++i) 
            System.out.print(arr[i] + (i == arr.length-1 ? "" : ","));
        
        System.out.println("\nAfter sorting:");
        for (int i = 0; i < resultArray.length; ++i) 
            System.out.print(resultArray[i] + (i == resultArray.length-1 ? "" : ","));
    }
}
