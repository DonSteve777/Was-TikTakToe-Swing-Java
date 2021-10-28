package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class CheckBoxExample extends JFrame implements ItemListener {

	JCheckBox redCB, blueCB, greenCB, yellowCB;
	JPanel redBox, blueBox, greenBox, yellowBox;

	CheckBoxExample() {
		super("[=] CheckBox Example [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();

		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		checkBoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		redCB = new JCheckBox("Red");
		redCB.setSelected(true);
		redCB.addItemListener(this);

		checkBoxPanel.add(redCB);
		checkBoxPanel.add(Box.createHorizontalGlue());

		blueCB = new JCheckBox("Blue");
		blueCB.addItemListener(this);

		checkBoxPanel.add(blueCB);
		checkBoxPanel.add(Box.createHorizontalGlue());

		greenCB = new JCheckBox("Green");
		greenCB.addItemListener(this);

		checkBoxPanel.add(greenCB);
		checkBoxPanel.add(Box.createHorizontalGlue());

		yellowCB = new JCheckBox("Yellow");
		yellowCB.addItemListener(this);

		checkBoxPanel.add(yellowCB);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		// Now we create a simple JPanel that displays our four coloured boxes.

		JPanel boxPanel = new JPanel(new GridLayout(2, 2, 20, 20));

		redBox = createSquareJPanel(Color.red, 50);
		blueBox = createSquareJPanel(Color.blue, 50);
		greenBox = createSquareJPanel(Color.green, 50);
		yellowBox = createSquareJPanel(Color.yellow, 50);

		// This sets all bar the red box to be hidden.

		blueBox.setVisible(false);
		greenBox.setVisible(false);
		yellowBox.setVisible(false);

		boxPanel.add(redBox);
		boxPanel.add(blueBox);
		boxPanel.add(greenBox);
		boxPanel.add(yellowBox);

		mainPanel.add(checkBoxPanel);
		mainPanel.add(boxPanel);

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

	// In itemStateChanged, we first determine if the item was selected
	// or deselected and set a temporary boolean depending.
	// Then we determine which item it was that was changed and display the
	// coloured box to match.

	public void itemStateChanged(ItemEvent e) {

		boolean visible = false;

		if (e.getStateChange() == ItemEvent.DESELECTED) {
			visible = false;
		} else {
			visible = true;
		}

		if (e.getItemSelectable() == redCB) {
			redBox.setVisible(visible);
		} else if (e.getItemSelectable() == blueCB) {
			blueBox.setVisible(visible);
		} else if (e.getItemSelectable() == greenCB) {
			greenBox.setVisible(visible);
		} else if (e.getItemSelectable() == yellowCB) {
			yellowBox.setVisible(visible);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CheckBoxExample();
			}
		});
	}
}