package es.ucm.fdi.tp.was;

import java.util.ArrayList;

public enum SheepMovements {
	
	DOWNANDRIGHT(1, 1), DOWNANDLEFT(1, -1);
	
	private int row;
	private int col;
	private final static int NUMBER_OF_SHEEPS = 4;
	
	private SheepMovements(int r, int c)
	{
		this.row = r;
		this.col = c;
	}
	
	/**
	 * Returns a list with the valid movements for the sheeps
	 * @param board bidimensional array containing the board with the last movement
	 * @param sheepNumber number corresponding the player number of the sheeps
	 * @return the list with the valid movements for the sheeps
	 */
	public static ArrayList<WasAction> validActions(int sheepNumber, int[][] board)
	{
		int sheepsCounter = 0;
		ArrayList<WasAction> movementsList = new ArrayList<WasAction>();
		
		
		 //Search all the sheeps in the board
		for (int row = 0; row < WasState.getDim() && sheepsCounter < NUMBER_OF_SHEEPS; row++) 
		{
			for (int col = 0; col < WasState.getDim() && sheepsCounter < NUMBER_OF_SHEEPS; col++) 
			{
				if (board[row][col] == sheepNumber) // If there is a sheep in the cell
				{
					for(SheepMovements movement : SheepMovements.values()) // Evaluate all sheep movements
					{
						if (WasState.isValidCell(row + movement.row, col + movement.col, board)) // If sheep movement is valid
						{
							movementsList.add(new WasAction(sheepNumber, row, col, row + movement.row, col + movement.col)); // Add movement
						}
					}
					
					sheepsCounter++;
				}
			}
		}
		return movementsList;
	}
}
