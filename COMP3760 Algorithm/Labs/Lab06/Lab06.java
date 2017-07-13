package Lab06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Lab06
{
    public static ArrayList<Integer> findSmallestNumbers(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new MaxComparator());
        
        for (int x : arr) {
            if (pq.size() < k)
                pq.offer(x);
            else if (pq.peek() > x) {
                pq.poll();
                pq.offer(x);
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < k; ++i)
            res.add(pq.poll());
        
        return res;
    }
    
    public static void main(String[] args) {
        int[] a = {1, 10, 5, 3, 4, 7, 6, 9, 8};
        
        
        
        System.out.println("Sample array is:  {1, 10, 5, 3, 4, 7, 6, 9, 8}; ");
        
        ArrayList<Integer> res = findSmallestNumbers(a, 3);
        
        int k = 4;
        System.out.println("In case of K: " + k);
        System.out.println("The result: " + res);
    }

    static class MaxComparator implements Comparator<Integer> {

        public int compare(Integer one, Integer two) {
            return two - one;
        }
    }
}
