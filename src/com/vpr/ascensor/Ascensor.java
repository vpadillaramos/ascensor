package com.vpr.ascensor;

import java.util.concurrent.Semaphore;

public class Ascensor extends Thread{
	//Constantes
	private final int CAPACIDAD = 5;
	private static final int PLANTAS = 5;
	
	//Atributos
	public static Semaphore[] semaforoEntrarAscensor = new Semaphore[PLANTAS]; //1 semaforo por cada planta donde las personas esperan el ascensor
	public static Semaphore[] semaforoSalirAscensor = new Semaphore[PLANTAS];
	private boolean acabar = false;
	private int contCapacidad;
	
	//Constructor
	public Ascensor() {
		//Inicializo los semaforos de las plantas
		for(int i=0; i<semaforoEntrarAscensor.length; i++) {
			semaforoEntrarAscensor[i] = new Semaphore(0);
			semaforoSalirAscensor[i] = new Semaphore(0);
		}
	}
	
	//Metodos
	public void run() {
		contCapacidad = 0;
		do {
			
			for(int i=0; i<PLANTAS; i++) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("[Planta %d] Esperando %d\n",i,semaforoEntrarAscensor[i].getQueueLength());
				
				//Compruebo si hay personas esperando a entrar al ascensor
				
				while(contCapacidad<CAPACIDAD && semaforoEntrarAscensor[i].hasQueuedThreads()) {
					semaforoEntrarAscensor[i].release();
					contCapacidad++;
					System.out.printf("contador: %d\n",contCapacidad);
				}
				
				//Compruebo si en cada planta hay alguien que quiere bajarse
				if(semaforoSalirAscensor[i].hasQueuedThreads()) {
					contCapacidad = contCapacidad - semaforoSalirAscensor[i].getQueueLength();
					semaforoSalirAscensor[i].release(semaforoSalirAscensor[i].getQueueLength());
					System.out.printf("contador: %d\n",contCapacidad);
				}
			}
			
		}while(!acabar);
	}
	
	public void setAcabar(boolean b) {
		acabar = b;
	}
}
