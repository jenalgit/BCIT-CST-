package Lab08;

public class GraphTest
{

    public static void main(String[] args)
    {
        AdjGraph ag = new AdjGraph(5);
        
        ag.addEdge(0, 1);
        ag.addEdge(0, 3);
        ag.addEdge(0, 4);
        ag.addEdge(1, 2);
        ag.addEdge(1, 4);
        ag.addEdge(2, 3);
        ag.addEdge(3, 4);
        
        System.out.println(ag.toString());
        System.out.println("degree[0]=" + ag.degree(0));
        System.out.println("degree[2]=" + ag.degree(2));
        System.out.println("directed=" + ag.isDirected());
        System.out.println("--------------------");
        System.out.println();
        
        AdjGraph dag = new AdjGraph(5, true);
        dag.addEdge(0, 1);
        dag.addEdge(0, 3);
        dag.addEdge(0, 4);
        dag.addEdge(1, 2);
        dag.addEdge(1, 4);
        dag.addEdge(2, 3);
        dag.addEdge(3, 4);
        
        System.out.println(dag.toString());
        System.out.println(" inDegree[1]=" + dag.inDegree(1));
        System.out.println("outDegree[1]=" + dag.outDegree(1));
        System.out.println("directed=" + dag.isDirected());
        System.out.println("--------------------");
        System.out.println();
        
        AdjGraph testGraph = new AdjGraph(8);
        testGraph.addEdge(0, 1);
        testGraph.addEdge(0, 2);
        testGraph.addEdge(0, 4);
        testGraph.addEdge(1, 3);
        testGraph.addEdge(1, 5);
        testGraph.addEdge(2, 3);
        testGraph.addEdge(2, 6);
        testGraph.addEdge(3, 7);
        testGraph.addEdge(4, 5);
        testGraph.addEdge(4, 6);
        testGraph.addEdge(5, 7);
        testGraph.addEdge(6, 7);
        System.out.println("# Test graph for dfs and bfs:");
        System.out.println(testGraph.toString());
        
        System.out.println("DFS start: ");
        testGraph.DFS();
        System.out.println();
        System.out.println("BFS start: ");
        testGraph.BFS();
    }

}
