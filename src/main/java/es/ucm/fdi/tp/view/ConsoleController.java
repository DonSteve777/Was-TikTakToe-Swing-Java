package es.ucm.fdi.tp.view;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;

public class ConsoleController<S extends GameState<S,A>, A extends GameAction<S, A>> extends GameController<S,A> 
{

	private List<GamePlayer> players;
	private boolean stopped;
	
	public ConsoleController(List<GamePlayer> players, GameTable<S,A> game) 
	{
		super(game);
		this.players = players;
		this.stopped = true;
	}

	@Override
	public void run() 
	{
		this.stopped = false;
		
		int playerCount = 0;
		for (GamePlayer p : players) 
			p.join(playerCount++); // welcome each player, and assign
									// playerNumber
		this.gameStart(); //begin game
		
		try
		{
			while (!game.getState().isFinished()) 
			{
				// request move
				A action = players.get(game.getState().getTurn()).requestAction(game.getState());
				
				this.makeMove(action);
				
				if (game.getState().isFinished())// game over
					this.stop();	
			}
		} catch(GameError err){ 
			
		}
		this.stopped = true;
	}

}
