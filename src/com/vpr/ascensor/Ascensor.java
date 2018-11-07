package com.vpr.ascensor;

import java.util.concurrent.Semaphore;

public class Ascensor extends Thread{
	//Constantes
	private final int CAPACIDAD = 5;
	private static final int PLANTAS = 10;
	
	//Atributos
	public static Semaphore[] semaforoPlanta = new Semaphore[PLANTAS]; //1 semaforo por cada planta donde las personas esperan el ascensor
	private boolean acabar = false;
	private int contCapacidad;
	
	//Constructor
	public Ascensor() {
		//Inicializo los semaforos de las plantas
		for(int i=0; i<semaforoPlanta.length; i++) {
			semaforoPlanta[i] = new Semaphore(0);
		}
	}
	
	//Metodos
	public void run() {
		do {
			
			for(int i=0; i<semaforoPlanta.length; i++) {
				if(semaforoPlanta[i].hasQueuedThreads()) {
					
				}
			}
			
		}while(!acabar);
	}
	
	public void setAcabar(boolean b) {
		acabar = b;
	}
}
