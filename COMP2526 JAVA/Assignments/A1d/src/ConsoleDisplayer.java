/**
 * Class ConsoleDisplayer.
 * 
 * @author Kunlaya Kobunnoi
 *
 */
public class ConsoleDisplayer {

  /**
   * Default constructor.
   */
  public ConsoleDisplayer() {
        
  }
    
  /**
   * Method to display arithmetic table that accepts any table type.
   * 
   * @param table for table
   */
  public void displayTable(Table table) {
    String line = "--------";
    String operator = table.getDescription();
        
     
    if (operator.equals("+")) {
            
      table.createAdditionTable();
      System.out.printf("%n");
       
           
      System.out.printf("%5s", operator);

            
      System.out.printf("  ");
      for (int i = 0; i < table.getSize(); i++) {
        System.out.printf("%6d", (i + table.getStart()));
      }
      System.out.printf("%n");

           
      System.out.printf("  ");
      for (int i = 0; i <= table.getSize(); i++) {
        System.out.printf("%6s", line);
      }
      System.out.printf("%n");
            
            
      for (int row = 0; row < table.getSize(); row++) {
        System.out.printf("%5d |", row + table.getStart());
        for (int col = 0; col < table.getSize(); col++) {
          System.out.printf("%6.2f", table.getValueAt(row + table.getStart(), 
                                      col + table.getStart()));
        } 
        System.out.printf("%n");
      }
    } else if (operator.equals("-")) {
            
      table.createSubtractionTable();
      System.out.printf("%n");
            
      System.out.printf("%5s", operator);

      System.out.printf("  ");
      for (int i = 0; i < table.getSize(); i++) {
        System.out.printf("%6d", (i + table.getStart()));
      }
      System.out.printf("%n");

      System.out.printf("  ");
      for (int i = 0; i <= table.getSize(); i++) {
        System.out.printf("%6s", line);
      }
      System.out.printf("%n");
            
      for (int row = 0; row < table.getSize(); row++) {
        System.out.printf("%5d |", row + table.getStart());
        for (int col = 0; col < table.getSize(); col++) {
          System.out.printf("%6.2f", table.getValueAt(row + table.getStart(), 
                                      col + table.getStart()));
        }   
        System.out.printf("%n");
      }
    } else if (operator.equals("/")) {
            
      table.createDivisionTable();
      System.out.printf("%n");
            
           
      System.out.printf("%5s", operator);

      System.out.printf("  ");
      for (int i = 0; i < table.getSize(); i++) {
        System.out.printf("%6d", (i + table.getStart()));
      }
      System.out.printf("%n");

      System.out.printf("  ");
      for (int i = 0; i <= table.getSize(); i++) {
        System.out.printf("%6s", line);
      }

      System.out.printf("%n");
            
      for (int row = 0; row < table.getSize(); row++) {
        System.out.printf("%5d |", row + table.getStart());
        for (int col = 0; col < table.getSize(); col++) {
          System.out.printf("%6.2f", table.getValueAt(row + table.getStart(), 
                                      col + table.getStart()));
        }  
        System.out.printf("%n");
      }
    } else {
           
      table.createMultiplicationTable(); 
      System.out.printf("%n");
            
      System.out.printf("%5s", operator);

      System.out.printf("  ");
      for (int i = 0; i < table.getSize(); i++) {
        System.out.printf("%7d", (i + table.getStart()));
      }
      System.out.printf("%n");

      System.out.printf("  ");
      for (int i = 0; i <= table.getSize(); i++) {
        System.out.printf("%7s", line);
      }
      System.out.printf("%n");
            
      for (int row = 0; row < table.getSize(); row++) {
        System.out.printf("%5d |", row + table.getStart());
        for (int col = 0; col < table.getSize(); col++) {
          System.out.printf("%7.2f", table.getValueAt(row + table.getStart(), 
                                      col + table.getStart()));
        }     
        System.out.printf("%n");
      } 
    }
  }
}