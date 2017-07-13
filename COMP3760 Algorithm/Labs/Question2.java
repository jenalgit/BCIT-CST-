package ca.bcit.comp3760;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Question2
{
    public static final String DICT_END = "*";
    
    private ArrayList<String> dictionary;
    private int[][] matrix; 
    private boolean[] visit;
    
    public Question2() {      
    }
    
    private int readDictionary(Scanner in) {
        dictionary = new ArrayList<>();
        
        String line = in.nextLine();
        while (!line.equals(DICT_END)) {
            dictionary.add(line);
            line = in.nextLine();
        }
        return dictionary.size();
    }
    
    private void initGraph(int v) {
        matrix = new int[v][v];
    }
    
    private void addAdjEdge() {
        int len = matrix.length;
        for (int i = 0; i < len-1; ++i) {
            for (int j = i+1; j < len; ++j)
                if (isAdjacent(dictionary.get(i), dictionary.get(j))) {
                    matrix[i][j] = 1;
                    matrix[j][i] = 1;
                }
        }           
    }
    
    private int bfs(int sIdx, int eIdx) {
        visit = new boolean[matrix.length];
        
        visit[sIdx] = true;
        System.out.print(dictionary.get(sIdx) + " ");
        
        int cnt = 0;
        Queue<Integer> queW = new LinkedList<>();
        Queue<Integer> queC = new LinkedList<>();
        queW.add(sIdx);
        queC.add(cnt);
        while(queW.peek() != null) {
            int i = queW.peek();
              cnt = queC.peek();
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == 1 && !visit[j]) {
                    visit[j] = true;
                    System.out.print(dictionary.get(j) + " ");
                    ++cnt;
                    System.out.print(cnt + "/ ");
                    if (j == eIdx) {
                        System.out.println(cnt);
                        return cnt;
                    }
                    queW.add(j);
                    queC.add(cnt);
                }
            }
            queW.remove();
            queC.remove();
        }
        return cnt;
    }
    
    public void makeGraph(Scanner in) {
        
        this.readDictionary(in);
        this.initGraph(dictionary.size());
        this.addAdjEdge(); 
    }
    
    public void findSuccessiveWords(String start, String end) {
        bfs(dictionary.indexOf(start), dictionary.indexOf(end));
    }
    
    public static boolean isAdjacent(String s1, String s2) {
        
        if (s1.length() != s2.length())
            return false;
        
        int cnt = 0;
        for(int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i))
                ++cnt;
        }
        return cnt == 1;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner in = new Scanner(new FileReader("src/sample2.txt"));
        int cases = in.nextInt();
        in.nextLine();
        
        for(int i = 0; i < cases; ++i) {
            Question2 q2 = new Question2();
            q2.makeGraph(in);
            
            while(in.hasNext()) {
                String line = in.nextLine();
                String[] words = line.split("\\s+");
                q2.findSuccessiveWords(words[0], words[1]);
            }
        }
        in.close();
    }

}
