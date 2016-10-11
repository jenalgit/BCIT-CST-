/**
 * Class SubtractionCalculator extends Calculator.
 * 
 * @author Kunlaya Kobunnoi
 *
 */
public class SubtractionCalculator extends Calculator {
  private String type;
  /**
   * Constructor.
   * 
   * @param operator for calculator type
   */
  
  public SubtractionCalculator(String operator) {
  // calls parent constructor
  super(operator);
    type = operator;
  }

  /**
  * Method to return calculator type.
  * 
  * @return type
  */
  @Override
  public String getDescription() {
    return type;
  }

  /**
  * Method to return calculated value.
  * 
  * @param row     for row
  * @param col     for column
  * @return (col - row)
  */
  @Override
  public float calcValue(int row, int col) {
    return (col - row);
  }
}