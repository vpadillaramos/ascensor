package com.vpr.ascensor;

public class Principal {
	public static void main(String[] args) {
		//Constantes
		final int PERSONAS = 5;
		
		//Atributos
		Ascensor ascensor = new Ascensor();
		Persona[] persona = new Persona[100];
		
		//Programa principal
		ascensor.start();
		
		for(int i=0; i<PERSONAS; i++) {
			persona[i] = new Persona();
			persona[i].start();
		}
		
		for(int i=0; i<PERSONAS; i++)
			try {
				persona[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		ascensor.setAcabar(true);
	}
}
