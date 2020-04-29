import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JComponent;

public class Board {

    private Cell[][] board;
    private int[][] solution;
    private boolean[][] given;
    private int ran;

    public Board() {
        board = new Cell[9][9];
        given = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Cell(0, false);
                given[i][j] = true;
            }
        }
        for (int i = 0; i < 9; i++) {
            new Row(board, i);
            new Column(board, i);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                new Subregion(board, i, j);
            }
        }
        ran = 0;
    }

    public Board(int[][] values, int[][] solu, boolean[][] giv) {//constructor for the imported data
        this();
        given = giv;
        solution = solu;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j].setValue(values[i][j]);
                board[i][j].setGiven(given[i][j]);
            }
        }
    }

    public boolean solve() {
        // --- Part A ---

        // Description:
        // This method solves the Sudoku.
        // Please refer to the following pseudocode for details:
		/*
		  public boolean solve() {
            int k =0;
            count how many cell of the board[][] is empty, increment k for each cell that is empty
            if(solution != null) {
              write the value of solution[][] to board[][]
              return true;
            }
            reset ran to 0;
            if solution is not calculated (i.e. null)
              return solve(k);
          }
		*/

        int k = 0;

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (this.board[i][j].getValue() == 0)
                    k++;


        if (solution != null) {
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    board[i][j].setValue(solution[i][j]);
            return true;
        }

        ran = 0;

        return solve(k);
    }


    private boolean solve(int k) {
        // --- Part A ---
		
		// Description:
		// This is a helper method that will be called by solve() method.
		// Please refer to the following pseudocode for details:
		/*
		  private boolean solve(int k) {
            ran++; // to count how many times solve is ran in this session

            if solve ran more than 5000 times in this session
              return false  // aborting this session.

            if k==0,
              return true   // there are no empty cell

            int x=0
            int y=0

            Stack s = new Stack<>();

            initialize s such that the stack has a size > 10

            for each cell inside board[][] {
              if cell contains value 1..9 
                then continue;
              if the size of cell.getMarkingStack() is strictly less than size of s {
                x = location of cell in first dimension of board[][]
                y = location of cell in second dimension of board[][]
                s = cell.getMarkingStack()
              }
			}*/
        ran++;

        if(this.ran>5000)
            return false;

        if(k==0)
            return true;

        int x=0, y=0;

        Stack<Integer> s = new Stack<>();

        for(int i=0;i<=10;i++)
            s.push(0);

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){
                if(board[i][j].getValue()>=1&&board[i][j].getValue()<=9)
                    continue;
                if(board[i][j].getMarkingStack().size() < s.size()){
                    x = i;
                    y = j;
                    s = board[i][j].getMarkingStack();
                }
            }

		/*
            // This part of the code is to find out which cell inside the board[][] has the least number of possible value as input.

            while(s is not Empty) {
              possible value of cell of position x,y = pop the next value from s
              if the cell has conflict with the possible_value 
                then continue
              set cell to the value as the possible value
              reduce the counter of empty cell (k)

              recursive call to next layer of solve(int), that is: if(solve(k)){return true;}
              increment the counter of empty cell (k) as the possible_value failed to complete the sudoku in reasonable time
              reset cell of board[x][y] as empty, that is, value of cell = 0
            } //end of while loop
			
            return false  // since all possible values inside stack s of cell of board[x][y] failed, we cant solve this puzzle.
          }
		*/

		while(!s.empty()){
		    int possibleValue = s.pop();
		    if(board[x][y].conflict(possibleValue))
		        continue;
		    board[x][y].setValue(possibleValue);
		    k--;
		    if(solve(k)) {return true;}
		    else {
                k++;
                board[x][y].setValue(0);
            }
        }

		return false;

    }

    public Cell[][] getBoard() {
        return this.board;
    }

    public int[][] getSolution() {
        return this.solution;
    }

    public void generate() {
        // --- Part A ---
		
		// Description:
		// This method generates a random Sudoku puzzle.
		// Please refer to the following pseudocode for details:
		/*
		  public void generate() {
            for every cell inside board[][], 
              set its value to zero, and set its given state as true
              Random rand = new Random();
		
            int times = 13; // This number is arbitrary
	
            for 13 times,
              set k = 81-16; // 17 is the minimum number of clues such that sudoku gives a unique solution, k is the target empty cell on the board.
		
              solve() the board[][], if it cannot be solved then call generate() again. return;
		
              while the counter of required empty cell (k) is not zero {
                int i = rand.nextInt(9);
                int j = rand.nextInt(9);
                if the cell at i,j of board [][] is 0, which means, it is empty, then,
                  continue // because making an empty cell empty does not progress the mission of making a target number of cell empty
                set the cell value to 0, since we found a cell that is not 0
                reduce the counter of required empty cell
              }

            // the above code destruct partial of the board and rebuild it for 13 times

            now we rebuild it, if(!solve()){generate(); return;}
	
            int k = 81-40; // I want 41 cell filled
	
            initialize solution as a two dimension array of 9x9 size
	
            copy the value from board[][] to solution[][]
	
            while(k!=0) {
              int i = random from 0...8
              int j = random from 0...8
              if(cell at board[i][j] is empty) 
                continue;
              set the cell at board[i][j] to 0, which is, to make it empty
              set the state, "given", of the cell to become false, as it is empty and thus not given now.
              reduce the counter k by 1
            }
          }
		*/

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){
                board[i][j].setValue(0);
                board[i][j].setGiven(true);
            }

        Random rand = new Random();

        int times = 13;

        for(int m=0;m<times;m++){
            int k = 65;

            if(!solve()){generate(); return;}

            while(k!=0){
                int i = rand.nextInt(9);
                int j = rand.nextInt(9);

                if(board[i][j].getValue()==0)
                    continue;

                board[i][j].setValue(0);
                k--;
            }

        }

        // the above code destruct partial of the board and rebuild it for 13 times

        if(!solve()) { generate(); return; }

        int k = 41;

        solution = new int[9][9];

        for(int i=0;i<9;i++)
            for(int j =0;j<9;j++)
                solution[i][j] = board[i][j].getValue(); // The last bug!

        while(k!=0){
            int i = rand.nextInt(9);
            int j = rand.nextInt(9);
            if(board[i][j].getValue()==0)
                continue;
            board[i][j].setValue(0);
            board[i][j].setGiven(false);
            k--;
        }


    }

}
