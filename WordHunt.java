import java.util.Random;
import java.io.File;
import java.io.FileInputStream; // inputs data
import java.io.FileNotFoundException;
import java.io.FileOutputStream; // outputs data
import java.io.PrintWriter; // allows the user to write things to output
import java.io.IOException; // exception catcher

public class WordHunt {
	private String[][] grid; // array
	private String[] list;
	private int rowLength; // row length
	private int columnLength; // column length
	private int colStart; // start pos for col
	private int rowStart; // start pos for row
	
		
	public WordHunt(String[] list) // constructor to create the grid - done
	{
		this.list = list;
		grid = new String[20][20]; // sizeable 2D array so that there are no errors with potential cramming for ten words
		rowLength = grid[0].length;
		columnLength = grid.length;	
	}
	
	public String[][] getGrid()
	{
		return grid;
	}
	
	public void setUp() // makes the crossword puzzle
	{
		for(int i = 0; i < list.length; i++) // loops through list to set words 
		{	
			Random ran = new Random(); // creates a Random object to chose whether word is vert or hori
			int decide = ran.nextInt(2);
			
			if(decide == 0)
			{
				setHorizontal(list[i]);
			}
			else
			{
				setVertical(list[i]);
			}
			
		}
		setRandomLetter();
	}

	private void setRandomLetter() // sets up filler letters - done
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] == null)
				{	
				Random r = new Random();
				char c = (char)(r.nextInt(26) + 'a');
				grid[i][j] = String.valueOf(c);
				}
			}
		}
	}
	
	// finds valid horizontal pos
	private void findHorizontal(String word)
	{	
		boolean validOne = false; // cond for finding
		boolean validTwo =  false; // cond for checking
		do // the point of this do while 
		{
			// implementation finds to see if it is inbounds
			int randomRow = (int)(Math.random() * rowLength); // finds a random row
			int randomCol = (int)(Math.random() * columnLength); // finds a random column to start at
			if(randomCol + word.length() - 1 < grid.length) // check if col + word length is in bounds, since the word will span these columns
			{
				validOne = true; // in bounds
				rowStart = randomRow;
				colStart = randomCol;
			}
		
			// loop to see if there are any characters in the way
			for(int j = colStart; j < word.length(); j++)
			{
				if(grid[rowStart][j] == null) // checks for empty spot
				{
					validTwo = true; 
				}
				else
				{
					validTwo = false;
					break;
				}
			}
		}while(!(validOne && validTwo));
	}
	
	public void setHorizontal(String word) // prints the word into the array
	{
		findHorizontal(word);
		for(int i = 0; i < word.length(); i++)
		{
			grid[rowStart][colStart] = word.substring(i, i + 1);
			colStart++;
		}
	}
	
	
	// finds valid vertical pos
	private void findVertical(String word)
	{
		boolean validOne = false; // cond for finding
		boolean validTwo =  false; // cond for checking
		do // the point of this do while 
		{
			// implementation finds to see if it is inbounds
			int randomRow = (int)(Math.random() * rowLength); // finds a random row
			int randomCol = (int)(Math.random() * columnLength); // finds a random column to start at
			if(randomRow + word.length() - 1 < grid[0].length) // check if col + word length is in bounds, since the word will span these columns
			{
				validOne = true; // in bounds
				rowStart = randomRow;
				colStart = randomCol;
			}
			
			// loop to see if there are any characters in the way
			for(int j = rowStart; j < word.length(); j++)
			{
				if(grid[j][colStart] == null) // checks for empty spot
				{
					validTwo = true; 
				}
				else
				{
					validTwo = false;
					break;
				}
			}
		}while(!(validOne && validTwo));
	}
	
	public void setVertical(String word) // sets the word vertically
	{
		findVertical(word);
		for(int i = 0; i < word.length(); i++)
		{
			grid[rowStart][colStart] = word.substring(i, i + 1);
			rowStart++;
		}
	}
	
	// throws IO in case of file errors
	public void printFile() throws IOException // copies the array to the file, adds a space in between for visual clarity + println
	{
		File file = new File("word_hunt.txt"); // creates the file
		
		FileOutputStream outputFile = null;
		try {
			outputFile = new FileOutputStream(file, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		PrintWriter writer = new PrintWriter(outputFile); // var to write to the file
		
		// loop through the 2d array so that writer copies it to the .txt file
		for(int i = 0; i < grid.length; i++)
		{
			for(int k = 0; k < grid[0].length - 1; k++)
			{
				writer.print(grid[i][k] + " ");
			}
			writer.print(grid[i][grid[0].length - 1]);
			writer.println();
		}
		writer.flush(); // makes sure everything is printed from the writer
		outputFile.close(); // closes so there is no more things being written
		
		System.out.println("Crossword completed.");
	}
}
