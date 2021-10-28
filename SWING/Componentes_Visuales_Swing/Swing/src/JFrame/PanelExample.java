package JFrame;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelExample extends JFrame {

	public PanelExample() {
		super("[=] There's a JPanel in here! [=]");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(290, 100);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PanelExample();
			}
		});
	}
}
