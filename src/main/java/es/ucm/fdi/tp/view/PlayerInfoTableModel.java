package es.ucm.fdi.tp.view;

import java.util.ArrayList;
import java.util.List;


import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class PlayerInfoTableModel extends DefaultTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -873195757212342167L;
	private String[] columnNames;
	List<Integer> players;
	
	public PlayerInfoTableModel() 
	{
		this.columnNames = new String[] { "#Player", "Color" };
		players = new ArrayList<>();
		players.add(0);
		players.add(1);
		
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return players != null ? players.size() : 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		if(columnIndex == 0)
			return rowIndex;
		else return null;
		
	}
	
	
	
	public void addPlayer(Integer player) {
		players.add(player);
		refresh();
	}
	
	public void refresh() {
		fireTableDataChanged();
	}
	
	


}
