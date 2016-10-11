/**
 * Abstract class Calculator.
 * 
 * @author Kunlaya Kobunnoi
 *
 */
public abstract class Calculator {

  /** Stores type of calculator as string. */
  private String calculatorType;
    
  /**
   * Constructor.
   * 
   * @param s for calculator type
   */
  protected Calculator(String string) {
    calculatorType = string;
  }

  /**
   * Method to return calculator type.
   * 
   * @return description
   */
  public String getDescription() {
    return calculatorType;
  }

  /**
   * Abstract method to return value from specific row and column.
   * 
   * @param row     for row
   * @param col     for column
   * @return value
   */
  public abstract float calcValue(int row, int col);
}