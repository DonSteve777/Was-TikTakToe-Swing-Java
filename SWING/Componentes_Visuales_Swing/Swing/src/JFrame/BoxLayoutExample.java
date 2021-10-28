package JFrame;

import javax.swing.*;

import java.awt.Component;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class BoxLayoutExample extends JFrame {

	JPanel centerPanel, leftPanel, rightPanel, topPanel, botPanel;

	public BoxLayoutExample() {
		super("[=] BoxLayout [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();
		// try X_AXIS instead of Y_AXIS
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addButton("Holaa", mainPanel);
		addButton("Holaaa", mainPanel);
		addButton("Holaaaa", mainPanel);
		addButton("Holaaaaa", mainPanel);
		addButton("Holaaaaaa", mainPanel);

		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(5, 5);
		this.pack();
		this.setVisible(true);
	}

	private void addButton(String text, JPanel container) {
		JButton button = new JButton(text);
		// try LEFT or RIGHT instead of CENTER
		button.setAlignmentX(Component.CENTER_ALIGNMENT);

		// JButton already set the maximum and minimum size -- try the
		// following:
		// System.out.println(button.getMaximumSize()+" "+button.getMinimumSize());

		// try to use change the maximum and minumum size, and see what happens
		// when you resize the window
		button.setPreferredSize(new Dimension(100, 30));
		// button.setMaximumSize(new Dimension(1000, 30));
		// button.setMinimumSize(new Dimension(100, 30));
		container.add(button);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BoxLayoutExample();
			}
		});
	}
}
