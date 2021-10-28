package tp.examples.swing.misc;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;

@SuppressWarnings("serial")
public class ListExample_1 extends JFrame implements ListSelectionListener {

	JList<String> boxes;
	JPanel[] boxArray = new JPanel[10];

	public ListExample_1() {
		super("[=] List with Multiple Selection [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();

		// Array for the names to be in the list.
		String names[] = { "Red", "Blue", "Green", "Orange", "Yellow", "Pink",
				"Cyan", "Gray", "Black", "Magenta" };

		// Creation of the list.
		// We set the cells in the list to be 20px x 140px.
		// And set the 'selection mode' to MULTIPLE! Woo, BigKahuna!
		// We finish by adding an event listener for the List.

		boxes = new JList<String>(names);
		boxes.setVisibleRowCount(10);
		boxes.setFixedCellHeight(20);
		boxes.setFixedCellWidth(140);
		boxes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		boxes.addListSelectionListener(this);

		// We create a JPanel with the GridLayout.
		JPanel boxesPanel = new JPanel(new GridLayout(3, 4, 10, 30));

		boxArray[0] = createSquareJPanel(Color.red, 50);
		boxArray[1] = createSquareJPanel(Color.blue, 50);
		boxArray[2] = createSquareJPanel(Color.green, 50);
		boxArray[3] = createSquareJPanel(Color.orange, 50);
		boxArray[4] = createSquareJPanel(Color.yellow, 50);
		boxArray[5] = createSquareJPanel(Color.pink, 50);
		boxArray[6] = createSquareJPanel(Color.cyan, 50);
		boxArray[7] = createSquareJPanel(Color.gray, 50);
		boxArray[8] = createSquareJPanel(Color.black, 50);
		boxArray[9] = createSquareJPanel(Color.magenta, 50);

		// This sets every JPanel in the array to be hidden,
		// and then adds it to the mainPanel JPanel.

		for (int i = 0; i < 10; i++) {
			boxArray[i].setVisible(false);
			boxesPanel.add(boxArray[i]);
		}

		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(boxes);
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(boxesPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));

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

	// valueChanged is the method that deals with a ListSelectionEvent.
	// This simply changes the boxes that are selected to true.

	public void valueChanged(ListSelectionEvent e) {

		if (e.getValueIsAdjusting() == false) {
			if (e.getSource() == boxes) {

				int[] fromindex = boxes.getSelectedIndices();

				for (int i = 0; i < 10; i++) {
					boxArray[i].setVisible(false);
				}
				for (int i = 0; i < fromindex.length; i++) {
					boxArray[fromindex[i]].setVisible(true);
				}
			}
		}
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ListExample_1();
			}
		});
	}
}
