package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.extra.jboard.BoardExample;
import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jboard.JBoard.Shape;

public  abstract class GameView<S extends GameState<S,A>, A extends GameAction<S,A>> extends JPanel
{
	

	
	public abstract void enable();
	public abstract void disable();
	public abstract void update(S state);
	
	public abstract void setEastPanel(EastPanel eastPanel);
	public abstract void setController(GameController<S,A> gameController);
	
	public abstract int getNumCols();
	public abstract int getNumRows();
	
	public abstract void mouseClicked(int row, int col, int clickCount, int mouseButton); 
	public abstract void createBoardData(int numOfRows, int numOfCols);
}
