/**
 * AdditionalTable Class creates addition arithmetic table.
 * @author Kunlaya Kobunnoi
 *
 */
public class AdditionTable extends Table {

  /**
   * Non-default constructor.
   * 
   * @param first tables starting value
   * @param last  tables ending value
   * @param type  tables arithmetic operator
   */
  public AdditionTable(int first, int last, String type) {
        // calls parent constructor
        super(first, last, type);
  }
    
  /**
   * Method to create table.
   */
  @Override
    public void createTable() {
    tableSize = last - first + 1;                
    table = new float[tableSize][tableSize];     
        
    // creates addition table.   
    for (int row = 0; row < table.length; row++) {
      for (int col = 0; col < table.length; col++) {
        table[row][col] = (row + first) + (col + first);
      }
    }
  }
    
}
   