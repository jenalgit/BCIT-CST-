package Lab07;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class question1
{
    public static Map<String, Integer> getWordCnt(String[] words) {
        HashMap<String, Integer> wcMap = new HashMap<>();
        for (String s: words) {
            int cnt = 1;
            if (wcMap.containsKey(s)) 
                cnt = wcMap.get(s) + 1;
            wcMap.put(s, cnt);
        }
        return wcMap;
    }
    
    public static void main(String[] args) throws IOException
    {
        String text = new String(Files.readAllBytes(
                Paths.get("/Users/Kunlaya/term3/COMP 3760 ALgorithm/Labs/AlgorithmClass/src/Lab07/love.txt")), 
                StandardCharsets.UTF_8);
        String[] words = text.toLowerCase().split("\\s+");

        TreeMap<String, Integer> treeMap = new TreeMap<>(getWordCnt(words));
        int i = 0;
        for (Map.Entry<String, Integer> entry: treeMap.entrySet()) {
            System.out.printf("%02d: %10s - %2d\n", ++i, entry.getKey(), entry.getValue());
        }
    }

}
