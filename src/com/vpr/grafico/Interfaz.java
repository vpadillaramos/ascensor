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
	public static final int WIDTH = 980; //640
	public static final int HEIGHT = 735; //WIDTH/12*9
	
	//Atirbutos
	private static final long serialVersionUID = 2601176506843040199L;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	//Constructor
	public Interfaz() {
		new Ventana("Ascensor", this, WIDTH, HEIGHT);
		handler = new Handler();
		handler.addObjeto(new ObjAscensor(43, HEIGHT-160, ID.Ascensor)); //añadir el objeto ascensor //560 470
		handler.addObjeto(new Edificio(20, 0, ID.Edificio));
		addPisos();
		addNumeros();
		handler.addObjeto(new Aforo(90, HEIGHT-100, ID.Aforo));
		
		/*
		//piso 0
		handler.addObjeto(new ObjPersona(140, HEIGHT-115, ID.Persona, true));
		handler.addObjeto(new ObjPersona(160, HEIGHT-115, ID.Persona, true));
		handler.addObjeto(new ObjPersona(140, HEIGHT-150, ID.Persona, false));
		handler.addObjeto(new ObjPersona(160, HEIGHT-150, ID.Persona, false));
		
		//piso 1
		handler.addObjeto(new ObjPersona(140, HEIGHT-205, ID.Persona, true));
		handler.addObjeto(new ObjPersona(160, HEIGHT-205, ID.Persona, true));
		handler.addObjeto(new ObjPersona(140, HEIGHT-240, ID.Persona, false));
		handler.addObjeto(new ObjPersona(160, HEIGHT-240, ID.Persona, false));*/
		
		for(int i = 0; i < 7; i++) {
			System.out.print(i + " ");
			addPersonaEsperando(i);
		}
		
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
	
	private void addPisos() {
		int cont = 90;
		int inicial = -20;
		for(int i = 0; i <= 7; i++) {
			inicial += cont;
			int altura = HEIGHT-inicial;
			handler.addObjeto(new Piso(40, altura, ID.Piso));
		}
	}
	
	private void addNumeros() {
		int cont = 90;
		int inicial = 45;
		for(int i = 0; i < 7; i++) {
			inicial += cont;
			int altura = HEIGHT-inicial;
			handler.addObjeto(new Numero(0, altura, ID.Numero, i));
		}
	}
	
	public void addPersonaEsperando(int piso) {
		int aumento = 90 * piso;
		int x = 140; //esto depende de las personas que haya esperando
		int y = HEIGHT - (115 + aumento); //esto depende del piso
		handler.addObjeto(new ObjPersona(x, y, ID.Persona, true));
	}
}
