/*
 * This program prints out the multiplication or addition table
 * given the start and end range and type of table specified by the user. 
 */

/**
 * 
 * @author Kunlaya Kobunnoi.
 * @author A00959419 SET C
 *
 */
public class ArithmeticTable {
    private static enum TableType { ADD, MULT }
    
    private float[][] table;
    private int start;
    private int end;
    private TableType tableType;
    private int tableSize;
    private String operator;

    /**
    *  Returns true if the arguments passed by user are valid.
    * @param args User input.
    * @return boolean true/false.
    */
    public boolean argumentCheck(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +, \"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }
        if (args[0].charAt(0) == '+') {
            tableType = TableType.ADD;
        } else {
            tableType = TableType.MULT;
            int sta;
            int sto;
            try {
                sta = Integer.parseInt(args[1]);
                sto = Integer.parseInt(args[2]);
            } catch (NumberFormatException ex) {
                System.err.println("Usage: Main <type> <start> <stop>");
                System.err.println("\tWhere <type> is one of +, -, \"*\", /");
                System.err.println("\tand <start> is between 1 and 100");
                System.err.println("\tand <stop> is between 1 and 100");
                System.err.println("\tand start < stop");
                return false;
            }
            if ((sta < 1 || sta > 100) || ((sto < 1 || sto > 100))) {
                System.err.println("Usage: Main <type> <start> <stop>");
                System.err.println("\tWhere <type> is one of +, -, \"*\", /");
                System.err.println("\tand <start> is between 1 and 100");
                System.err.println("\tand <stop> is between 1 and 100");
                System.err.println("\tand start < stop");
                return false;
            }

            if (sta >= sto) {
                System.err.println("Usage: Main <type> <start> <stop>");
                System.err.println("\tWhere <type> is one of +, -, \"*\", /");
                System.err.println("\tand <start> is between 1 and 100");
                System.err.println("\tand <stop> is between 1 and 100");
                System.err.println("\tand start < stop");
                return false;
            }

            start = sta;
            end = sto;
        }
        return true;

    }

    /**
    * This method creates 2d array of provided operation by the user.
    * @param begin the table with ending value.
    * @param finish the table with ending value.
    * @param tableType table arithmetic.
    */
    public void createTable(int begin, int finish, TableType tableType) {
        
        start = begin;
        end = finish;
        tableSize = end - start + 1;
        table = new float[tableSize][tableSize];
       
        switch (tableType) {
          case ADD:
              for (int row = 0; row < table.length; row++) {
                  for (int col = 0; col < table.length; col++) {
                      table[row][col] = (row + start) + (col + start);
                  }
              }
              break;
          case MULT:
              for (int row = 0; row < table.length; row++) {
                  for (int col = 0; col < table.length; col++) {
                      table[row][col] = (row + start) * (col + start);
                  }
              }
              break;
          default:
              break;
        }
    }

    /**
    * This method print out the arithmetic table.
    */
    public void printTable() {
        String line = "_______";
        System.out.printf("\n");
        
        if (tableType == TableType.ADD) {
            operator = "+";
        } else {
            operator = "*";
            System.out.printf("%6s", operator);
        }

        
        System.out.printf("  ");
        for (int i = 0; i < table.length; i++) {
            System.out.printf("%6d", (i + start));
        }
        System.out.printf("\n");
        
       
        System.out.printf("  ");
        for (int i = 0; i <= table.length; i++) {
            System.out.printf("%6s", line);
        }
        System.out.printf("\n");
        
        
        for (int row = 0; row < table.length; row++) {
            System.out.printf("%6d |", row + start);
            for (int col = 0; col < table.length; col++) {
                System.out.printf("%6.0f", table[row][col]); 
            }
            System.out.printf("\n");
        }
    }

    /**
    * Main method.
    * @param args user input.
    */
    public static void main(String[] args) {
        ArithmeticTable table = new ArithmeticTable();
        if (table.argumentCheck(args)) {
            table.createTable(table.start, table.end, table.tableType);
            table.printTable();

        }
    }
}