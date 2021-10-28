package tp.examples.swing.misc;

import javax.swing.*;

// The same as FrameExample.java, but using a class that extends JFrame
//
public class FrameExample_1 extends JFrame {

	private static final long serialVersionUID = 1L;

	public FrameExample_1() {
		super("[=] Hello World [=]");
		initGUI();
	}

	private void initGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 100);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.out.println("This is swing's thread: "+Thread.currentThread().getName());
				new FrameExample_1();
			}
		});
		System.out.println("This is main's thread: "+Thread.currentThread().getName());
	}
}