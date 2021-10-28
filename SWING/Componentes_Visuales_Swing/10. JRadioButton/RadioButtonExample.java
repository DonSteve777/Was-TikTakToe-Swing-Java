package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class RadioButtonExample extends JFrame implements ActionListener,
		ItemListener {

	JRadioButton leftButton, centerButton, rightButton;
	JPanel boxesPanel;
	String names[] = { "LEFT", "CENTER", "RIGHT" };

	public RadioButtonExample() {
		super("[=] RadioButtons Example [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		// First, lets get on with creating the RadioButtons.
		// We create each RadioButton, then add it to a ButtonGroup.
		// This ButtonGroup deals with keeping only one selected.
		// We must also add them to a JPanel to display the RadioButtons.

		leftButton = new JRadioButton(names[0]);
		leftButton.addActionListener(this);
		leftButton.addItemListener(this);
		centerButton = new JRadioButton(names[1]);
		centerButton.setSelected(true);
		centerButton.addActionListener(this);
		centerButton.addItemListener(this);
		rightButton = new JRadioButton(names[2]);
		rightButton.addActionListener(this);
		rightButton.addItemListener(this);

		// Create the button group to keep only one selected.
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(leftButton);
		btnGroup.add(centerButton);
		btnGroup.add(rightButton);

		// Create the JPanel to display the RadioButtons.
		JPanel buttonsPanel = new JPanel();
		buttonsPanel
				.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));

		buttonsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		buttonsPanel.add(leftButton);
		buttonsPanel.add(Box.createHorizontalGlue());
		buttonsPanel.add(centerButton);
		buttonsPanel.add(Box.createHorizontalGlue());
		buttonsPanel.add(rightButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(10, 10)));

		// We create the first JPanel with the FlowLayout.
		// Although we could just use ONE Panel and simply change the
		// align using .setAlignment(), this way we can practice CardLayout
		// again.

		boxesPanel = new JPanel();
		boxesPanel.setPreferredSize(new Dimension(200, 120));
		boxesPanel.setBackground(Color.gray);

		JPanel red = createSquareJPanel(Color.red, 50);
		JPanel blue = createSquareJPanel(Color.blue, 50);
		JPanel green = createSquareJPanel(Color.green, 50);
		JPanel orange = createSquareJPanel(Color.orange, 50);
		JPanel yellow = createSquareJPanel(Color.yellow, 50);

		boxesPanel.add(red);
		boxesPanel.add(blue);
		boxesPanel.add(green);
		boxesPanel.add(orange);
		boxesPanel.add(yellow);

		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(buttonsPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(20, 0)));
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == leftButton)
			System.out.println("iteml: leading");
		else if (e.getSource() == centerButton)
			System.out.println("iteml: center");
		else if (e.getSource() == rightButton)
			System.out.println("iteml: trailing");
	}

	// This actionPerformed simply takes sets the visibility of each
	// coloured box depending on what is selected on the combo box.

	public void actionPerformed(ActionEvent e) {
		FlowLayout fl = (FlowLayout) boxesPanel.getLayout();
		if (e.getSource() == leftButton) {
			fl.setAlignment(FlowLayout.LEFT);
			System.out.println("action: leading");
		} else if (e.getSource() == centerButton) {
			fl.setAlignment(FlowLayout.CENTER);
			System.out.println("action: center");
		} else if (e.getSource() == rightButton) {
			fl.setAlignment(FlowLayout.RIGHT);
			System.out.println("action: trailing");
		}
		boxesPanel.revalidate();

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RadioButtonExample();
			}
		});
	}

}
