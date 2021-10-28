package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class TableExample extends JFrame {

	TableExample() {
		super("[=] JTable Example [=]");
		initGUI();
	}

	private void initGUI() {

		String[] columnNames = { "First Name", "Last Name", "Sport",
				"# of Years", "Vegetarian" };

		Object[][] data = {
				{ "Kathy", "Smith", "Snowboarding", new Integer(5),
						new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),
						new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),
						new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		JTable table = new JTable(data,columnNames);
		mainPanel.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TableExample();
			}
		});
	}
}
