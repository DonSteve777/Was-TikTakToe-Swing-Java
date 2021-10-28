package es.ucm.fdi.tp.view;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.view.GameWindow.PlayerMode;


public class GameController<S extends GameState<S,A>, A extends GameAction<S,A>> 
{

	protected GameTable<S,A> game;
	
	public GameController(GameTable<S,A> game) 
	{
		this.game = game;
	}
	
	
	
	public void makeMove(A action)
	{
		try{
			game.execute(action);
			
		} catch(GameError e)
		{
			
		}
		
	}
	
	public void stop()
	{
		game.stop();
	}
	
	public void gameStart()
	{
		game.start();
	}
	
	public void run()
	{
		this.gameStart();
	}
	
	
	public void makeRandomMove(final RandomPlayer rp)
	{
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() 
			{
				GameController.this.makeMove(rp.requestAction(GameController.this.game.getState()));
			}
			
		});
		
	}
	
	public void makeSmartMove(final SmartPlayer sp)
	{
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() 
			{
				GameController.this.makeMove(sp.requestAction(GameController.this.game.getState()));
			}
			
		});
	}
	
}
