public class Table {
    
  /** Stores multiplying, adding and subtracting operator as String .*/
  protected final String operator; 
    
  /** 2D array for multiplication, addition and subtraction table. */
  protected float[][] table;     
    
  /** Stores array size of the table. */
  protected int tableSize;
    
  /** Stores starting value of the table.*/
  protected int first;
    
  /** Stores ending value of the table. */
  protected int last;
    
  /**
   * Non-default constructor.
   * 
   * @param start tables starting value
   * @param stop  tables ending value
   * @param type  tables arithmetic operator
   */
  protected Table(final int start, final int stop, final String type) {
    this.first    = start;
    this.last     = stop;
    this.operator = type;
  }
    
  /**
  * Undefined method to create table.
  */
  protected void createTable() {
        
  }
    
  /**
   * Method to display table.
   */
  protected void displayTable() {
    String line = "_______";
    System.out.printf("%n");
       
    // prints operator
    System.out.printf("%5s", operator);

    // prints header numbers
    System.out.printf("  ");
    for (int i = 0; i < table.length; i++) {
      System.out.printf("%5d", (i + first));
    }

    System.out.printf("%n");

    // prints underline under header numbers
    System.out.printf("  ");
    for (int i = 0; i <= table.length; i++) {
      System.out.printf("%5s", line);
    }

    System.out.printf("%n");

    // prints side column numbers and elements of array
    for (int row = 0; row < table.length; row++) {
      System.out.printf("%5d |", row + first);
      for (int col = 0; col < table.length; col++) {
        System.out.printf("%5.0f", table[row][col]);
      }
            
      System.out.printf("%n");
    }
  }
}
    
