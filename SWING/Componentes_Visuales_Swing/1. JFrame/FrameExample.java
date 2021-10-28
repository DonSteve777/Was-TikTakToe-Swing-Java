package tp.examples.swing.misc;

import javax.swing.*;

public class FrameExample {

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("[=] Hello World [=]");

		// To make sure the default operation on pressing the close button on
		// the window-bar
		// is to exit the program. Otherwise the swing thread will keep running.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the size of the window
		frame.setSize(400, 100);

		// Display the window.
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}