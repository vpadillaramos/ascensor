package com.vpr.grafico;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Numero extends Objeto{
	
	//Atributos
	private Image imagen;
	private ImageIcon i;
	
	public Numero(int x, int y, ID id, int numero) {
		super(x, y, id);
		
		i = new ImageIcon("..\\Ascensor\\res\\piso"+numero+".png");
		imagen = i.getImage();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(imagen, x, y, 70, 50, null);
	}

}
