package Lab06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueSample {

    public static void main(String[] args) {
        int[] a = {1, 10, 5, 3, 4, 7, 6, 9, 8};

        MaxComparator pqMaxComparetor = new MaxComparator();
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, pqMaxComparetor);

        for (int x : a) {
            pq.offer(x);
        }

        System.out.println("pq: " + pq);

        // print size
        System.out.println("size: " + pq.size());
        // return highest priority element in the queue without removing it
        System.out.println("peek: " + pq.peek());
        // print size
        System.out.println("size: " + pq.size());
        // return highest priority element and removes it from the queue
        System.out.println("poll: " + pq.poll());
        // print size
        System.out.println("size: " + pq.size());

        System.out.print("pq: " + pq);

    }

    static class MaxComparator implements Comparator<Integer> {

        public int compare(Integer one, Integer two) {
            return two - one;
        }
    }
}
