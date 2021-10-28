package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import es.ucm.fdi.tp.extra.jcolor.ColorChooser;

public class EastPanel extends JPanel 
{

	private JTextArea statusMessages;
	private JScrollPane scrollTextArea;
	private PlayerInfoTable playersTable;
	private PlayerInfoTableModel tableModel;
		
	public EastPanel() 
	{
		initGUI();
	}

	private void initGUI() 
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.addInfoArea();
		this.addPlayerInfoTable();
	}
	
	private void addPlayerInfoTable() 
	{
		tableModel = new PlayerInfoTableModel();
		playersTable = new PlayerInfoTable(tableModel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(playersTable), BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createTitledBorder("Player Information"));
		
		panel.setPreferredSize(new Dimension(50, 100));
		this.add(panel);
	}

	private void addInfoArea()
	{
		JPanel messagesPanel = new JPanel();
		messagesPanel.setLayout(new BorderLayout());
		messagesPanel.setBorder(BorderFactory.createTitledBorder("Status Messages"));
		this.statusMessages = new JTextArea(15, 20);
		this.statusMessages.setEditable(false);
		this.statusMessages.setLineWrap(true);
		this.statusMessages.setWrapStyleWord(true);
		
		this.scrollTextArea = new JScrollPane(this.statusMessages, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		messagesPanel.add(scrollTextArea, BorderLayout.CENTER);
		
		this.add(messagesPanel);
	}
	
	public Color getPlayerColor(int row)
	{
		return this.playersTable.getPlayerColor(row);
	}
	
	public void writeMessage(String message)
	{
		this.statusMessages.append("* " + message + System.getProperty("line.separator"));
	}
	
	public void cleanStatusMessages()
	{
		statusMessages.setText(null);
	}
	
	
}
