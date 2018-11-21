package com.vpr.grafico;

import java.awt.Graphics;

public abstract class Objeto {
	//Atributos
	protected int x, y;
	protected int velX;
	protected int velY;
	protected ID id;
	
	//Consructor
	public Objeto(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	//Metodos
	public abstract void tick();
	public abstract void render(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	
}
