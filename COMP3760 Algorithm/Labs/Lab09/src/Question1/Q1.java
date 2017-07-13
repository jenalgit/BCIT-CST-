 package Question1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Q1
{
    public static ArrayList<String> getAlbums(Scanner scanner, int count) {
        ArrayList<String> lines = new ArrayList<>();
        
        for (int i = 0; i < count; ++i)
            lines.add(scanner.nextLine());
        
        return lines;
    }
    
    public static void rearrangeAlbums(ArrayList<String> originaAlb, ArrayList<String> desiredAlb) {
        int i, j;
        for (i = originaAlb.size()-1, j = i; i >= 0; --i) {
            String str1 = originaAlb.get(i);
            String str2 = desiredAlb.get(j);
            if (str1.equals(str2)) 
                --j;
        }
        
        for(; j >= 0; --j) {
            String str1 = desiredAlb.get(j);
            System.out.println(str1);
            //originaAlb.remove(str1);
            //originaAlb.add(0, str1);
        }
        System.out.println("");
        
        //System.out.println("--------------------------");
        //for (i = 0; i < originaAlb.size(); ++i)
        //  System.out.println(originaAlb.get(i));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("src/Question1/SampleInput.txt"));
        int cases = in.nextInt();

        for(int i = 0; i < cases; ++i) {
            int albumCnt = in.nextInt();
            in.nextLine();
            
            ArrayList<String> arrSrc = getAlbums(in, albumCnt);
            ArrayList<String> arrDst = getAlbums(in, albumCnt);

            rearrangeAlbums(arrSrc, arrDst);
        }
        in.close();
    }
}
