package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;

public class GameWindow<S extends GameState<S, A>, A extends GameAction<S, A>> extends JFrame
		implements GameObserver<S, A> {
	enum PlayerMode {
		MANUAL("manual"), AI("smart"), RANDOM("random");

		private String name;

		PlayerMode(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	RandomPlayer randPlayer;
	SmartPlayer smartPlayer;
	PlayerMode playerMode;

	private GameView<S, A> view;
	private GameController<S, A> controller;
	private int player;
	private EastPanel eastPanel;
	private Settings toolbar;

	public GameWindow(String gameName, GameController<S, A> controller, GameView<S, A> view, RandomPlayer rp,
			SmartPlayer sp, GameTable<S, A> game, int player) {
		super(gameName + " (view for player " + player + ")");
		this.view = view;
		this.controller = controller;
		this.view.setController(controller);
		this.randPlayer = rp;
		this.smartPlayer = sp;
		this.playerMode = PlayerMode.MANUAL;
		game.addObserver(this);
		this.player = player;
		initGUI();
	}

	private void initGUI() 
	{
		JPanel mainPanel = new JPanel(new BorderLayout(5,5));
		JPanel settingsPanel = new JPanel();
		toolbar = new Settings(this.controller, this.smartPlayer, this.randPlayer, this.player);
		eastPanel = new EastPanel();
		this.view.setEastPanel(eastPanel);
		settingsPanel.add(toolbar);
		
		mainPanel.add(eastPanel, BorderLayout.EAST);
		mainPanel.add(settingsPanel, BorderLayout.NORTH);
		mainPanel.add(view, BorderLayout.CENTER);
		
		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		this.setResizable(true);
		this.setVisible(true);

	}

	private void playerModeSelected(GameEvent<S, A> e) 
	{
		boolean gameFinished = e.getState().isFinished();
		boolean isPlayerTurn = e.getState().getTurn() == player;

		switch (playerMode) 
		{
		case RANDOM:

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					try {
						if (!gameFinished && isPlayerTurn) {
							Thread.sleep(100);
							GameWindow.this.controller.makeRandomMove(randPlayer);
						}

					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			});
			break;

		case AI:

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					try {
						if (!gameFinished && isPlayerTurn) {
							Thread.sleep(100);
							GameWindow.this.controller.makeSmartMove(smartPlayer);
						}

					} catch (InterruptedException e) {

						e.printStackTrace();
					}

				}

			});
			break;

		default:
			break;
		}
	}

	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				handleEvent(e);
			}

		});
	}

	public void handleEvent(GameEvent<S, A> e) 
	{

		switch (e.getType()) 
		{
		case Change:

			view.update(e.getState());
			playerMode = GameWindow.this.toolbar.getPlayerMode();

			if (e.getState().getTurn() == GameWindow.this.player) 
			{
				if (GameWindow.this.toolbar.getPlayerMode() == PlayerMode.MANUAL) 
				{
					view.enable();
					toolbar.enable();
				} 
				else 
				{
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							GameWindow.this.playerModeSelected(e);

						}

					});
				}

			} 
			else 
			{
				view.disable();
				toolbar.disable();
			}

			if (e.getState().getTurn() == GameWindow.this.player)
				GameWindow.this.eastPanel.writeMessage("Your turn");
			else
				GameWindow.this.eastPanel.writeMessage("Turn for player " + e.getState().getTurn());

			break;

		case Start:

			view.update(e.getState());
			playerMode = GameWindow.this.toolbar.getPlayerMode();

			if (e.getState().getTurn() == GameWindow.this.player) 
			{
				if (GameWindow.this.toolbar.getPlayerMode() == PlayerMode.MANUAL) 
				{
					view.enable();
					toolbar.enable();
				} 
				else 
				{
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() 
						{
							GameWindow.this.playerModeSelected(e);
						}

					});
				}

			}

			else 
			{
				view.disable();
				toolbar.disable();
			}

			GameWindow.this.eastPanel.cleanStatusMessages();

			if (e.getState().getTurn() == GameWindow.this.player)
				GameWindow.this.eastPanel.writeMessage("Your turn");
			else
				GameWindow.this.eastPanel.writeMessage("Turn for player " + e.getState().getTurn());

			break;

		case Stop:
			if (e.getState().getWinner() != -1)
				GameWindow.this.eastPanel.writeMessage("The winner is the player " + e.getState().getWinner());
			else
				GameWindow.this.eastPanel.writeMessage("There is a DRAW.");
			view.disable();
			break;

		case Error:
			if (e.getState().getTurn() == GameWindow.this.player)
				GameWindow.this.eastPanel.writeMessage("Push on a valid cell");
			break;

		case Info:

			break;

		}

	}
}
