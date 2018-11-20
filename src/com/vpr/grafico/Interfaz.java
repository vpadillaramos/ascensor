package com.vpr.grafico;

import java.awt.Canvas;

public class Interfaz extends Canvas implements Runnable{
	//Constantes
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH/12*9;
	
	//Atirbutos
	private static final long serialVersionUID = 2601176506843040199L;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	//Constructor
	public Interfaz() {
		
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
				delta--;
			}
			
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		
	}
	
	private void render() {
		
	}
	
	/*
	//******MAIN******
	public static void main(String[] args) {
		new Interfaz();
	}*/
}
