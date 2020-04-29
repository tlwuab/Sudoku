// --- Part A ---

// Create the Subregion class according to the details given in the UML class diagram.
// i.e., https://course.cse.ust.hk/comp1022p/assignments/pa2/image/uml.jpg

// The implementation of Subregion class should be very similar to the Row class.

public class Subregion extends Subset {
    public Subregion(Cell[][] matrix, int locX, int locY){
        super();
        c = new Cell[9];
        int i = 0;
        for(int j = 3*locY; j <= 3*locY+2; j++)
            for(int k = 3*locX; k <= 3*locX+2; k++){
                c[i] = matrix[j][k];


                c[i].setSubregion(this);
                i++;
            }
    }

}