/**
 * MultiplicationTable class creates a multiplication table.
 * @author Kunlaya Kobunnoi
 *
 */
public class MultiplicationTable extends Table {
 
  /**
  * Non-default constructor.
  * 
  * @param first tables first value
  * @param last  tables ending value
  * @param type  tables arithmetic operator
  */
  public MultiplicationTable(int first, int last, String type) {
        
        super(first, last, type);
  }
    
  /**
   * method to create table.
   */
  @Override
    public void createTable() {
    tableSize = last - first + 1;               
    table = new float[tableSize][tableSize];    
        
    // creates multiplication table
    for (int row = 0; row < table.length; row++) {
      for (int col = 0; col < table.length; col++) {
        table[row][col] = (row + first) * (col + first);
      }
    }
  }
}
    
   