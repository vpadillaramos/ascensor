package com.vpr.grafico;

import java.awt.Graphics;

public class Aforo extends Objeto{
	
	//Atributos
	public static int cont;
	private static boolean SUBIR = false;
	private static boolean BAJAR = false;
	private static String aforo = "0";
	
	public Aforo(int x, int y, ID id) {
		super(x, y, id);
		velY = 1;
	}

	@Override
	public void tick() {
		if(SUBIR) {
			subePiso();
		}
		
		if(BAJAR) {
			bajaPiso();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawString(aforo, x, y);
	}
	
	public void subePiso() {
		if(cont >= 90) {
			velY *= 0;
			SUBIR = false;
		}
		else {
			SUBIR = true;
			velY = 1;
			y -= velY;
			cont++;
		}
	}
	
	public void bajaPiso() {
		if(cont >= 90) {
			BAJAR = false;
			velY *= 0;
		}
		else {
			BAJAR = true;
			velY = 1;
			y += velY;
			cont++;
		}
	}
	
	public static void subir() {
		SUBIR = true;
	}
	
	public static void bajar() {
		BAJAR = true;
	}
	
	public static void preparaAforo() {
		SUBIR = false;
		BAJAR = false;
		cont = 0;
	}
	
	public static void setAforo(int personas) {
		aforo = String.valueOf(personas);
	}

}
