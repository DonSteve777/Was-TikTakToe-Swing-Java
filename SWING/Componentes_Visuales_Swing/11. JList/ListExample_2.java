package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

@SuppressWarnings("serial")
public class ListExample_2 extends JFrame implements ActionListener {

	JList<String> itemList, shoppingList;
	JButton buttonin, buttonout;

	// The ListModels we will be using in the example.
	DefaultListModel<String> shopping, items;

	public ListExample_2() {
		super("[=] List Example - add and remove [=]");
		initGUI();
	}

	private void initGUI() {

		// Create the final Panel.
		JPanel mainPanel = new JPanel();

		// Instantiate the List Models.
		shopping = new DefaultListModel<String>();
		items = new DefaultListModel<String>();

		// Things to be in the list.
		String shoppingItems[] = { "Milk", "Cheese", "Bread", "Butter",
				"Beans", "Soup", "Bacon", "Chicken", "Curry Sauce", "Chocolate" };

		// Using a for loop, we add every item in the String array
		// into the ListModel.

		for (int i = 0; i < shoppingItems.length; i++) {
			shopping.addElement(shoppingItems[i]);
		}

		// Creation of the list.
		// We set the cells in the list to be 20px x 140px.

		itemList = new JList<String>(shopping);
		itemList.setVisibleRowCount(10);
		itemList.setFixedCellHeight(20);
		itemList.setFixedCellWidth(140);
		itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		// We then add them to a JScrollPane.
		// This means when we remove items from the JList
		// it will not shrink in size.
		JScrollPane list1 = new JScrollPane(itemList);

		shoppingList = new JList<String>(items);
		shoppingList.setVisibleRowCount(10);
		shoppingList.setFixedCellHeight(20);
		shoppingList.setFixedCellWidth(140);
		shoppingList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		// We add this list to a JScrollPane too.
		// This is so the list is displayed even though there are
		// currently no items in the list.
		// Without the scrollpane, the list would not show.
		JScrollPane list2 = new JScrollPane(shoppingList);

		// We create the buttons to be placed between the lists.
		JPanel buttonPanel = new JPanel();

		buttonin = new JButton(">>");
		buttonin.addActionListener(this);
		buttonPanel.add(buttonin);

		buttonout = new JButton("<<");
		buttonout.addActionListener(this);
		buttonPanel.add(buttonout);

		// This final bit of code uses a BoxLayout to space out the widgets
		// in the GUI.

		// JPanel bottomPanel = new JPanel();
		// bottomPanel.setLayout(new BoxLayout(bottomPanel,
		// BoxLayout.LINE_AXIS));

		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(list1);
		mainPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		mainPanel.add(buttonPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		mainPanel.add(list2);
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	// valueChanged is the method that deals with a ListSelectionEvent.
	// This simply changes the boxes that are selected to true.

	public void actionPerformed(ActionEvent e) {
		int i = 0;

		// When the 'in' button is pressed,
		// we take the indices and values of the selected items
		// and output them to an array.

		if (e.getSource() == buttonin) {
			int[] fromindex = itemList.getSelectedIndices();
			List<String> from = itemList.getSelectedValuesList();

			// Then, for each item in the array, we add them to
			// the other list.
			for (String s : from) {
				items.addElement(s);
			}

			// Finally, we remove the items from the first list.
			// We must remove from the bottom, otherwise we try to
			// remove the wrong objects.
			for (i = (fromindex.length - 1); i >= 0; i--) {
				shopping.remove(fromindex[i]);
			}
		}

		// If the out button is pressed, we take the indices and values of
		// the selected items and output them to an array.
		else if (e.getSource() == buttonout) {
			List<String> to = shoppingList.getSelectedValuesList();
			int[] toindex = shoppingList.getSelectedIndices();

			// Then, for each item in the array, we add them to
			// the other list.
			for (String s : to) {
				shopping.addElement(s);
			}

			// Finally, we remove the items from the first list.
			// We must remove from the bottom, otherwise we try to
			// remove the wrong objects.
			for (i = (toindex.length - 1); i >= 0; i--) {
				items.remove(toindex[i]);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ListExample_2();
			}
		});
	}
}