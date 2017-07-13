package Lab08;

import java.util.LinkedList;
import java.util.Queue;

public class AdjGraph
{
    private int[][] matrix;
    private boolean directed;
    private boolean[] visit;
    
    AdjGraph(int v) {
        matrix = new int[v][v];
        directed = false;
    }
    
    AdjGraph(int v, boolean hasDirection) {
        matrix = new int[v][v];
        directed = hasDirection;
    }
    
    public void addEdge(int u, int v) {
        matrix[u][v] = 1;
        if (!directed)
            matrix[v][u] = 1;
    }
    
    public String toString() {
        String result = "";
        for(int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j)
                result += matrix[i][j] + " ";
            result += "\n";
        }
        return result;
    }
    
    public int degree(int v) {
        int sum = 0;
        for (int i = 0; i < matrix[v].length; ++i)
            if (matrix[v][i] == 1)
                ++sum;
        return sum;
    }
    
    public int outDegree(int v) {
        int sum = 0;
        for (int i = 0; i < matrix[v].length; ++i)
            if (matrix[v][i] == 1)
                ++sum;
        return sum;
    }
    
    public int inDegree(int v) {
        int sum = 0;
        for (int i = 0; i < matrix.length; ++i)
            if (matrix[i][v] == 1)
                ++sum;
        return sum;
    }
    
    public boolean isDirected() {
        return directed;
    }
    
    public void setDirected(boolean value) {
        directed = value;
    }
    
    public void DFS() {
        visit = new boolean[matrix.length];
        for (int i = 0; i < matrix.length; ++i) {
            if (degree(i) > 0 && !visit[i])
                    dfs(i);
        }
        visit = null;
    }
    
    private void dfs(int v) {
        visit[v] = true;
        System.out.println("visiting vertex " + v);
        
        for (int i = 0; i < matrix[v].length; ++i) 
            if (matrix[v][i] == 1 && !visit[i])
                dfs(i);
    }
    
    public void BFS() {
        visit = new boolean[matrix.length];
        for (int i = 0; i < visit.length; ++i) 
            if (!visit[i])
                bfs(i);
    }
    
    private void bfs(int v) {
        visit[v] = true;
        System.out.println("visiting vertex " + v);
        
        Queue<Integer> que = new LinkedList<>();
        que.add(v);
        while(que.peek() != null) {
            int i = que.peek();
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == 1 && !visit[j]) {
                    visit[j] = true;
                    System.out.println("visiting vertex " + j);
                    que.add(j);
                }
            }
            que.remove();
        }
    }
}
