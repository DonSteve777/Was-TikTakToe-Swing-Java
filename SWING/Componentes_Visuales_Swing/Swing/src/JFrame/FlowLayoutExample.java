package JFrame;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class FlowLayoutExample extends JFrame {

	public FlowLayoutExample() {
		super();
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

		mainPanel.add(createPanel(Color.red, 50, 50));
		mainPanel.add(createPanel(Color.blue, 50, 50));
		mainPanel.add(createPanel(Color.green, 50, 50));
		mainPanel.add(createPanel(Color.orange, 50, 50));
		mainPanel.add(createPanel(Color.yellow, 50, 50));

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 180);
		this.setVisible(true);
	}

	private JPanel createPanel(Color color, int x, int y) {

		JPanel panel;
		panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x, y));
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FlowLayoutExample();
			}
		});
	}
}
