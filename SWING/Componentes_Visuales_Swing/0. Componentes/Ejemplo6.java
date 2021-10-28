import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;

public class Ejemplo6 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Ejemplo6() {
		
		super("Mi primera ventana - Ejemplo 6");
		this.setSize(450, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int filas = 3;
		int cols = 4;
		
		this.getContentPane().setLayout(new GridLayout(filas,cols,5,5));
		
		for (int i = 0; i < filas-1; i++) {
			for (int j = 0; j < cols; j++) {
				
				JLabel lbl = new JLabel("(" + Integer.toString(i) + ", " + Integer.toString(j) + ")");
				lbl.setBackground(Color.blue);
				lbl.setOpaque(true);
				this.getContentPane().add(lbl);
				
			}
		}
		
	}

	public static void main(String []args) {

		final Ejemplo6 v = new Ejemplo6();
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				v.setVisible(true);
			}
			
		});
		
	}
	
}

/* 
 * Para centrar las etiquetas en cada celda del grid hay que sustituir 
 * el codigo del cuerpo de los bucles 'for' por lo siguiente:
 */

// JPanel p1 = new JPanel();
// p1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
// p1.add(new JLabel("(" + Integer.toString(i) + ", " + Integer.toString(j) + ")"));
// p1.setBackground(Color.BLUE);
// this.getContentPane().add(p1);
