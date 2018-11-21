package com.vpr.grafico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Edificio extends Objeto{
	//Atributos
	private Image imagen;
	private ImageIcon i;
	
	//Constructor
	public Edificio(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		
		i = new ImageIcon("..\\Ascensor\\res\\edificio.png");
		imagen = i.getImage();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(imagen, x, y, 150, Interfaz.HEIGHT - 20, null);
	}

}
