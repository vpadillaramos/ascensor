package com.vpr.grafico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class ObjAscensor extends Objeto{
	//Constantes
	
	//Atributos
	private Image imagen;
	private ImageIcon i;
	public static int cont;
	private static boolean SUBIR = false;
	private static boolean BAJAR = false;
	private boolean seguirSubiendo = false;
	private boolean seguirBajando = false;
	
	public ObjAscensor(int x, int y, ID id) {
		super(x, y, id);
		
		//imagen
		i = new ImageIcon("..\\Ascensor\\res\\ascensor2.png");
		imagen = i.getImage();
		
		//velocidad
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
		g.drawImage(imagen, x, y, 100, 100, null);
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
	
	public static void preparaAscensor() {
		SUBIR = false;
		BAJAR = false;
		cont = 0;
	}

}
