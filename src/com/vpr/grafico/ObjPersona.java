package com.vpr.grafico;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ObjPersona extends Objeto{
	
	private Image imagen;
	private ImageIcon i;
	private boolean esperando = false;
	private boolean saliendo = false;
	
	public ObjPersona(int x, int y, ID id, boolean espera) {
		super(x, y, id);
		
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
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(imagen, x, y, 50, 40, null);
	}

}
