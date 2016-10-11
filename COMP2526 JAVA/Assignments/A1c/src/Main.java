/**
 * Main class drives the program.
 * @author Kunlaya Kobunnoi
 * @author A00959419 Set C
 * @version October 01,2016
 */
public class Main {
  /**
   * Drives the program.
   * @param argv arguments
   */
  public static void main(final String[] argv) {
    final TableType type;
    final int       start;
    final int       stop;
        
  
    if (argv.length != 3) {
      usage();
    }

    type  = getType(argv[0]);
    start = getNumber(argv[1]);
    stop  = getNumber(argv[2]);
        
    getTable(type,start,stop);
        
  }
  /**
   * Returns the TableType represented by the specified String.
   * @param str the table type
   * @return TableType
   */
  
  public static TableType getType(final String str) {
    final TableType type;
        
    if (str.equals("+")) {
      type = TableType.ADD;
    } else if (str.equals("-")) {
      type = TableType.SUB;
    } else if (str.equals("*")) {
      type = TableType.MULT;
    } else {
      usage();
      type = null;
    }
        
    return (type);
  }
  /**
   * Returns the number contained in the specified String.
   * @param str contains a number
   * @return number as an integer.
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
   * @param type tables type
   * @param start tables starting value
   * @param stop  tables ending value
   * 
   * @return Table
   */
  public static Table getTable(final TableType type,
                               final int start,
                               final int stop) { 
    Table table;
    
    if (type == TableType.ADD) {
      table  = new AdditionTable(start, stop, "+");
      table.createTable();
      table.displayTable();
    } else if (type == TableType.MULT) {
      table = new MultiplicationTable(start, stop, "*");
      table.createTable();
      table.displayTable();
    } else {
      table = new SubtractionTable(start, stop, "-");
      table.createTable();
      table.displayTable();
    }
    return table;
  } 
  /**
   * Prints a program usage screen to the console.
   */
  
  public static void usage() {
    System.err.println("Usage: Main <type> <start> <stop>");
    System.err.println("\tWhere <type> is one of +, \"*\"");
    System.err.println("\tand <start> is between 1 and 100");
    System.err.println("\tand <stop> is between 1 and 100");
    System.err.println("\tand start < stop");
    System.exit(1);
  }            
}
