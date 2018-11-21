package com.vpr.grafico;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Ventana extends Canvas{

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
		
		f.add(interfaz);
		
		f.setVisible(true);
		interfaz.start();
	}
}
