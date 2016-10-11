/**
 * MultiplicationTable class creates and displays a multiplication table.
 * @author Kunlaya Kobunnoi
 *
 */
public class MultiplicationTable {

    /**
    * Represents the operator multiple.
    */
    private static final String multiply = "*";
    /**
     * Holds 2D array for addition.
     */
    private float[][] table;
    /**
     * Holds table size.
     */
    private int tableSize;
    /**
     * Holds starting value of table.
     */
    private int start;
    /**
     * Holds ending value of table.
     */
    private int end;
    
    /**
     * This is a constructor that holds the starting and ending value.
     * @param start Starting value of addition table.
     * @param end   Ending   value of addition table.
     */
    public MultiplicationTable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * This method creates a multiplication table and total 
     * the product and place them in 2d array table.
     */
    public void createTable() {
        tableSize = end - start + 1;
        table = new float[tableSize][tableSize];
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table.length; col++) {
                table[row][col] = (row + start) * (col + start);
            }
        }
    }
    
    /**
    * This method displays multiplication table on 2d array table.
    */ 
    public void display() {
        String line = "_______";
        System.out.printf("%n");
        
        System.out.printf("%5s", multiply);
        
        System.out.printf("  ");
        for (int i = 0; i < table.length; i++) {
            System.out.printf("%5d", (i + start));
        }
        System.out.printf("%n");
    
   
        System.out.printf("  ");
        for (int i = 0; i <= table.length; i++) {
            System.out.printf("%5s", line);
        }
        System.out.printf("%n");
    
    
        for (int row = 0; row < table.length; row++) {
            System.out.printf("%5d |", row + start);
            for (int col = 0; col < table.length; col++) {
                System.out.printf("%5.0f", table[row][col]); 
            }
            System.out.printf("%n");
        }
    }
}
