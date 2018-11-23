package com.vpr.ascensor;

import com.vpr.grafico.Interfaz;
import com.vpr.grafico.ObjPersona;

public class Principal {
	//Atributos
	private static Interfaz interfaz;
	
	public static void main(String[] args) {
		//Constantes
		final int PERSONAS = 20;
		
		//Atributos
		Ascensor ascensor = new Ascensor();
		Persona[] persona = new Persona[PERSONAS];
		
		//Programa principal
		interfaz = new Interfaz();
		
		ascensor.start();
		
		for(int i = 0; i < persona.length; i++) {
			try {
				persona[i] = new Persona();
				persona[i].start();
				Thread.sleep(Metodos.intRandom(2000, 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		for(int i = 0; i < persona.length; i++) {
			try {
				persona[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		//Bucle para generar personas de forma indefinida
		/*
		do {
			Persona p = new Persona();
			p.start();
			
			try {
				Thread.sleep(Metodos.intRandom(2000, 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(ascensor.isActivo());*/
		
		
		//Acabo el hilo del ascensor
		ascensor.setActivo(false);
	}
	
	public static ObjPersona addPersonaEsperado(int posicionEntrada, int piso) {
		ObjPersona persona = interfaz.addPersonaEsperando(posicionEntrada, piso);
		return persona;
	}
	
	public static void removePersona(ObjPersona persona) {
		interfaz.removePersona(persona);
	}
	
	public static void addPersonaSaliendo(int posicionSalida, int piso) {
		try {
			interfaz.addPersonaSaliendo(posicionSalida, piso);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
