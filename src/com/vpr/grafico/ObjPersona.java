package com.vpr.grafico;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ObjPersona extends Objeto{
	
	private Image imagen;
	private ImageIcon i;
	private static int cont;
	private boolean espera;
	
	
	public ObjPersona(int x, int y, ID id, boolean espera) {
		super(x, y, id);
		this.espera = espera;
		
		if(espera) {
			i = new ImageIcon("..\\Ascensor\\res\\persona_esperando.png");
			imagen = i.getImage();
		}
		if(!espera) {
			i = new ImageIcon("..\\Ascensor\\res\\persona_saliendo.png");
			imagen = i.getImage();
		}
	}

	@Override
	public void tick() {
		if(!espera)
			personaAbandona();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(imagen, x, y, 50, 40, null);
	}
	
	public void personaAbandona() {
		velX  = 2;
		x += velX;
	}

}
