package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GridLayoutExample extends JFrame {

	public GridLayoutExample() {
		super("[=] Grid Layout [=]");
		initGUI();
	}

	private void initGUI() {

		// We create a main JPanel with the GridLayout.
		JPanel mainPanel = new JPanel(new GridLayout(5, 2, 3, 3));

		JPanel red = createSquareJPanel(Color.red, 100);
		JPanel blue = createSquareJPanel(Color.blue, 100);
		JPanel green = createSquareJPanel(Color.green, 100);
		JPanel orange = createSquareJPanel(Color.orange, 100);
		JPanel yellow = createSquareJPanel(Color.yellow, 50);
		JPanel pink = createSquareJPanel(Color.pink, 50);
		JPanel cyan = createSquareJPanel(Color.cyan, 50);
		JPanel gray = createSquareJPanel(Color.gray, 50);
		JPanel black = createSquareJPanel(Color.black, 50);
		JPanel magenta = createSquareJPanel(Color.magenta, 50);

		mainPanel.add(red);
		mainPanel.add(blue);
		mainPanel.add(green);
		mainPanel.add(orange);
		mainPanel.add(yellow);
		mainPanel.add(pink);
		mainPanel.add(cyan);
		mainPanel.add(gray);
		mainPanel.add(black);
		mainPanel.add(magenta);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	// In this method, we create a square JPanel of a colour and set size
	// specified by the arguments.

	private JPanel createSquareJPanel(Color color, int size) {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(color);
		tempPanel.setMinimumSize(new Dimension(size, size));
		tempPanel.setMaximumSize(new Dimension(size, size));
		tempPanel.setPreferredSize(new Dimension(size, size));
		return tempPanel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GridLayoutExample();
			}
		});
	}
}
