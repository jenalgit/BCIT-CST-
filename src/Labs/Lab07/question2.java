package Lab07;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class question2
{

    public static void main(String[] args) throws IOException
    {
        String text = new String(Files.readAllBytes(
                Paths.get("/Users/Kunlaya/term3/COMP 3760 ALgorithm/Labs/AlgorithmClass/src/Lab07/q2input.txt")), 
                StandardCharsets.UTF_8);
        String[] words = text.toLowerCase().split("\\s+");
        
        boolean isDistinct = true;
        HashSet<String> hs = new HashSet<>();
        for (String s: words) {
            if (!hs.add(s)) {
                isDistinct = false;
                break;
            }
        }
        System.out.println(isDistinct ? "DISTINCT" : "NOT DISTINCT");
    }

}
