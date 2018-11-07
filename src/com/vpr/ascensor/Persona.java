package com.vpr.ascensor;

import com.vpr.ascensor.Metodos;
import com.vpr.ascensor.Ascensor;

public class Persona extends Thread{
	//Constantes
	
	//Atributos
	private static int cont;
	private int id;
	
	//Constructor
	public Persona() {
		cont++;
		id = cont;
	}
	
	//Metodos
	public void run() {
		try {
			//Cada persona se para en una planta aleatoria hasta que llegue el ascensor
			Ascensor.semaforoPlanta[Metodos.intRandom(0, Ascensor.semaforoPlanta.length-1)].acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
