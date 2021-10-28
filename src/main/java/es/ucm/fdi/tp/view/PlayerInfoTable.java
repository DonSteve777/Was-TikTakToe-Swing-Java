package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import es.ucm.fdi.tp.extra.jcolor.ColorChooser;

public class PlayerInfoTable extends JTable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1022419422448339477L;
	private PlayerInfoTableModel tableModel;
	private ColorChooser colorChooser;
	private Map<Integer, Color> colors; // Line -> Color
	

	public PlayerInfoTable(PlayerInfoTableModel tableModel)
	{
		this.tableModel = tableModel;
		this.setModel(tableModel);
		initGUI();
	}
	
	

	private void initGUI() 
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		colors = new HashMap<>();
		colors.put(0, Color.BLUE);
		colors.put(1, Color.RED);
		colorChooser = new ColorChooser(new JFrame(), "Choose Player's Color", Color.BLACK);
		
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = PlayerInfoTable.this.rowAtPoint(evt.getPoint());
				int col = PlayerInfoTable.this.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					changeColor(row);
				}
			}

		});
		
		mainPanel.add(new JScrollPane(this), BorderLayout.CENTER);
		mainPanel.setPreferredSize(new Dimension(200, 200));
		mainPanel.setOpaque(true);
		
		
		
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
		Component comp = super.prepareRenderer(renderer, row, col);

		// the color of row 'row' is taken from the colors table, if
		// 'null' setBackground will use the parent component color.
		if (col == 1)
			comp.setBackground(colors.get(row));
		else
			comp.setBackground(Color.WHITE);
		comp.setForeground(Color.BLACK);
		return comp;
	}
	
	private void changeColor(int row) 
	{
		colorChooser.setSelectedColorDialog(colors.get(row));
		colorChooser.openDialog();
		if (colorChooser.getColor() != null) {
			colors.put(row, colorChooser.getColor());
			
			int i = 0;
			++i;
		}
		
		tableModel.refresh();
	}
	
	
	public Color getPlayerColor(int row)
	{
		return this.colors.get(row);
	}
	
}
