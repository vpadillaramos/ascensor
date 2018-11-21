package com.vpr.grafico;

import java.awt.Color;
import java.awt.Graphics;

class Piso extends Objeto{

	public Piso(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		//g.fillRect(x, y, Interfaz.WIDTH - 30, 8);
		g.fillRect(x, y, Interfaz.WIDTH - 30, 8);
	}

}
