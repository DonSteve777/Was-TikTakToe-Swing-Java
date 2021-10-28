package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jboard.JBoard.Shape;
import es.ucm.fdi.tp.extra.jcolor.ColorChooser;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.was.WasAction;
import es.ucm.fdi.tp.was.WasState;

public class WasView extends RectBoardGameView<WasState, WasAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int SHEEP = 1;
	private static final int WOLF = 0;
	private static final Integer EMPTY = null;
	private static final int DIM = 8;
	private static int numOfPlayer = 0;
	private int player;
	private boolean firstClickReceived;

	private int sourceRow = -1;
	private int sourceCol = -1;
	private int destinyRow;
	private int destinyCol;

	public WasView() 
	{
		createBoardData(DIM, DIM);
		
		this.add(boardComp);
		this.enabled = true;
		this.player = (numOfPlayer == 0 ? WOLF : SHEEP);
		++numOfPlayer;
	}

	@Override
	public void createBoardData(int numOfRows, int numOfCols) {

		this.numOfRows = numOfRows;
		this.numOfCols = numOfCols;
		board = new Integer[numOfRows][];

		// Initizalize an empty board
		for (int i = 0; i < DIM; ++i)
		{
			board[i] = new Integer[this.numOfCols];
			for (int j = 0; j < DIM; ++j)
				board[i][j] = EMPTY;
		}
			

		// Place the sheeps in the first row
		for (int k = 1; k < DIM; k += 2)
			board[0][k] = SHEEP;

		board[DIM - 1][0] = WOLF;
	}
	
	
	@Override
	protected void keyTyped(int keyCode)
	{
		if(firstClickReceived && keyCode == KeyEvent.VK_ESCAPE)
		{
			firstClickReceived = false;
			
			this.eastPanel.writeMessage("Selection canceled");
			this.eastPanel.writeMessage("Click on source cell");
		}
	}
	

	@Override
	public void mouseClicked(int row, int col, int clickCount, int mouseButton) 
	{
		if(enabled)
		{
			if(!firstClickReceived)
			{
				sourceRow = row;
				sourceCol = col;
				firstClickReceived = true; 
				this.eastPanel.writeMessage("Selected (" + row + "," + col + ")");
			}
			else
			{
				WasAction action = new WasAction(player, WasView.this.sourceRow, WasView.this.sourceCol,  row, col);
				boolean actionIsValid = this.state.validActions(this.state.getTurn()).contains(action);
			
				if(actionIsValid)
				{
					SwingUtilities.invokeLater(new Runnable(){
						
						@Override
						public void run() 
						{
							WasView.this.controller.makeMove(action);
						}
					});
					
					firstClickReceived = false;
				}
			}
		}
	}

	@Override
	public void update(WasState state) 
	{
		this.state = state;
		int[][] stateBoard = state.getBoard();
		// Si el tablero es vacío poner el board a null
		for (int row = 0; row < this.board.length; ++row)
			for (int col = 0; col < this.board[row].length; col++)
				this.board[row][col] = (stateBoard[row][col] == -1 ?  null : stateBoard[row][col]);
		
		this.boardComp.repaint();
	}
	
	public int getNumRows() {
		return this.numOfRows;
	}
	
	public int getNumCols() {
		return this.numOfCols;
	}
}
