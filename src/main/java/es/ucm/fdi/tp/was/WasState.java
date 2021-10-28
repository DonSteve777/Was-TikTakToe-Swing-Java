package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;

/**
 * A WolfAndSheeps state.
 * Describes a board of WolfAndSheeps that is either being
 * played or is already finished.
 */

public class WasState extends GameState<WasState, WasAction> {

	private static final long serialVersionUID = 1L;
	private static final int DIM = 8;
	private static final int EMPTY = -1;
	private static final int SHEEP = 1;
	private static final int WOLF = 0;

	private final int turn;
	private final boolean finished;
	private final int[][] board;
	private final int winner;
	

	/**
	 * Iniitializes the board and sets the wolf as the initial player
	 */
	
	public WasState() {
		super(2);

		board = new int[DIM][];

		// Initizalize an empty board
		for (int i = 0; i < DIM; ++i) {
			board[i] = new int[DIM];
			for (int j = 0; j < DIM; ++j) {
				board[i][j] = EMPTY;
			}
		}

		// Place the sheeps in the board
		for (int k = 1; k < DIM; k += 2) {
			board[0][k] = SHEEP;
		}
		
		board[DIM - 1][0] = WOLF;

		this.turn = 0;
		this.winner = -1;
		this.finished = false;
	}

	/**
	 * Receives a previous state and shifts the turn to the other player
	 * 
	 * @param prev
	 *            previous state used to shift the turn of the player
	 * @param board
	 *            bidimensional array containing the game board
	 * @param finished
	 *            true if some of the players have won, false otherwise
	 * @param winner
	 *            -1 if there is no winner yet, 0 if the wolf have won and 1 if
	 *            the sheeps have won
	 */
	public WasState(WasState prev, int[][] board, boolean finished, int winner) {
		super(2);
		this.board = board;
		this.turn = (prev.turn + 1) % 2;
		this.finished = finished;
		this.winner = winner;
	}

	/**
	 * Initializes all the attributes of the class setting the wolf as the
	 * initial player. Used for the JUnit tests.
	 * @param finished
	 *            true if some of the players have won, false otherwise
	 * @param board 
	 * 			  bidimensional array containing the game board with the last movement
	 * @param winner
	 *            -1 if there is no winner yet, 0 if the wolf have won and 1 if
	 *            the sheeps have won
	 */
	public WasState(int[][] board, boolean finished, int winner)
	{
		super(2);
		this.board = board;
		this.turn = WOLF;
		this.finished = finished;
		this.winner = winner;
	}

	@Override
	public int getTurn()
	{
		return this.turn;
	}

	@Override
	public List<WasAction> validActions(int playerNumber)
	{
		ArrayList<WasAction> valid = new ArrayList<>();

		if (this.finished) 
			return valid;
		
		if (playerNumber == WOLF) 
			valid = WolfMovements.validActions(WOLF, this.board);
		else // If player is SHEEP
			valid = SheepMovements.validActions(SHEEP, this.board);
		
		return valid;
	}

	
	/**
	 * Checks if a cell is within the boundaries of the board
	 * 
	 * @param row
	 *            row of the cell
	 * @param col
	 *            col of the cell
	 * @param board
	 *            bidimensional array containing the game board
	 * @return true if the cell is within the boundaries of the board and it's
	 *         not empty, false otherwise
	 */
	public static boolean isValidCell(int row, int col, int[][] board)
	{
		return row >= 0 && row < DIM && col >= 0 && col < DIM && board[row][col] == EMPTY;
	}

	
	@Override
	public boolean isFinished() 
	{
		return this.finished;
	}

	@Override
	public int getWinner() 
	{
		return this.winner;
	}

	/**
	 * Makes a copy of the board in a local variable and returns that variable
	 * 
	 * @return a copy of the board
	 */
	public int[][] getBoard() 
	{
		int[][] copy = new int[board.length][];
		for (int i = 0; i < board.length; i++)
			copy[i] = board[i].clone();
		return copy;
	}

	/**
	 * Returns the value of the board at (row, col)
	 * @param row
	 *            the row to check
	 * @param col
	 *            the col to check
	 * @return value of the board at row and col
	 */
	public int at(int row, int col) 
	{
		return this.board[row][col];
	}

	/**
	 * Returns if the current player is winner or not
	 * 
	 * @param board
	 *            bidimensional array containing the game board
	 * @return true if the current player has won, false otherwise
	 */
	public boolean isWinner(int[][] board) 
	{
		if (this.turn == SHEEP) 
			return this.isSheepWinner(board); 
		else // WOLF case
			return this.isWolfWinner(board);
	}

	/**
	 * Returns if the player controlling the wolf has won
	 * 
	 * @param board
	 *            bidimensional array containing the game board
	 * @return true if the wolf player has won, false otherwise
	 */
	private boolean isWolfWinner(int[][] board) {
		int sheepRow = -1, wolfRow = -1;
		int row = 0, col = 0;
		boolean foundSheep = false, foundWolf = false, foundFirstSheep = false;

		// Search first sheep and wolf beginning by row 0
		while (row < DIM && (!foundFirstSheep || !foundWolf))
		{
			col = 0;
			while (col < DIM && (!foundFirstSheep || !foundWolf))
			{
				foundSheep = board[row][col] == SHEEP;
				foundWolf = board[row][col] == WOLF;

				if (foundSheep && !foundFirstSheep)
				{
					sheepRow = row;
					foundFirstSheep = true;
				}
					
				if (foundWolf)
					wolfRow = row;

				col++;
			}
			row++;
		}

		return wolfRow == 0 || wolfRow <= sheepRow || SheepMovements.validActions(SHEEP, board).size() == 0;
	}

	/**
	 * Returns if the player controlling the sheeps has won
	 * 
	 * @param board
	 *            bidimensional array containing the game board
	 * @return true if the sheeps player has won, false otherwise
	 */
	private boolean isSheepWinner(int[][] board) 
	{
		return WolfMovements.validActions(SHEEP, board).size() == 0;
	}

	/**
	 * Returns the string representation of the game board
	 * 
	 * @return the String containing the values of the board
	 */
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		// Draw numbers of columns
		sb.append("   ");
		for(int i = 0; i < DIM; i++)
			sb.append(i + 1 + " ");
		
		// Draw separator
		sb.append(System.getProperty("line.separator") + "  -");
		for(int j = 0; j < DIM; j++)
			sb.append("--");
		sb.append(System.getProperty("line.separator"));
		
		// Draw board
		for(int row = 0; row < DIM; ++row)
		{	
			// Draw numbers of rows
			sb.append( row + 1 + "|");
			
			// Draw
			for(int col = 0; col < DIM; ++col)
			{
				switch(this.board[row][col])
				{
					//If row + column is pair then the cell is red(a blank space), if it's odd then the cell is black(a dot)
					case EMPTY: sb.append( ((row + col) % 2 == 0 ? "  " : " .")); break;
					case SHEEP: sb.append(" S"); break;
					case WOLF: sb.append(" W"); break;
				}
				
			}
			sb.append(" |");
			sb.append(System.getProperty("line.separator"));
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @return board dimension
	 */
	public static int getDim()
	{
		return WasState.DIM;
	}
}
