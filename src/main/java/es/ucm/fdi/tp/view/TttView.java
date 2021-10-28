package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WasState;


public class TttView extends RectBoardGameView<TttState, TttAction> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1611274208601330620L;
	private static final int DIM = 3;
	private static int numOfPlayer = 0;
	private int player;
	
	public TttView() 
	{
		createBoardData(DIM, DIM);
		this.add(boardComp);
		this.enabled = true;
		this.player = numOfPlayer;
		++numOfPlayer;
	}
	
	@Override
	public void createBoardData(int numRows, int numCols) 
	{
		this.numOfCols = numCols;
		this.numOfRows = numRows;
		board = new Integer[numOfRows][numOfCols];
		
		// Initizalize an empty board
		for (int i = 0; i < DIM; ++i) 
			for (int j = 0; j < DIM; ++j) 
				board[i][j] = null;
	}
	
	@Override
	public void mouseClicked(int row, int col, int clickCount, int mouseButton) 
	{
		if(enabled && board[row][col] == null)
		{
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() 
				{
					TttView.this.controller.makeMove(new TttAction(player, row, col));	
				}
			});	
		}	
	}

	

	@Override
	public void update(TttState state) 
	{
		int[][] stateBoard = state.getBoard();
		//Si el tablero es vacío poner el board a null
		for(int row = 0; row < this.board.length; ++row)
			for (int col = 0; col < this.board[row].length; col++) 
				this.board[row][col] = ( stateBoard[row][col] == -1 ? null : stateBoard[row][col]);
		
		this.boardComp.repaint();
	}	
	
	protected void keyTyped(int keyCode)
	{
		
	}
	
	public int getNumRows() {
		return this.numOfRows;
	}
	
	public int getNumCols() {
		return this.numOfCols;
	}
}
