package JDialog;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

@SuppressWarnings("serial")
public class FileChooserExample extends JFrame implements ActionListener {

	JButton loadButton, saveButton;
	JFileChooser fc;

	public FileChooserExample() {
		super("[=] FileChooser Example [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();

		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		mainPanel.add(saveButton);

		loadButton = new JButton("Load");
		loadButton.addActionListener(this);
		mainPanel.add(loadButton);

		// Create a file chooser only once, and declare it as a filed, this way
		// it remembers in which it was every time we open it, etc.
		fc = new JFileChooser();

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		//fc = new JFileChooser();
		if (e.getSource() == loadButton) {
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("Opening: " + file.getName());
			} else {
				System.out.println("Open command cancelled by user.");
			}
		} else if (e.getSource() == saveButton) {
			int returnVal = fc.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("Saving: " + file.getName());
			} else {
				System.out.println("Open command cancelled by user.");
			}
		}
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FileChooserExample();
			}
		});
	}
}
