import java.util.Random;
import java.util.Scanner;
public class Fifteen{
    static int SIZE = 4;
    static int[][] board = new int[SIZE][SIZE];
    int emptyRow, emptyCol;
  
    public Fifteen() {
        initializeBoard();
        shuffleBoard();
    }
  
    public void initializeBoard() {
        int value = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = value++;
            }
        }
        board[SIZE - 1][SIZE - 1] = 0;
        emptyRow = SIZE - 1;
        emptyCol = SIZE - 1;
    }

    public void shuffleBoard() {
        Random rand = new Random();
        for (int i = 0; i < 32; i++) {
            int direction = rand.nextInt(4);
            switch (direction) {
                case 0:
                    if (emptyRow > 0) { 
                        swap(emptyRow, emptyCol, emptyRow - 1, emptyCol);
                        emptyRow--;
                    }
                    break;
                case 1:
                    if (emptyRow < SIZE - 1) { 
                        swap(emptyRow, emptyCol, emptyRow + 1, emptyCol);
                        emptyRow++;
                    }
                    break;
                case 2:
                    if (emptyCol > 0) { 
                        swap(emptyRow, emptyCol, emptyRow, emptyCol - 1);
                        emptyCol--;
                    }
                    break;
                case 3:
                    if (emptyCol < SIZE - 1) {
                        swap(emptyRow, emptyCol, emptyRow, emptyCol + 1);
                        emptyCol++;
                    }
                    break;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    System.out.print("   ");
                }
                else {
                    System.out.printf("%2d ", board[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean move(int num) {
        int numRow = -1;
        int numCol = -1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == num) {
                    numRow = i;
                    numCol = j;
                    break;
                }
            }
        }
        if (numRow == -1 || numCol == -1) {
            return false;
        }

        // Check if the selected number is adjacent to the empty space
        if ((Math.abs(numRow - emptyRow) == 1 && numCol == emptyCol) ||
            (Math.abs(numCol - emptyCol) == 1 && numRow == emptyRow)) {
            swap(numRow, numCol, emptyRow, emptyCol);
            emptyRow = numRow;
            emptyCol = numCol;
            return true;
        } 
        else {
            System.out.println("Invalid move, the number is not adjacent to the empty space.");
            return false;
        }
    }

    public boolean isSolved() {
        int value = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == SIZE - 1 && j == SIZE - 1) {
                    return board[i][j] == 0;
                }
                if (board[i][j] != value++) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void swap(int row1, int col1, int row2, int col2) {
        int temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fifteen puzzle = new Fifteen();
        System.out.println("Welcome to the 15 Puzzle Game!");
        puzzle.printBoard();

        while (!puzzle.isSolved()) {
            System.out.print("Enter the number to move (1-15): ");
            int number = sc.nextInt();

            if (puzzle.move(number)) {
                puzzle.printBoard();
            }
            else {
                System.out.println("Invalid move, try again.");
            }
        }
        System.out.println("Congratulations! You've solved the puzzle!");
        sc.close();
    }
}
