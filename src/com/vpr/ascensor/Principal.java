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
		
		do {
			//cont++;
			Persona p = new Persona();
			p.start();
			try {
				Thread.sleep(Metodos.intRandom(2000, 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(ascensor.isActivo());
		
		
		//Acabo el hilo del ascensor
		//ascensor.setActivo(true);
	}
}
