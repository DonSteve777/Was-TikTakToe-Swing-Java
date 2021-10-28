package es.ucm.fdi.tp.was;

import es.ucm.fdi.tp.base.model.GameAction;

/**
 * An action for the Wolf and Sheeps game. An action represents a movement of any player in the board.
 */

public class WasAction implements GameAction<WasState, WasAction>
{
	private static final long serialVersionUID = 1L;
	
	private int player;
    private int originRow;
    private int originCol;
    private int destinyRow;
    private int destinyCol;

    /**
     * Initializes the player and its origin and destiny cells
     * @param player number of the player, 0 for wolf and 1 for sheeps
     * @param originRow the row where the player is before the action
     * @param originCol the column where the player is before the action
     * @param destinyRow the destiny row of the action
     * @param destinyCol the destiny column of the action
     */
    public WasAction(int player, int originRow, int originCol, int destinyRow, int destinyCol)
    {
        this.player = player;
        this.originRow = originRow;
        this.originCol = originCol;
        this.destinyRow = destinyRow;
        this.destinyCol = destinyCol;
    }

	@Override
	public int getPlayerNumber()
	{
		return player;
	}

	@Override
	public WasState applyTo(WasState state)
	{
		boolean playerFound = false;
		int row = 0;
		int col = 0;
		
		if (player != state.getTurn())
			throw new IllegalArgumentException("Not the turn of this player");
		
        // make move
        int[][] board = state.getBoard();
        while(row < WasState.getDim() && !playerFound)
        {
        	col = 0;
        	while(col < WasState.getDim() && !playerFound)
        	{
        		playerFound = board[row][col] == player;
        		++col;
        	}
        	++row;
        }
        
        board[this.originRow][this.originCol] = -1;
        board[this.destinyRow][this.destinyCol] = player;

        // update state
        WasState next;
        
        if (state.isWinner(board))
        	next = new WasState(state, board, true, state.getTurn());
        else
        	next = new WasState(state, board, false, -1);
        
        return next;
	}
	
    
    @Override
    /**
     * Sobreescrito para las pruebas con JUnit
     */
    public boolean equals(Object o)
    {
    	if(!(o instanceof WasAction))
    		return false;
    	else
    	{
    		return this.player == ((WasAction) o).player
    			&& this.originCol == ((WasAction) o).originCol 
    			&& this.originRow == ((WasAction) o).originRow
    			&& this.destinyCol == ((WasAction) o).destinyCol
    			&& this.destinyRow == ((WasAction) o).destinyRow;
    	}
    	
    }

    	/**
    	 * Returns the string representation of an action, including origin and destiny cells of the player
    	 */
    public String toString()
    {
        return "place (" + (this.originCol + 1) + ", " + (this.originRow + 1) + ") at (" + (this.destinyCol + 1) + ", " + (this.destinyRow + 1) + ")";
    }

}
