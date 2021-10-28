package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MenuBarExample_2 extends JFrame implements ActionListener,
		ItemListener {

	JTextArea whitebox;
	JCheckBoxMenuItem cake, sorbet;

	public MenuBarExample_2() {
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

	public JMenuBar createMenuBar() {
		// Create the menu bar.
		JMenuBar menuBar = new JMenuBar();

		// Add a JMenu
		// The Mnemonic is S, but you need to press Alt+S to access it as it is
		// a top level menu.
		JMenu starter = new JMenu("Starters");
		starter.setMnemonic(KeyEvent.VK_T);
		menuBar.add(starter);

		// Now we want to fill the menu.
		// This has every type of widget in the MenuBar
		// and has Event Listeners attached to each.

		// Sout with the Mnemonic 'U', and the Accelerator Alt+U
		JMenuItem soup = new JMenuItem("Soup");
		soup.setMnemonic(KeyEvent.VK_U);
		soup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				ActionEvent.ALT_MASK));
		soup.addActionListener(this);

		// Steak with the Mnemonic T
		JMenu steak = new JMenu("Steak");
		steak.setMnemonic(KeyEvent.VK_T);

		// Rare with the Mnemonic 'R', and the Accelerator Alt+R
		JMenuItem rare = new JMenuItem("Rare");
		rare.setMnemonic(KeyEvent.VK_R);
		rare.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		rare.addActionListener(this);

		// Well Done with the Mnemonic 'W', and the Accelerator Alt+W
		JMenuItem welldone = new JMenuItem("Well Done");
		welldone.setMnemonic(KeyEvent.VK_W);
		welldone.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.ALT_MASK));
		welldone.addActionListener(this);

		steak.add(rare);
		steak.add(welldone);

		// Chips with the Mnemonic 'C', Potato with the Mnemonic 'P'
		JRadioButtonMenuItem chips = new JRadioButtonMenuItem("Chips");
		chips.setMnemonic(KeyEvent.VK_C);
		chips.addActionListener(this);
		JRadioButtonMenuItem bp = new JRadioButtonMenuItem("Baked Potato");
		bp.setMnemonic(KeyEvent.VK_P);
		bp.addActionListener(this);

		// A button group to group the radiobuttons together.
		ButtonGroup sides = new ButtonGroup();
		sides.add(chips);
		sides.add(bp);

		// Cake with the Mnemonic 'k', Sorbet with the Mnemonic 'b'
		cake = new JCheckBoxMenuItem("Cake");
		cake.setMnemonic(KeyEvent.VK_K);
		cake.addItemListener(this);
		sorbet = new JCheckBoxMenuItem("Sorbet");
		sorbet.setMnemonic(KeyEvent.VK_B);
		sorbet.addItemListener(this);

		// Items added in the order you want them displayed.
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
				new MenuBarExample_2();
			}
		});
	}
}