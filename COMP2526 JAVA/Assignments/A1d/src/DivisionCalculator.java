/**
 * Class DivisionCalculator extends Calculator.
 * 
 * @author Kunlaya Kobunnoi
 *
 */
public class DivisionCalculator extends Calculator {

  private String type;
  /**
   * Constructor.
   * 
   * @param operator for calculator type.
   */
  
  public DivisionCalculator(String operator) {
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
  * @return (col / rol)
  */
  @Override
  public float calcValue(int rol, int col) {
    return (float)col / rol;
  }
}