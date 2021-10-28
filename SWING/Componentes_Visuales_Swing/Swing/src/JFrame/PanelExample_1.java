package JFrame;

import javax.swing.*;

import java.awt.Color;

@SuppressWarnings("serial")
public class PanelExample_1 extends JFrame {

	public PanelExample_1() {
		super("[=] There's 3 JPanels in here! [=]");
		initGUI();
	}

	private void initGUI() {

		// We create a bottom JPanel to place everything on.
		JPanel mainPanel = new JPanel();

		// We set the Layout Manager to null so we can manually place
		// the Panels.
		mainPanel.setLayout(null);

		// Now we create a new panel, size it, shape it,color it red.
		// then add it to the bottom JPanel.
		JPanel redPanel = new JPanel();
		redPanel.setBackground(Color.red);
		redPanel.setLocation(60, 10);
		redPanel.setSize(50, 50);
		mainPanel.add(redPanel);

		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(Color.blue);
		bluePanel.setLocation(200, 10);
		bluePanel.setSize(50, 50);
		mainPanel.add(bluePanel);

		// Finally we plug the main into the content Pane.
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(290, 100);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PanelExample_1();
			}
		});
	}
}