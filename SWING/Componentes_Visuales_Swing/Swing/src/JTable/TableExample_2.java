package JTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class TableExample_2 extends JFrame implements ActionListener {

	SomeTableModel tableModel;
	private JButton printTable;
	private JTextField xPos;
	private JTextField yPos;
	private JTextField value;
	private JButton setButton;
	private JButton incrButton;
			
	public TableExample_2() {
		super("[=] AbstractTableModel Example [=]");
		initGUI();
	}


	class SomeTableModel extends AbstractTableModel {

		String[] columnNames = { "A", "B", "C", "D", "E", "F", "G", "H" };
		Object[][] rowData;
		static final int initNumOfRows = 10;
		int currNumOfCols;
		
		public SomeTableModel() {
			int k = 0;
			rowData = new Object[initNumOfRows][columnNames.length];
			for(int i=0; i<rowData.length; i++)
				for(int j=0; j<columnNames.length; j++)
					rowData[i][j] = new Integer(k++);
			currNumOfCols = 1;
		}
		public String getColumnName(int col) {
			return columnNames[col].toString();
		}

		public int getRowCount() {
			return rowData.length;
		}

		public int getColumnCount() {
			return currNumOfCols;
		}

		public Object getValueAt(int row, int col) {
			return rowData[row][col];
		}

		public boolean isCellEditable(int row, int col) {
			return col>0;
		}

		public void setValueAt(Object value, int row, int col) {
			rowData[row][col] = value;
			fireTableCellUpdated(row, col);	
		}
		
		public void printTable() {
			for(int i=0; i<rowData.length; i++) {
				for(int j=0; j<columnNames.length; j++) 
					System.out.print(rowData[i][j]+" ");
			System.out.println();
			}
		}
		
		public void incrNumOfCols() {
			if ( currNumOfCols < columnNames.length ) {
				currNumOfCols++;
				fireTableStructureChanged();
			}
		}

	}


	private void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		tableModel = new SomeTableModel();
		JTable table = new JTable(tableModel);
		mainPanel.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);

		JPanel buttonsPanel = new JPanel();
		mainPanel.add(buttonsPanel, BorderLayout.PAGE_START);

		printTable = new JButton("Print Table");
		buttonsPanel.add(printTable);
		printTable.addActionListener(this);
		
		xPos = new JTextField(4);
		yPos = new JTextField(4);
		value = new JTextField(4);
		buttonsPanel.add(xPos);
		buttonsPanel.add(yPos);
		buttonsPanel.add(value);
		
		setButton = new JButton("Set");
		buttonsPanel.add(setButton);
		setButton.addActionListener(this);

		incrButton = new JButton("Incr Cols");
		buttonsPanel.add(incrButton);
		incrButton.addActionListener(this);
		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == printTable )
			tableModel.printTable();
		else if ( e.getSource() == setButton ) {
			Integer x = new Integer( xPos.getText() );
			Integer y = new Integer( yPos.getText() );
			Integer v = new Integer( value.getText() );
			tableModel.setValueAt(v, x, y);
		} else if ( e.getSource() == incrButton ) 
			tableModel.incrNumOfCols();
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TableExample_2();
			}
		});
	}

}

