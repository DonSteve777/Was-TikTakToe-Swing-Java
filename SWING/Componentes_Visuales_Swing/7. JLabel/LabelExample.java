package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.Color;

@SuppressWarnings("serial")
public class LabelExample extends JFrame {

	public LabelExample() {
		super("[=] JLabel Fables [=]");
		initGUI();
	}

	public void initGUI() {

		// We create a bottom JPanel to place everything on.
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setOpaque(true);

		// Creation of a Panel to contain the JLabels
		JPanel textPanel = new JPanel();
		textPanel.setLayout(null);
		textPanel.setLocation(10, 0);
		textPanel.setSize(260, 30);
		mainPanel.add(textPanel);

		// First JLabel, outputs "Red".
		// Added to the 'textPanel' JPanel
		JLabel redLabel = new JLabel("Red");
		redLabel.setLocation(0, 0);
		redLabel.setSize(50, 30);
		redLabel.setHorizontalAlignment(JLabel.CENTER);
		textPanel.add(redLabel);

		// Second JLabel, outputs "Yellow"
		JLabel yellowLabel = new JLabel("Yellow");
		yellowLabel.setLocation(70, 0);
		yellowLabel.setSize(50, 30);
		yellowLabel.setHorizontalAlignment(JLabel.CENTER);
		textPanel.add(yellowLabel);

		JLabel greenLabel = new JLabel("Green");
		greenLabel.setLocation(140, 0);
		greenLabel.setSize(50, 30);
		greenLabel.setHorizontalAlignment(JLabel.CENTER);
		textPanel.add(greenLabel);

		JLabel blueLabel = new JLabel("Blue");
		blueLabel.setLocation(210, 0);
		blueLabel.setSize(50, 30);
		blueLabel.setHorizontalAlignment(JLabel.CENTER);
		textPanel.add(blueLabel);

		// Creates a panel to hold the following panels.
		JPanel panelForPanels = new JPanel();
		panelForPanels.setLayout(null);
		panelForPanels.setLocation(10, 40);
		panelForPanels.setSize(260, 50);
		mainPanel.add(panelForPanels);

		JPanel redPanel = new JPanel();
		redPanel.setBackground(Color.red);
		redPanel.setLocation(0, 0);
		redPanel.setSize(50, 50);
		panelForPanels.add(redPanel);

		JPanel yellowPanel = new JPanel();
		yellowPanel.setBackground(Color.yellow);
		yellowPanel.setLocation(70, 0);
		yellowPanel.setSize(50, 50);
		panelForPanels.add(yellowPanel);

		JPanel greenPanel = new JPanel();
		greenPanel.setBackground(Color.green);
		greenPanel.setLocation(140, 0);
		greenPanel.setSize(50, 50);
		panelForPanels.add(greenPanel);

		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(Color.blue);
		bluePanel.setLocation(210, 0);
		bluePanel.setSize(50, 50);
		panelForPanels.add(bluePanel);

		this.setContentPane(mainPanel);

		// The other bits and pieces that make our program a bit more stable.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(290, 130);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LabelExample();
			}
		});
	}
}