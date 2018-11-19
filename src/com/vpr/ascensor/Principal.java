package com.vpr.ascensor;

public class Principal {
	public static void main(String[] args) {
		//Constantes
		final int PERSONAS = 5;
		
		//Atributos
		Ascensor ascensor = new Ascensor();
		Persona[] persona = new Persona[PERSONAS];
		
		//Programa principal
		ascensor.start();
		
		/*
		for(int i=0; i<persona.length; i++) {
			Persona p = new Persona();
			p.start();
		}*/
		
		do {
			Persona p = new Persona();
			p.start();
			try {
				Thread.sleep(Metodos.intRandom(2000, 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(ascensor.isActivo());
		
		
		/*
		try {
			Thread.sleep(Metodos.intRandom(2000, 4000));
			Persona p = new Persona();
			Persona p2 = new Persona();
			p.start();
			p2.start();
			p.join();
			p2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		//Acabo el hilo del ascensor
		//ascensor.setActivo(true);
	}
}
