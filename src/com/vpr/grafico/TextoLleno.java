package com.vpr.grafico;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TextoLleno extends Objeto{
	
	//Atributos
	private Image imagen;
	private ImageIcon i;
	
	public TextoLleno(int x, int y, ID id) {
		super(x, y, id);
		
		//imagen
		i = new ImageIcon("..\\Ascensor\\res\\texto_lleno.png");
		imagen = i.getImage();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(imagen, x, y, 80, 20, null); //80, 20
	}

}
