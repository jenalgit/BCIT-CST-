
/**
 * Main class drives the program.
 * @author Kunlaya Kobunnoi
 * @author A00959419 Set C
 * @version September 22,2016
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
        
        displayTable(type, start, stop);
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
     * Displays a table with the specified bounds using
     * the specified TableType (operation).
     * @param type of operation
     * @param start upper bound
     * @param stop lower bound
     */
    public static void displayTable(final TableType type,
                                    final int start,
                                    final int stop) {
     
        if ( type == TableType.ADD) {
            AdditionalTable add = new AdditionalTable(start,stop);
            add.createTable();
            add.display();
        } else {
            MultiplicationTable multiply = new MultiplicationTable(start,stop);
            multiply.createTable();
            multiply.display();
        }
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
