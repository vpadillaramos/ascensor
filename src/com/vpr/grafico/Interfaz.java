package com.vpr.grafico;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class Interfaz extends Canvas implements Runnable{
	//Constantes
	public static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = (int) d.getWidth()-30; //640
	public static final int HEIGHT = (int) (d.getHeight()-50); //WIDTH/12*9
	
	//Atirbutos
	private static final long serialVersionUID = 2601176506843040199L;
	private boolean SUBIR_BAJAR = false;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	//Constructor
	public Interfaz() {
		new Ventana("Ascensor", this, WIDTH, HEIGHT);
		handler = new Handler();
		handler.addObjeto(new ObjAscensor(23, 560, ID.Ascensor)); //añadir el objeto ascensor //560 470
		handler.addObjeto(new Edificio(0, 0, ID.Edificio));
		handler.addObjeto(new Piso(20, HEIGHT-70, ID.Piso));
		handler.addObjeto(new Piso(20, HEIGHT-160, ID.Piso));
		handler.addObjeto(new Piso(20, HEIGHT-250, ID.Piso));
		handler.addObjeto(new Piso(20, HEIGHT-340, ID.Piso));
		handler.addObjeto(new Piso(20, HEIGHT-430, ID.Piso));
		handler.addObjeto(new Piso(20, HEIGHT-520, ID.Piso));
		handler.addObjeto(new Piso(20, HEIGHT-610, ID.Piso));
		handler.addObjeto(new Piso(20, HEIGHT-700, ID.Piso));
	}
	
	//Metodos
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//Atributos
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		//Cuerpo
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				/*if(SUBIR_BAJAR)
					subirPiso();
				else
					bajarPiso();*/
				
				delta--;
			}
			
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//pantalla
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	/*
	//******MAIN******
	public static void main(String[] args) {
		new Interfaz();
	}*/
}
