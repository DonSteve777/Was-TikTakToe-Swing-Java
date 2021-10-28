package es.ucm.fdi.tp.mvc;

import java.util.ArrayList;
import java.util.Collection;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;

/**
 * An event-driven game engine.
 * Keeps a list of players and a state, and notifies observers
 * of any changes to the game.
 */
public class GameTable<S extends GameState<S, A>, A extends GameAction<S, A>> implements GameObservable<S, A> {

    // define fields here
	private S  initialState;
	private S currentState;
	private Collection<GameObserver<S,A>> observers;
	private boolean active;
	

    public GameTable(S initState) 
    {
        this.initialState = initState;
        this.currentState = initState;
        this.observers = new ArrayList<GameObserver<S,A>>();
        this.active = false;
    }
    
    public void start() 
    {
        this.currentState = this.initialState;
        this.active = true;
        this.notifyObservers(new GameEvent<S, A>(EventType.Start, null, this.initialState, null, "Game has started\n\n" + this.currentState.toString()));
    }
    
    public void stop() 
    {
    	if(this.active)
    	{
    		this.active = false;
    		String endText = "The game ended: ";
			int winner = currentState.getWinner();
			if (winner == -1) {
				endText += "draw!";
			} else {
				//endText += "player " + (winner + 1) + " (" + players.get(winner).getName() + ") won!";
				endText += "player " + (winner + 1) + " won!";
			}
    		this.notifyObservers(new GameEvent<S, A>(EventType.Stop, null, this.currentState, null, endText));
    	}
    }
    
    public void execute(A action) 
    {
    	boolean activeGame = this.active;
    	boolean gameFinished = this.currentState.isFinished();
    	boolean isPlayerTurn = action.getPlayerNumber() == this.currentState.getTurn();
    	
    		
    	if(activeGame && !gameFinished && isPlayerTurn) 
    	{
    		this.currentState = action.applyTo(this.currentState);
    		this.notifyObservers(new GameEvent<S, A>(EventType.Change, action, this.currentState, null, "After action:\n" + this.currentState));
    		
    	}
    	//Mostrar en el tablero las acciones válidas
    	else if(activeGame && !gameFinished && isPlayerTurn)
		{
    		this.notifyObservers(new GameEvent<S,A>(EventType.Info, null, this.currentState, null, "Click on one of the following cells."));
		}
		else 
    	{
    		GameError error = new GameError("An error has ocurred.");
    		this.notifyObservers(new GameEvent<S, A>(EventType.Error, null, this.currentState, error, "Game ended."));
    		throw error;
    	}
    	
    	if(this.currentState.isFinished())
			this.stop();
    }
    
    public S getState() 
    {
        return this.currentState;
    }

    public void addObserver(GameObserver<S, A> o) 
    {
        this.observers.add(o);
    }
    public void removeObserver(GameObserver<S, A> o) 
    {
       	this.observers.remove(o);
    }
    
    private void notifyObservers(GameEvent<S,A> event)
    {
    	for(GameObserver<S,A> o : observers)
    		o.notifyEvent(event);
    }
}
