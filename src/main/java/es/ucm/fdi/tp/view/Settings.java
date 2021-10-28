package es.ucm.fdi.tp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.view.GameWindow.PlayerMode;

public class Settings extends JToolBar
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5956206383243867668L;
	
	private JButton randomMoveButton;
	private JButton smartMoveButton;
	private JButton restartButton;
	private JButton exitButton;
	private JComboBox<PlayerMode> playerSelector;
	
	private GameController<?,?> controller;
	private RandomPlayer randPlayer;
	private SmartPlayer smartPlayer;
	private int player;
	
	public Settings(GameController<?,?> c, SmartPlayer sp, RandomPlayer rp, int player) 
	{
		this.controller = c;
		this.randPlayer = rp;
		this.smartPlayer = sp;
		this.player = player;
		initGUI();
		
	}

	private void initGUI() 
	{
		//RANDOM MOVE BUTTON
		this.randomMoveButton = new JButton();
		this.randomMoveButton.setToolTipText("Random move");
		this.randomMoveButton.setIcon(new ImageIcon("src/main/resources/dice.png"));
        this.add(randomMoveButton);
        
        
        this.randomMoveButton.addActionListener(new ActionListener()
        {
        	@Override
			public void actionPerformed(ActionEvent e) 
			{
				controller.makeRandomMove(Settings.this.randPlayer);
			}
        });
        
        //SMART MOVE BUTTON
		this.smartMoveButton = new JButton();
		this.smartMoveButton.setToolTipText("Smart move");
		this.smartMoveButton.setIcon(new ImageIcon("src/main/resources/nerd.png"));
        this.add(smartMoveButton);
        
        this.smartMoveButton.addActionListener(new ActionListener()
        {
        	@Override
			public void actionPerformed(ActionEvent e) 
			{
				controller.makeSmartMove(Settings.this.smartPlayer);
			}
        });
		
        //RESTART BUTTON
		this.restartButton = new JButton();
		this.restartButton.setToolTipText("Restart");
        this.restartButton.setIcon(new ImageIcon("src/main/resources/restart.png"));
        this.add(restartButton);
        this.restartButton.addActionListener(new ActionListener()
        {
        	@Override
			public void actionPerformed(ActionEvent e) 
			{
				controller.gameStart();
			}
        });
		
        //EXIT BUTTON
		this.exitButton = new JButton();
		this.exitButton.setToolTipText("Exit");
        this.exitButton.setIcon(new ImageIcon("src/main/resources/exit.png"));
        this.add(exitButton);
        this.exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String message = "Do you want to exit?";
				String title = "Exit";
				int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION)
					System.exit(0);
				
			}
		});
		
        this.add(new JLabel(" Player Mode:"));
		
        this.playerSelector = new JComboBox<PlayerMode>(new DefaultComboBoxModel<PlayerMode>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1417506784694074663L;

			@Override
			public void setSelectedItem(Object o)
			{
				super.setSelectedItem((PlayerMode) o);
				
				//COMPROBAR SI ES EL TURNO DEL JUGADOR
				//¿INVOCAR AL METODO QUE DISTINGUE entre playerMode DE GAMEWINDOW?
				//Si llamo a los metodos del controlador no se controla si es el turno del jugador o si el juego ha acabado
			}
			
		});
		
		playerSelector.addItem(PlayerMode.MANUAL);
		playerSelector.addItem(PlayerMode.RANDOM);
		playerSelector.addItem(PlayerMode.AI);
		
		this.add(playerSelector);
	}
	
	@Override
	public void disable()
	{
		this.randomMoveButton.setEnabled(false);
		this.smartMoveButton.setEnabled(false);	
	}
	
	@Override
	public void enable()
	{
		this.randomMoveButton.setEnabled(true);
		this.smartMoveButton.setEnabled(true);
	}
	
	public PlayerMode getPlayerMode()
	{
		return (PlayerMode) this.playerSelector.getSelectedItem();
	}
	
}
