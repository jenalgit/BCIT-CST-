
public class Main
{
    public static void main(final String[] argv)
    {
        final TableType type;
        final int       start;
        final int       stop;
        final Table niceTable;
        
        if(argv.length != 3)
        {
            usage();
        }

        type  = getType(argv[0]);
        start = getNumber(argv[1]);
        stop  = getNumber(argv[2]);
        
        // see assignment for what goes here
        niceTable = getTable(type, start, stop);
        niceTable.createTable();
        niceTable.display();
    }
    
    public static TableType getType(final String str)
    {
        final TableType type;
        
        if(str.equals("+"))
        {
            type = TableType.ADD;
        }
        else if(str.equals("-"))            
        {
            type = TableType.SUB;
        }
        else if(str.equals("*"))            
        {
            type = TableType.MULT;
        }
        else
        {
            usage();
            type = null;
        }
        
        return (type);
    }
    
    public static int getNumber(final String str)
    {
        int val;
        
        try
        {
            val = Integer.parseInt(str);
            
            if(val < 1 || val > 100)
            {
                usage();
            }
        }
        catch(NumberFormatException ex)
        {
            usage();
            val = 0;
        }
        
        return (val);
    }
    
    // see assignment for what goes here
    public static Table getTable(final TableType type,
                               final int start,
                               final int stop)
    {
    Table created;
    	if (type == TableType.ADD){
    	created = new AdditionTable(start, stop, "+");
    } else if (type == TableType.SUB){
    	created = new SubtractionTable(start, stop, "-");
    } else {
    	created = new MultiplicationTable(start, stop, "*");
    }
    	
    return (created);
    }    
    
    public static void usage()
    {
        System.err.println("Usage: Main <type> <start> <stop>");
        System.err.println("\tWhere <type> is one of +, \"*\"");
        System.err.println("\tand <start> is between 1 and 100");
        System.err.println("\tand <stop> is between 1 and 100");
        System.err.println("\tand start < stop");
        System.exit(1);
    }            
}
