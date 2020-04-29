// --- Part A ---

// Create the Column class according to the details given in the UML class diagram.
// i.e., https://course.cse.ust.hk/comp1022p/assignments/pa2/image/uml.jpg

// The implementation of Column class should be very similar to the Row class.

public class Column extends Subset {
    public Column(Cell[][] matrix, int column){
        super();
        c = new Cell[9];
        for (int i = 0; i < 9; i++) {
            c[i] = matrix[column][i];



            c[i].setColumn(this);
        }
    }
}