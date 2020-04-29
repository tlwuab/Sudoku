// Part A

// Create the Row class according to the details given in the UML class diagram.
// i.e., https://course.cse.ust.hk/comp1022p/assignments/pa2/image/uml.jpg

// The class is given in FAQ 4.

public class Row extends Subset { 
    public Row(Cell[][] matrix, int row) {
        super();                   // Call the constructor of the superclass (Subset)
        c = new Cell[9];           // Create an array of 9 Cell objects and reference it by c
        for (int i = 0; i < 9; i++) {
            c[i] = matrix[i][row]; // Copy matrix data to each cell c[i]
			                       // ---- Note ----
								   // The first subscript of matrix refers to column number (x), and
								   // the second subscript of matrix refers to the row number (y)
            c[i].setRow(this);     // Set the constructing object to the instance variable "row" of c[i]
        }       
    }
}