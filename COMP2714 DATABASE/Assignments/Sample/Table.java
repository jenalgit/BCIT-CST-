
public class Table {
	    private int start;
	    private int end;
	    private int tableSize;
	    protected float[][] protoTable;
	    private String type;

	    
	    protected Table (final int start, final int stop, final String type){
	    	this.start = start;
	        this.end = stop;
	        this.tableSize = end - start + 1;
	        this.type = type;
	    
	    }

        protected int getStart(){
        	return (start);
        }
        
        protected int getEnd(){
        	return(end);
        }
	    
        protected int getTableSize(){
        	return(tableSize);
        }
        
        
	    protected void createTable() {
	        int top = 0;
	        int bottom = 0;
	        int createTableSize = tableSize;
	        protoTable = new float[createTableSize][createTableSize];
	        for (int i = 0; i < createTableSize; i++) {
	            for (int j = 0; j < createTableSize; j++) {
	                protoTable[i][j] = top + bottom;
	                bottom++;
	                if (j == createTableSize - 1) {
	                    bottom = start;
	                }
	            }
	            top++;
	        }   
	    }
	    
	    protected void display() {
	        System.out.printf(type + "    ");
	        
	        for (int k = start; k <= end; k++) { // Prints out the Top Header
	            System.out.printf("%5d", k);
	        }
	        System.out.println();
	        System.out.printf("     ");
	        for (int l = start; l <= end; l++) { // Prints out the dashes below the Top Header
	            System.out.printf("%5s", "-----");
	        }

	        System.out.println();
	        for (int n = 0; n < protoTable.length; n++) { // Prints out the left side Header
	            int sideNum = n + start;
	            System.out.printf("%-3d |", sideNum);

	            for (int o = 0; o < protoTable.length; o++) { //Prints out the solution line by line
	                System.out.printf("%5.0f", protoTable[o][n]);
	            }
	            System.out.println();
	        }
	    }
}
