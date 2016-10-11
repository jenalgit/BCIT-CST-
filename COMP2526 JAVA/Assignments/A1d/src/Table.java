/**
 * Class Table.
 * 
 * @author Kunlaya Kobunnoi
 *
 */
public class Table {

  /** 
   * This stores calculator type.
   */
  private Calculator calc;
  /** 
  * This stores 2D array for multiplication table.
  */
  protected float[][] table; 
  /** 
  * This stores the starting value.
  */
  private int start;
  /**
  * This stores the ending value.
  */
  private int stop;
    
  /**
   * Constructor.
   * 
   * @param start for starting value
   * @param stop  for ending value
   * @param calc  for type of calculator
   */
  public Table(final int start, final int stop, final Calculator calc) {
    this.start = start;
    this.stop = stop;
    this.calc = calc;
  }
    
  /**
   * Method to return description.
   * 
   * @return description
   */
  public String getDescription() {
    return calc.getDescription();
  }
   
  /**
   * Method to return start.
   * 
   * @return start value
   */
  public int getStart() {
    return start;
  }
   
  /**
   * Method to return size.
   * 
   * @return size
   */
  public int getSize() {
    return (stop - start + 1) ;
  }
   
  /**
   * Method to return value from specific row and column.
   * 
   * @param row  for row
   * @param col  for column
   * @return value
   */
  public float getValueAt(final int row, final int col) {
    return calc.calcValue(row, col);
  }
   
  /**
   * Method to create addition table.
   */
  public void createAdditionTable() {
    calc = new AdditionCalculator("+");
    table = new float[getSize()][getSize()];     

    for (int row = 0; row < table.length; row++) {
      for (int col = 0; col < table.length; col++) {
        table[row][col] = calc.calcValue((row + start), (col + start)); 
      }
    }
  }
   
  /**
   * Method to create subtraction table.
   */
  public void createSubtractionTable() {
    calc = new SubtractionCalculator("-");
    table = new float[getSize()][getSize()];     
    
    for (int row = 0; row < table.length; row++) {
      for (int col = 0; col < table.length; col++) {
        table[row][col] = calc.calcValue((col + start), (row + start)); 
      }
    }
  }
    
  /**
   * Method to create multiplication table.
   */
  public void createMultiplicationTable() {
    calc = new MultiplicationCalculator("*");
    table = new float[getSize()][getSize()];     
        
    for (int row = 0; row < table.length; row++) {
      for (int col = 0; col < table.length; col++) { 
        table[row][col] = calc.calcValue((row + start), (col + start)); 
      }
    }
  }
    
  /**
   * Method to create division table.
   */
  public void createDivisionTable() {
    calc = new DivisionCalculator("/"); 
    table = new float[getSize()][getSize()];    
        
    for (int row = 0; row < getSize(); row++) {
      for (int col = 0; col < getSize(); col++) {
        table[row][col] = calc.calcValue((row + start), (col + start));   
      }
    }   
  }
}