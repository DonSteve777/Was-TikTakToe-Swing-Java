package tp.examples.swing.misc;

import javax.swing.*;

import tp.examples.swing.icons.IconsDir;

@SuppressWarnings("serial")
public class ButtonExample_2 extends JFrame {

	// Definition of global values and items that are part of the GUI.

	static int i = 0;
	int redScoreAmount = 0;
	int blueScoreAmount = 0;

	JButton exitButton, undoButton;

	public ButtonExample_2() {
		super("[=] JButton Icons! [=]");
		initGUI();
	}

	public void initGUI() {

		// We create a bottom JPanel to place everything on.
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(true);

		exitButton = new JButton("Exit");
		exitButton.setLocation(0, 0);
		exitButton
				.setIcon(new ImageIcon("src/tp/examples/swing/icons/exit.png"));
		mainPanel.add(exitButton);

		exitButton = new JButton("Undo");
		exitButton.setLocation(0, 0);
		exitButton
				.setIcon(new ImageIcon(IconsDir.class.getResource("undo.png")));
		mainPanel.add(exitButton);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 190);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ButtonExample_2();
			}
		});
	}
}
