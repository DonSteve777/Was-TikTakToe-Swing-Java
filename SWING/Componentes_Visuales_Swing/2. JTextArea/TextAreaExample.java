package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class TextAreaExample extends JFrame implements ActionListener {

	JTextArea textArea;
	JButton readOnlyButton, clearButton;

	public TextAreaExample() {
		super("[=] TextArea Example[=]");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		textArea = new JTextArea(5, 30);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		mainPanel.add(textArea, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);

		readOnlyButton = new JButton("Make ReadOnly");
		readOnlyButton.addActionListener(this);
		buttonsPanel.add(readOnlyButton);

		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		buttonsPanel.add(clearButton);

		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clearButton) {
			textArea.setText("");
		} else if (e.getSource() == readOnlyButton) {
			boolean editable = textArea.isEditable();
			textArea.setEditable(!editable);
			if (editable)
				readOnlyButton.setText("Make Editable");
			else
				readOnlyButton.setText("Make Read Only");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TextAreaExample();
			}
		});
	}
}
