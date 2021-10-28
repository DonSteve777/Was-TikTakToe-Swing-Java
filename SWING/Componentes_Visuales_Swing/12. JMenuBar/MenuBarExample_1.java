package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MenuBarExample_1 extends JFrame implements ActionListener, ItemListener {

	JTextArea whitebox;
	JCheckBoxMenuItem cake, sorbet;

	public MenuBarExample_1() {
		super("[=] JMenuBar [=]");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		whitebox = new JTextArea();
		whitebox.setEditable(false);
		whitebox.setLineWrap(true);
		whitebox.setWrapStyleWord(true);
		whitebox.setMinimumSize(new Dimension(300, 200));
		whitebox.setPreferredSize(new Dimension(300, 200));
		whitebox.setMaximumSize(new Dimension(300, 200));

		mainPanel.add(whitebox);

		mainPanel.setBackground(Color.white);
		mainPanel.setMinimumSize(new Dimension(300, 200));
		mainPanel.setPreferredSize(new Dimension(300, 200));
		mainPanel.setMaximumSize(new Dimension(300, 200));
		mainPanel.setOpaque(true);

		JMenuBar menu = createMenuBar();
		
		this.setContentPane(mainPanel);
		this.setJMenuBar(menu);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}


	public Container createContentPane() {
		whitebox = new JTextArea();
		whitebox.setEditable(false);
		whitebox.setLineWrap(true);
		whitebox.setWrapStyleWord(true);
		whitebox.setMinimumSize(new Dimension(300, 200));
		whitebox.setPreferredSize(new Dimension(300, 200));
		whitebox.setMaximumSize(new Dimension(300, 200));

		JPanel totalGUI = new JPanel();
		totalGUI.add(whitebox);

		totalGUI.setOpaque(true);
		return totalGUI;
	}

	public JMenuBar createMenuBar() {
		// Create the menu bar.
		JMenuBar menuBar = new JMenuBar();

		// Add a JMenu
		JMenu starter = new JMenu("Starters");
		menuBar.add(starter);

		// Now we want to fill the menu.
		// This has every type of widget in the MenuBar
		// and has Event Listeners attached to each.

		JMenuItem soup = new JMenuItem("Soup");
		soup.addActionListener(this);

		JMenu steak = new JMenu("Steak");

		JMenuItem rare = new JMenuItem("Rare");
		rare.addActionListener(this);
		JMenuItem welldone = new JMenuItem("Well Done");
		welldone.addActionListener(this);
		steak.add(rare);
		steak.add(welldone);

		JRadioButtonMenuItem chips = new JRadioButtonMenuItem("Chips");
		chips.addActionListener(this);
		JRadioButtonMenuItem bp = new JRadioButtonMenuItem("Baked Potato");
		bp.addActionListener(this);

		ButtonGroup sides = new ButtonGroup();
		sides.add(chips);
		sides.add(bp);

		cake = new JCheckBoxMenuItem("Cake");
		cake.addItemListener(this);
		sorbet = new JCheckBoxMenuItem("Sorbet");
		sorbet.addItemListener(this);

		starter.add(soup);
		starter.addSeparator();
		starter.add(steak);
		starter.addSeparator();
		starter.add(chips);
		starter.add(bp);
		starter.addSeparator();
		starter.add(cake);
		starter.add(sorbet);

		return menuBar;
	}

	// Deals with the Action Events.
	public void actionPerformed(ActionEvent e) {
		whitebox.append(e.getActionCommand() + " Selected \n");
	}

	// Deals with the Item Events.
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cake) {
			whitebox.append("Cake Clicked\n");
		} else if (e.getSource() == sorbet) {
			whitebox.append("Sorbet Clicked\n");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MenuBarExample_1();
			}
		});
	}
}