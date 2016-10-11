/**
 * Main class drives the program.
 * @author Kunlaya Kobunnoi
 * @author A00959419 Set C
 * @version October 10,2016
 */
public class Main {
  /**
    * Main method.
    * 
    * @param argv command line arguments
    */
  public static void main(final String[] argv) {
    final String    type;
    final int       start;
    final int       stop;
        
    if (argv.length != 3) {
      usage();
    }

    type  = getType(argv[0]);
    start = getNumber(argv[1]);
    stop  = getNumber(argv[2]);
        
    // creates ConsoleDisplayer object
    ConsoleDisplayer display = new ConsoleDisplayer();
        
    // calls method from ConsoleDisplay to display table
    display.displayTable(getTable(type, start, stop));       
  }
  
  /**
   * the getType represented by the specified String.
   * @param str the table type
   * @return Type
   */
  public static String getType(final String str) {
    final String type;
        
    if (str.equals("+")) {
      type = "+";
    } else if (str.equals("-")) {
      type = "-";
    } else if (str.equals("x") || str.equals("*")) {
      type = "x";
    } else if (str.equals("/")) {
      type = "/";
    } else {
      usage();
      type = null;
    }
        
    return (type);
  }
  
  /**
   * Returns the number contained in the specified String.
   * @param str contains a number
   * @return number.
   */
  public static int getNumber(final String str) {
    int val;
    try {
      val = Integer.parseInt(str);
            
      if (val < 1 || val > 100) {
        usage();
      }
    } catch (NumberFormatException ex) {
      usage();
      val = 0;
    }
    return (val);
  }
     
  /**
   * getTable method to retrieve arithmetic table.
   * 
   * @param type calculator type
   * @param start tables starting value
   * @param stop  tables ending value
   * 
   * @return Table
   */
  public static Table getTable(final String type, final int start, final int stop) {
    Table table;     
    // determines type of table
    switch (type) {
      case "+": table = new Table(start, stop, new AdditionCalculator("+"));
                break;
      case "-": table = new Table(start, stop, new SubtractionCalculator("-"));
                break;
      case "*": table = new Table(start, stop, new MultiplicationCalculator("*"));
                break;
      case "/": table = new Table(start, stop, new DivisionCalculator("/"));
                break;
      default: System.err.println("Table type unknown");
                table = new Table(start, stop, new MultiplicationCalculator("*"));
                break;
    }
    return table;
  } 
  /**
   * Prints a program usage screen to the console.
   */
  
  public static void usage() {
    System.err.println("Usage: Main <type> <start> <stop>");
    System.err.println("\tWhere <type> is one of +, -, /, \"*\"");
    System.err.println("\tand <start> is between 1 and 100");
    System.err.println("\tand <stop> is between 1 and 100");
    System.err.println("\tand start < stop");
    System.exit(1);
  }            
}
