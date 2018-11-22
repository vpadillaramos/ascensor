package com.vpr.grafico;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Ventana extends Canvas{
	
	//Componentes
	private JPanel panel = new JPanel();
	
	private static JTextArea consola1 = new JTextArea(0, 24);
	private static JTextArea consola2 = new JTextArea(0, 24);
	private static JTextArea consola3 = new JTextArea(0, 24);
	
	private JScrollPane sp1 = new JScrollPane(consola1);
	private JScrollPane sp2 = new JScrollPane(consola2);
	private JScrollPane sp3 = new JScrollPane(consola3);
	
	//Atributos
	private static final long serialVersionUID = -3177900806970467554L;
	
	//Constructor
	public Ventana(String titulo, Interfaz interfaz, int width, int height) {
		JFrame f = new JFrame(titulo);
		
		f.setPreferredSize(new Dimension(width, height));
		f.setMaximumSize(new Dimension(width, height));
		f.setMinimumSize(new Dimension(width, height));
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(new BorderLayout(10,0));
		
		
		f.add(interfaz);
		
		//panel que contiene los 3 TextArea
		f.add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(3,1,0,0));
		panel.setBackground(Color.WHITE);
		
		//Editamos y añadimos los textArea
		consola1.setEditable(false);
		consola2.setEditable(false);
		consola3.setEditable(false);
		sp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		sp3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		panel.add(sp1);
		panel.add(sp2);
		panel.add(sp3);
		
		/*panel.add(consola, BorderLayout.NORTH);
		consola2.setText("area 2");
		panel.add(consola2, BorderLayout.CENTER);
		consola3.setText("area 3");
		panel.add(consola3, BorderLayout.SOUTH);*/
		
		
		
		f.setVisible(true);
		interfaz.start();
	}
	
	public static void consolaPersonaSube(String mensaje) {
		consola2.setForeground(Color.GRAY);
		consola2.append(mensaje + "\n");
		consola2.setCaretPosition(consola2.getDocument().getLength());
	}
	
	public static void consolaPersonaEspera(String mensaje) {
		consola1.setForeground(Color.GREEN);
		consola1.append(mensaje + "\n");
		consola1.setCaretPosition(consola1.getDocument().getLength());
	}
	
	public static void consolaPersonaSale(String mensaje) {
		consola3.setForeground(Color.RED);
		consola3.append(mensaje + "\n");
		consola3.setCaretPosition(consola3.getDocument().getLength());
	}
}
