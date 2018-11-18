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
		
		for(int i=0; i<persona.length; i++) {
			persona[i] = new Persona();
			persona[i].start();
		}
		
		for(int i=0; i<persona.length; i++)
			try {
				persona[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		//Acabo el hilo del ascensor
		ascensor.setAcabar(true);
	}
}
