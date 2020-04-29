public class Subset implements Validator {
    protected Cell[] c;

    public Subset() {
        c = new Cell[9];
    }


    @Override
    public boolean isValid() {
        // --- Part A ---

        // Description:
        // This method returns true if the subset (represented by c)
        // contains 1-9 exactly once.

        boolean result = true;

        for (int i = 0; i < 9; i++)
            for (int j = i + 1; j < 9; j++)
                if (c[i].getValue() - c[j].getValue() == 0)
                    result = false;

        return result;
    }

    public boolean[] markings() {
		// --- Part A ---
		
		// Description: 
		// This method returns a boolean array of size 10, say a, where
		// a[i] is true if the subset does not contain i.
		
		// Explanation:
		// a[i] represents whether number represented by index i can be marked.
		// e.g., if a[2] is true, then it means the number 2 can be marked
		// by the player.

        boolean[] a = new boolean[10];

        for(int i=0;i<10;i++)
            for(int j=0;j<9;j++)
                if(i==c[j].getValue()){
                    a[i] = false;
                    break;
                }
                else
                    a[i] = true;

        return a;
    }

    public boolean conflict(int n) {
        // --- Part A ---
		
		// Description:
		// This method returns true if the subset (represented by c) contains n.
        boolean result = false;

        for(int i=0;i<9;i++)
            if(n==c[i].getValue())
                result = true;

        return result;
    }

}
