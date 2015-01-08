import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ProgramII {
	public static void main(String[] args) {
		int[][] sudoku = new int[9][9]; //creates new 2 dimensional array called sudoku
		Scanner sc = null; //sets scanner to null
		try {
			int i = 0, j = 0, count = 1;
			
			//sc = new Scanner(new File(args[0])); //validsudoku1.txt
			sc = new Scanner(new File("validsudoku1.txt")); //validsudoku1.txt
			while (sc.hasNextInt()) {	//while there is an integer
				if ((count % 9) == 0) {
					sudoku[i][j] = sc.nextInt();
					count++;
					i++;
					j = 0;
				} else {
					sudoku[i][j++] = sc.nextInt();
					count++;
				}	
			}
			//sc.close();
		}	
		catch (FileNotFoundException fnfe) { //catch File not Found Exception, print, and exit
			System.out.println("No file found");
			System.exit(1);
		}
		finally { // finally close the file
			sc.close();
		}
		if(puzzleIsValid(sudoku)){ //if the puzzle is valid then it prints it is valid
			System.out.println("Sudoku is Valid");
			printArray(sudoku);
		}else{ //else it prints that it is not valid
			System.out.println("Sudoku is Invalid");
		}
		
	}
	/**
	Prints any array
	@param array
	@return none
	*/
	public static void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++){
				System.out.print(array[i][j]+ " "); //for all the elements in the array print them
			}
			System.out.println(); //after every row print a ln
		}
	}
	/**
	Determines if all rows are valid
	@param array
	@return true if rows are valid
	*/
	public static boolean rowsAreValid(int[][] array) {
		for (int i = 0; i < array.length; i++) { 
			for (int j = 0; j < array[i].length; j++) { //for every row and column of the array
				for (int column = 0; column < array.length; column++){ //check the values of every row
					if (column != j && array[i][column] == array[i][j]){ //if there is a duplicate return false
						return false;
					}
				}
			}
		}
		return true;
	}
	/**
	Determines if all columns are valid
	@param array
	@return true if columns are valid
	*/
	public static boolean columnsAreValid(int[][] array) {
		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array.length; i++) { //for every column and row of the array
				for (int row = 0; row < array.length; row++){ //check the values of every column
					if (row != i && array[row][j] == array[i][j]){ //if there is a duplicate return false
						return false;
					}
				}
			}
		}
		return true;
	}
	/**
	Determines if all regions are valid
	@param array
	@return true if all regions are valid
	*/
	public static boolean regionsAreValid(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) { //for the elements in the array
				for(int k = (i/3)*3; k < (i/3)*3+ 3; k++) {
					for (int l = (j/3)*3; l < (j/3)*3 + 3;l++){//for the regions that are multiples of 3
						if((k != i && array[k][j] == array[i][j]) 
							|| (l != j && array[i][l] == array[i][j]) 
							|| (k!= i && l!= j && array[k][l]==array[i][j])){ //if there is a duplicate in the region return false
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	/**
	Determines if puzzle is valid
	@param array
	@return true if rows, columns, and regions are all vaid
	*/
	public static boolean puzzleIsValid(int [][] array) {
		if(rowsAreValid(array) && columnsAreValid(array) && regionsAreValid(array)){ //if rows are valid and columns and regions are valid then return true
			return true;
		}
		return false;
	}
}
		

