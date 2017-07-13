 package ca.bcit.comp3760;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Question1
{
    public static ArrayList<String> getAlbums(Scanner in, int cnt) {
        ArrayList<String> lines = new ArrayList<>();
        
        for (int i = 0; i < cnt; ++i)
            lines.add(in.nextLine());
        
        return lines;
    }
    
    public static void rearrangeAlbums(ArrayList<String> orgAlbums, ArrayList<String> dstAlbums) {
        int i, j;
        for (i = orgAlbums.size()-1, j = i; i >= 0; --i) {
            String str1 = orgAlbums.get(i);
            String str2 = dstAlbums.get(j);
            if (str1.equals(str2)) 
                --j;
        }
        
        for(; j >= 0; --j) {
            String str1 = dstAlbums.get(j);
            System.out.println(str1);
            //orgAlbums.remove(str1);
            //orgAlbums.add(0, str1);
        }
        System.out.println("");
        
        //System.out.println("--------------------------");
        //for (i = 0; i < orgAlbums.size(); ++i)
        //  System.out.println(orgAlbums.get(i));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("src/sample1.txt"));
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
