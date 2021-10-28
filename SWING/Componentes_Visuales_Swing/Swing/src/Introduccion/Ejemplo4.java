package Introduccion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

public class Ejemplo4 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Ejemplo4() {
		
		super("Mi primera ventana - Ejemplo 4");
		this.setSize(200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().setLayout(new FlowLayout());
		
		this.getContentPane().add(new JLabel("Ventana de saludo"));
		
			JButton boton = new JButton("Pulsa para saludar");
			
			boton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event) {
					System.out.println("Hola Mundo!");
				}
				
			});
			
		this.getContentPane().add(boton);
		
		this.getContentPane().add(new JLabel("otra etiqueta"));
		
			JButton otroBoton = new JButton("Ahora salimos!");
	
			otroBoton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event) {
					Ejemplo4.this.dispose();
				}
				
			});
		
		this.getContentPane().add(otroBoton);
	
	}

	public static void main(String []args) {
		
		final Ejemplo4 v = new Ejemplo4();
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				v.setVisible(true);
			}
		
		});
		
	}
	
}
