
public class SubtractionTable extends Table{

    
	public SubtractionTable(final int start, final int stop, final String type){
		super(start, stop, type);
	}
	
    public void createTable() {
        int top = super.getStart();
        int bottom = super.getStart();
        int createTableSize = super.getTableSize();
        protoTable = new float[createTableSize][createTableSize];
        for (int i = 0; i < createTableSize; i++) {
            for (int j = 0; j < createTableSize; j++) {
                protoTable[i][j] = ((top) - (bottom));
                bottom++;
                if (j == createTableSize - 1) {
                    bottom = super.getStart();
                }
            }
            top++;
       
}
    }}

