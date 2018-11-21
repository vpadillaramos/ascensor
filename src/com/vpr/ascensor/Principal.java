package com.vpr.ascensor;

import com.vpr.grafico.Interfaz;

public class Principal {
	public static void main(String[] args) {
		//Constantes
		final int PERSONAS = 5;
		
		//Atributos
		Ascensor ascensor = new Ascensor();
		Persona[] persona = new Persona[PERSONAS];
		
		//Programa principal
		Interfaz interfaz = new Interfaz();
		
		ascensor.start();
		
		/*try {
			interfaz.subir();
			
			Thread.sleep(2000);
			interfaz.preparaAscensor();
			Thread.sleep(2000);
			
			interfaz.bajar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
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
