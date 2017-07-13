package Lab07;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class question3
{
    public static int getKeywdCnt(String line) {
        String[] words = line.split("\\s+");
        return Integer.parseInt(words[0]);
    }
    
    public static int getStatementCnt(String line) {
        String[] words = line.split("\\s+");
        return Integer.parseInt(words[1]);
    }
    
    public static int getKeywdCntInStmt(HashSet<String> kwSet, String line) {
        String[] words = line.split("\\s+");
        int cnt = 0;
        for(String s: words) {
            if (kwSet.contains(s))
                ++cnt;
        }
        return cnt;
    }
    
    public static void main(String[] args) throws IOException
    {
        String text = new String(Files.readAllBytes(
                Paths.get("/Users/Kunlaya/term3/COMP 3760 ALgorithm/Labs/AlgorithmClass/src/Lab07/q3test.txt")), 
                StandardCharsets.UTF_8);
        String[] lines = text.toLowerCase().split("[\\r\\n]+");
        int keywdCnt = getKeywdCnt(lines[0]);
        int stmtCnt = getStatementCnt(lines[0]);
        
        HashSet<String> keywdSet = new HashSet<>();
        for (int i = 1; i <= keywdCnt; ++i) 
            keywdSet.add(lines[i]);

        TreeMap<String, Integer> treeMap = new TreeMap<>();
        int maxCnt = 0;
        int endline = keywdCnt + stmtCnt;
        for (int i = keywdCnt + 1; i <= endline; ++i) {
            if (!treeMap.containsKey(lines[i])) {
                int tmp = getKeywdCntInStmt(keywdSet, lines[i]);
                if (tmp > maxCnt)
                    maxCnt = tmp;
                treeMap.put(lines[i], tmp);
            }   
        }
        System.out.println("matching keyword count: " + maxCnt);
        for (Map.Entry<String, Integer> entry: treeMap.entrySet()) {
            if (entry.getValue() == maxCnt)
                System.out.println(entry.getKey());
        }
    }

}
