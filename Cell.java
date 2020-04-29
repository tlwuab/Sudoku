import java.util.Stack;

public class Cell implements Validator {

    private int value;
    private boolean valid;
    private boolean given;
    private Row row;
    private Column column;
    private Subregion subregion;  // Store row, column and subregion data for processing easily later on
    private boolean[] pencilMarking;

    public Cell(int v, boolean g) {
        value = v;    // Initialize value with v
        given = g;    // Initialize given with g
        valid = true; // Initialize valid to true
        pencilMarking = new boolean[10]; // Create an array of booleans
    }

    public void setValue(int v) {
        value = v;    // Assign v to value
    }

    public void setGiven(boolean g) {
        given = g;    // Assign g to given
    }

    private void update() {
        valid = true;
        valid = valid && row.isValid();
        valid = valid && column.isValid();
        valid = valid && subregion.isValid();
    }

    public Stack<Integer> getMarkingStack() {
        Stack<Integer> s = new Stack<>();
        boolean[] b = this.getPencilMarking();
     
        for (int i = 1; i < 10; i++) {
            if (b[i]) {
                s.push(i);
            }
        }
        return s;
    }

    public boolean[] getPencilMarking() {
        // --- Part A ----
		
		// Description:
		// This method returns a boolean array pencilMarking, where
		// pencilMarking[i] is true if all the corresponding row, column, 
		// and subregion do not contain i.
		
		// Hint: Use markings method of Row, Column and Subregion class.


        for(int i=1;i<=9;i++)
            if((row.markings()[i])&&(column.markings()[i])&&(subregion.markings()[i]))
                pencilMarking[i] = true;
            else
                pencilMarking[i] = false;

        return pencilMarking;
    }

    public boolean conflict(int i) {
		// --- Part A ----
		
		// Description:
		// This method returns true if i has conflict with its
		// row, column, or subregion.
		
		// Hint: Use conflict method of Row, Column and Subregion class.

        if(row.conflict(i)||column.conflict(i)||subregion.conflict(i))
            return true;
        else
            return false;

    }

    @Override
    public boolean isValid() {
        update();
        return valid;
    }
    
	public boolean getGiven() {
		return this.given;
	}
	
    public void setRow(Row r) {
        row = r;
    }

    public void setColumn(Column c) {
        column = c;
    }

    public void setSubregion(Subregion s) {
        subregion = s;
    }

    public int getValue() {
        return value;
    }
}
