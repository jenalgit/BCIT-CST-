package src;

/**
 * Class AdditionCalculator extends Calculator.
 * 
 * @author Kunlaya Kobunnoi
 *
 */
public class AdditionCalculator extends Calculator {
  private String type;
  /**
   * Constructor.
   * 
   * @param operator for calculator type
   */
  
  public AdditionCalculator(String operator) {
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
   * @param rol     for row
   * @param col     for column
   * @return (x + y)
   */
  @Override
    public float calcValue(int rol, int col) {
    return (rol + col); 
  }
}
