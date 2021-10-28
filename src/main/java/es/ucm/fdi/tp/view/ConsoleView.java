package es.ucm.fdi.tp.view;



import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;

public class ConsoleView<S extends GameState<S,A>, A extends GameAction<S,A>> implements GameObserver<S,A> 
{

	//private ConsoleController<S,A> controller;
	
	public ConsoleView(GameObservable<S,A> gameTable) 
	{
		gameTable.addObserver(this);
	}
	
	

	@Override
	public void notifyEvent(GameEvent<S, A> e) 
	{
		switch (e.getType())
		{
		case Start:
		case Stop: 
		case Change:
			System.out.println(e);
			break;
		case Error: break;
		case Info: break;
		}
	}

}
