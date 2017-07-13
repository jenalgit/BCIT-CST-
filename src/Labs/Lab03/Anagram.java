package Lab03;


import java.io.IOException;
	import java.nio.charset.StandardCharsets;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.ArrayList;
	import java.util.Arrays;

public class Anagram {

	    //
	    // Technique #1
	    //
	    public static boolean isAnagram1(String word1, String word2) {
	    	StringBuilder sb;
	        if (word1.length() != word2.length())
	            return false;
	        else {
	            int len1 = word1.length();
	            //for (int i = 0; i < len1; ++i)
	              for(int i = 0; i < len1; i++){
	                char c = word1.charAt(i);
	                int len2 = word2.length();
	                int j = 0;
	                while(j < len2) {
	                    if (word2.charAt(j) == c) {
	                        sb = new StringBuilder(word2);
	                        sb.deleteCharAt(j);
	                        word2 = sb.toString();
	                        break;
	                    }
	                    j++;
	                }
	                if (j == len2)
	                    return false;
	            }
	            return true;
	        }
	    }

	    
	    //
	    // Technique #2
	    //
	    public static boolean isAnagram2(String word1, String word2) {
	        if (word1.length() != word2.length())
	            return false;
	        else {
	            char[] w1 = word1.toCharArray();
	            char[] w2 = word2.toCharArray();
	            Arrays.sort(w1);
	            Arrays.sort(w2);
	            
	            
	           /* if (w1.equals(w2)) 
	            	return true;
	            else 
	            	return false;*/
	            
	           int len = word1.length();
	           for (int i = 0; i < len; i++) {
	                if (w1[i] != w2[i])
	                    return false;
	            }
	            return true; 
	        }
	    }
	    
	   
	    
	    //
	    // Technique #3
	    //
	    public static boolean isAnagram3(String word1, String word2) {
	        if (word1.length() != word2.length())
	            return false;
	        else {
	            int i, len = word1.length();
	            int[] vec = new int[128];
	            
	            Arrays.fill(vec, 0);            
	            for (i = 0; i < len; ++i) { 
	                vec[(int)word1.charAt(i)]++;
	                vec[(int)word2.charAt(i)]--;
	            }
	            
	            for (i= 0; i < len; ++i) { 
	                if (vec[(int)word2.charAt(i)] != 0)
	                    return false;
	            }
	            return true;
	        }
	    }
	    
	    
	    // for each letter in word 1
	    //        search word 2 for the letter
	    //        if found, delete the letter from word 2
	    
	    public static void tech1(ArrayList<String> dictionary) {
	        
	        String maxStr = "";
	        int maxCnt = 0;
	        ArrayList<String> dict = new ArrayList<String>(dictionary);
	    
	        long startTime = System.currentTimeMillis();
	        for (int i = 0; i < dict.size(); i++) {
	            String word1 = dict.get(i);
	            
	            int cnt = 0;
	            int j = i + 1;
	            while(j < dict.size()) {
	                String word2 = dict.get(j);
	                if (isAnagram1(word1, word2)) {
	                    ++cnt;
	                    dict.remove(j);
	                }
	                else
	                    ++j;
	            }
	            if (cnt > maxCnt) {
	                maxStr = word1;
	                maxCnt = cnt;
	            }
	        }
	        long elapTime = System.currentTimeMillis() - startTime;
	        
	        System.out.println("Technique #1: [" + maxStr + "] has " + 
	                maxCnt + " anagrams, "+ elapTime / 1000.0 + " secs.");
	    }
	    
	    // sort word 1
	    // sort word 2
	    // use a linear compare of the 2 sorted words
	    public static void tech2(ArrayList<String> dictionary) {
	        
	        String maxStr = "";
	        int maxCnt = 0;
	        ArrayList<String> dict = new ArrayList<String>(dictionary);
	    
	        long startTime = System.currentTimeMillis();
	        for (int i = 0; i < dict.size(); i++) {
	            String word1 = dict.get(i);
	            
	          
	            
	            int cnt = 0;
	            int j = i + 1;
	            while(j < dict.size()) {
	                String word2 = dict.get(j);
	              
	                if (isAnagram2(word1, word2)) {
	                    ++cnt;
	                    dict.remove(j);
	                }
	                else
	                    ++j;
	            }
	            if (cnt > maxCnt) {
	                maxStr = word1;
	                maxCnt = cnt;
	            }
	        }
	        long elapTime = System.currentTimeMillis() - startTime;
	        
	        System.out.println("Technique #2: [" + maxStr + "] has " + 
	                maxCnt + " anagrams, "+ elapTime / 1000.0 + " secs.");
	    }
	    
	    // create letter vector for word 1 and word2 
	    // use a linear compare of the letter vectors
	    public static void tech3(ArrayList<String> dictionary) {
	        
	        String maxStr = "";
	        int maxCnt = 0;
	        ArrayList<String> dict = new ArrayList<String>(dictionary);
	    
	        long startTime = System.currentTimeMillis();
	        for (int i = 0; i < dict.size(); i++) {
	            String word1 = dict.get(i);
	            
	            int cnt = 0;
	            int j = i + 1;
	            while(j < dict.size()) {
	                String word2 = dict.get(j);
	                if (isAnagram3(word1, word2)) {
	                    ++cnt;
	                    dict.remove(j);
	                }
	                else
	                    ++j;
	            }
	            if (cnt > maxCnt) {
	                maxStr = word1;
	                maxCnt = cnt;
	            }
	        }
	        long elapTime = System.currentTimeMillis() - startTime;
	        
	        System.out.println("Technique #3: [" + maxStr + "] has " + 
	                maxCnt + " anagrams, "+ elapTime / 1000.0 + " secs.");
	    }

	    public static void main(String[] args) throws IOException
	    {
	        String text = new String(Files.readAllBytes(
	        		//C:\Users\Kunlaya\term3\COMP 3760 Algorithm\Labs
	                Paths.get("/Users/Kunlaya/term3/COMP 3760 Algorithm/Labs/Dict.txt")), 
	                StandardCharsets.UTF_8);
	        String[] words = text.toLowerCase().split(" ");
	        
	        ArrayList<String> dict = new ArrayList<String>(Arrays.asList(words));
	        
	        Anagram.tech1(dict);
	        Anagram.tech2(dict);
	        Anagram.tech3(dict);
	    }
}

