import javax.swing.JFrame;
import java.awt.EventQueue;

public class Ejemplo2 extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Ejemplo2 () {
		
		super("Mi primera ventana - Ejemplo 2");
		this.setSize(320, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String []args) {
		
		final Ejemplo2 v = new Ejemplo2();
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				v.setVisible(true);
			}
			
		});
		
	}
	
}
