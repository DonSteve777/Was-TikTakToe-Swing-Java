package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import org.w3c.dom.css.Rect;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.extra.jboard.BoardExample;
import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jboard.JBoard.Shape;
import es.ucm.fdi.tp.mvc.GameObserver;

/**
 * Class to be extended by every class that uses a GUI view of a game
 * 
 * @author Sergio
 *
 * @param <S>
 *            represents the state of the game
 * @param <A>
 *            represents the action of a game
 */

public abstract class RectBoardGameView<S extends GameState<S, A>, A extends GameAction<S, A>> extends  GameView<S, A> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7441793300186406441L;
	
	protected GameController<S,A> controller;
	protected boolean enabled;
	protected int numOfCols;
	protected int numOfRows;
	protected Integer[][] board;
	protected JBoard boardComp;
	
	protected S state;
	
	protected EastPanel eastPanel;
	
	public RectBoardGameView() 
	{
		
		super();
		initGUI();
	}
	
	@SuppressWarnings("serial")
	private void initGUI()
	{
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		
		boardComp = new JBoard() {

			@Override
			protected void keyTyped(int keyCode) 
			{
				RectBoardGameView.this.keyTyped(keyCode);
			}

			@Override
			protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
				RectBoardGameView.this.mouseClicked(row, col, clickCount, mouseButton);
			}

			@Override
			protected Shape getShape(int player) {
				return Shape.CIRCLE;
			}

			@Override
			protected Color getColor(int player) 
			{
				if(RectBoardGameView.this.eastPanel != null)
					return RectBoardGameView.this.eastPanel.getPlayerColor(player);
				else
					return player == 0 ? Color.BLUE : Color.RED;
			}
			
			@Override
			protected Integer getPosition(int row, int col) {
				return RectBoardGameView.this.getPosition(row, col);
			}

			@Override
			protected Color getBackground(int row, int col) {
				return (row+col) % 2 == 0 ? Color.LIGHT_GRAY : Color.BLACK;
			}

			@Override
			protected int getNumRows() {
				return RectBoardGameView.this.getNumRows();
			}

			@Override
			protected int getNumCols() {
				return RectBoardGameView.this.getNumRows();
			}
			@Override
			protected int getSepPixels()
			{
				return 0;
			}

		};
		
		this.add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(boardComp, BorderLayout.CENTER);		
	}

	

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}
	
	protected Integer getPosition(int row, int col) {
		return this.board[row][col];
	}	
	
	
	public void setEastPanel(EastPanel eastPanel)
	{
		this.eastPanel = eastPanel;
	}


	public void setController(GameController<S, A> gameController) 
	{
		this.controller = gameController;
	}
	
	
	protected abstract void keyTyped(int keyCode); 
	public abstract void update(S state);
	public abstract void mouseClicked(int row, int col, int clickCount, int mouseButton);
	public abstract void createBoardData(int numOfRows, int numOfCols);
}
