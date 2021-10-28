package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class DialogExample_1 extends JFrame {

	public DialogExample_1() {
		super("Confirm Quit Example");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		mainPanel.add(quit);
		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				quit();
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(400, 60);
		this.setVisible(true);

	}

	private void quit() {
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (n == 0) {
			// finalize your app
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new DialogExample_1();
			}
		});
	}
}
