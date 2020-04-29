import javax.print.attribute.standard.PrinterResolution;
import java.io.*;
import java.util.Scanner;

public class IO {
    private FileWriter out;
    private PrintWriter bOutput;
    private File file;

    public IO() throws IOException {

    }

    public void write(String address, Board b) throws IOException {
		
		// Description:
		// This method is for writing the game data to a file (i.e., Export function).
		// There are three parts of Sudoku that you need to save:
		// 1. value
		// 2. solution
		// 3. given
		
		// Hint:
		// Use getBoard(), getValue(), getSolution(), and getGiven() methods

        file = new File(address);
        bOutput = new PrintWriter(file);

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){
                bOutput.print(b.getBoard()[i][j].getValue());
                bOutput.print("\n");
            }

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){
                bOutput.print(b.getSolution()[i][j]);
                bOutput.print("\n");
            }

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){
                bOutput.print(b.getBoard()[i][j].getGiven());
                bOutput.print("\n");
            }

        bOutput.close();
    }

    public Board read(String address) throws IOException {
		
		// Description:
		// This method is for reading the game data from a file (i.e., Import function).
		// Read all the data you have saved, i.e., value, solution, and given,
		// and construct a Board object using constructor of Board.

        // This method returns the constructed Board object.

        file = new File(address);
        Scanner sc = new Scanner(file);

        int[][] value = new int[9][9];
        int[][] solution = new int[9][9];
        boolean[][] given = new boolean[9][9];

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                value[i][j] = sc.nextInt();

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                solution[i][j] = sc.nextInt();

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                given[i][j] = sc.nextBoolean();

        Board bData = new Board(value,solution,given);

        sc.close();

        return bData;
    }
}
