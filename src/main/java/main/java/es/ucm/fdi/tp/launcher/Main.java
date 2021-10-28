package main.java.es.ucm.fdi.tp.launcher;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.view.ConsoleController;
import es.ucm.fdi.tp.view.ConsoleView;
import es.ucm.fdi.tp.view.GameController;
import es.ucm.fdi.tp.view.GameView;
import es.ucm.fdi.tp.view.GameWindow;
import es.ucm.fdi.tp.view.RectBoardGameView;
import es.ucm.fdi.tp.view.TttView;
import es.ucm.fdi.tp.view.WasView;
import es.ucm.fdi.tp.was.WasAction;
import es.ucm.fdi.tp.was.WasState;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;

/**
 * Demo main, used to test game functionality. You can use it as an inspiration,
 * but we expect you to build your own main-class.
 */
public class Main
{
	private static Scanner sc = new Scanner(System.in);
	
	 /**
	 * Returns the initialState depending of the game received in gameName
	 * @param gameName name of the game chosen by the user
	 * @return the initial state of the game
	 */
	 public static GameState<?,?> createInitialState(String gameName)
	 {
		 return ( gameName.equalsIgnoreCase("WAS") ? new WasState() : new TttState(3) );	 
	 }
	 
	 /**
	  * Returns a player created for the game gameName
	  * @param gameName name of the game to be played
	  * @param playerType the kind of player to play, console, smart or random player
	  * @param playerName Name of the player
	  * @return a GamePlayer object 
	  */
	 public static GamePlayer createPlayer(String gameName, String playerType, String playerName)
	 {
		if(playerType.equalsIgnoreCase("MANUAL"))
			return new ConsolePlayer(playerName, Main.sc);
		
		else if(playerType.equalsIgnoreCase("RAND"))
			return new RandomPlayer(playerName);
		
		else if(playerType.equalsIgnoreCase("SMART"))
			return new SmartPlayer(playerName, 5);
		
		else 
			return null;
	 }


	private static GameTable<?, ?> createGame(String gameName) 
	{
		if(gameName.equalsIgnoreCase("WAS"))
			return new GameTable<WasState, WasAction>(new WasState());
		
		else if(gameName.equalsIgnoreCase("TTT"))
			return new GameTable<TttState, TttAction>(new TttState(3));
		
		else
			return null;
	}
	
	private static GameView<?,?> createGameView(String gameName)
	{
		if(gameName.equalsIgnoreCase("TTT"))
			return new TttView();
		
		else if(gameName.equalsIgnoreCase("WAS"))
			return new WasView();
		
		else
			return null;
	}
	
	private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startConsoleMode(String gameName, GameTable<S, A> game, String playerModes[]) 
	{
		List<GamePlayer> players = new ArrayList<GamePlayer>();
		int i = 0;
		try
		{
			for(i = 0; i < playerModes.length; ++i)
				players.add(Main.createPlayer(gameName, playerModes[i], "player " + i));
			
			new ConsoleView<S,A>(game);
			new ConsoleController<S,A>(players, game).run();
		} 
		catch(NullPointerException e)
		{
			usage();
			System.exit(1);
		}
	}
	
	
		private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startGUIMode(final String gameName, final GameTable<S, A> game, String playerModes[]) throws InvocationTargetException, InterruptedException 
		{
			final GameController<S,A> controller = new GameController<S,A>(game);
			
			for(int i = 0; i < playerModes.length; ++i)
			{
				final RandomPlayer randomPlayer = new RandomPlayer("Player - " + i);
				final SmartPlayer smartPlayer = new SmartPlayer("Player - " + i, 10);
				randomPlayer.join(i);
				smartPlayer.join(i);
				final int j = i;
				final GameView<?, ?> view = createGameView(gameName);
				view.setEnabled((i % 2 == 0 ? true : false));
				SwingUtilities.invokeAndWait(new Runnable() {
					
					@Override
					public void run() 
					{
						new GameWindow<S,A>(gameName, controller, (GameView<S,A>) view, randomPlayer, smartPlayer, game, j);
					}
				});
			}
			
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() 
				{
					controller.run();	
				}
			});
			
		}
		                                                  
		
		private static void usage() 
		{ 
			String sb = "Error in the number or the type of the arguments, type the following arguments: " + System.getProperty("line.separator") + System.getProperty("line.separator");
			sb += "game (was for wolf and sheep or ttt for tick tack toe) mode (console or gui) player1 player2 (manual, rand or smart)";
			System.out.println(sb);
		
		}
		
		public static void main(String[] args) throws InvocationTargetException, InterruptedException 
		{ 
			if (args.length < 4) 
			{
				usage();
				System.exit(1);
		     }
		     
			GameTable<?, ?> game = createGame(args[0]);
			
			if (game == null) 
			{ 
				System.err.println("Invalid game"); 
				usage();
				System.exit(1);
			}
			
		     String[] otherArgs = Arrays.copyOfRange(args, 2, args.length);
		     
		     switch (args[1]) 
		     { 
		     	case "console":
		     		startConsoleMode(args[0], game, otherArgs);
		     		break; 
		        
		     	case "gui":
		     		startGUIMode(args[0], game, otherArgs);
		     		break; 
		        
		        default:
		        	System.err.println("Invalid view mode: " + args[1]);
		        	usage();
		        	System.exit(1);
		     } 
		  }

	
}
