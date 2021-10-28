package es.ucm.fdi.tp.was;

import java.util.ArrayList;

public enum WolfMovements {

	UPANDLEFT(-1, -1), UPANDRIGHT(-1, 1), DOWNANDLEFT(1, -1), DOWNANDRIGHT(1, 1);
	
	
	private int row;
	private int col;
	
	private WolfMovements(int r, int c)
	{
		this.row = r;
		this.col = c;
	}
	
	/**
	 * Returns a list with the valid movements for the wolf
	 * @param wolfNumber number corresponding the player number of the wolf
	 * @param board bidimensional array containing the board with the last movement
	 * @return the list with the vlaid movements for the wolf
	 */
	public static ArrayList<WasAction> validActions(int wolfNumber, int[][] board)
	{
		ArrayList<WasAction> movementsList = new ArrayList<WasAction>();
		int wolfRow = 0, wolfCol = 0;
		
		
		//Searh the player in the board
		for(int row = 0; row < WasState.getDim(); ++row)
		{
			for(int col = 0; col < WasState.getDim(); ++col)
			{
				if(board[row][col] == 0)
				{
					wolfRow = row;
					wolfCol = col;
				}
			}
		}
		
		for(WolfMovements movement : WolfMovements.values())
		{
			if(WasState.isValidCell(wolfRow + movement.row, wolfCol + movement.col, board))
				movementsList.add(new WasAction(wolfNumber, wolfRow, wolfCol, wolfRow + movement.row, wolfCol + movement.col));
		}
		
		return movementsList;
	}
}
