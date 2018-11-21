package com.vpr.grafico;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<Objeto> objeto = new LinkedList<Objeto>();
	
	public void tick() {
		for(int i = 0; i < objeto.size(); i++) {
			Objeto tempObjeto = objeto.get(i);
			tempObjeto.tick();
		}
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objeto.size(); i++) {
			Objeto tempObjeto = objeto.get(i);
			tempObjeto.render(g);
		}
	}
	
	public void addObjeto(Objeto objeto) {
		this.objeto.add(objeto);
	}
	
	public void removeObjeto(Objeto objeto) {
		this.objeto.remove(objeto);
	}
}
