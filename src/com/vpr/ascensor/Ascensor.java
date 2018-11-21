package com.vpr.ascensor;

import java.util.concurrent.Semaphore;

import com.vpr.grafico.ObjAscensor;

public class Ascensor extends Thread{
	//Constantes
	private final int CAPACIDAD = 5;
	private static final int PLANTAS = 7; //*****MAXIMO DE PLANTAS PARA LA INTERFAZ GRAFICA******
	private final int TIEMPO_MOVIMIENTO = 3000;
	
	//Atributos
	public static Semaphore[] semaforoEntrarAscensor = new Semaphore[PLANTAS]; //1 semaforo por cada planta donde las personas esperan el ascensor
	public static Semaphore[] semaforoSalirAscensor = new Semaphore[PLANTAS];
	public static Semaphore semaforoEsperaPersonaEntra = new Semaphore(0);
	public static Semaphore semaforoEsperaPersonaSale = new Semaphore(0);
	private boolean activo = true;
	private int contCapacidad,plantaActual;
	private int aux = 0;
	
	
	
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
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		do {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			//prioridad de subida
			boolean bucleHecho = false;
			aux = plantaActual;
			while(hayPlantaSubiendo(plantaActual) || hayBajadaSubiendo(plantaActual)) {
				bucleHecho = true;
				try {
					System.out.printf("[PLANTA %d]\n",plantaActual);
					if(plantaActual != aux)
						ObjAscensor.subir();
					Thread.sleep(TIEMPO_MOVIMIENTO);
					ObjAscensor.preparaAscensor();

					//compruebo si hay capacidad en el ascensor
					if(contCapacidad >= CAPACIDAD)
						System.out.printf("¡Está lleno! (%d)\n",contCapacidad);
					else {
						while(contCapacidad < CAPACIDAD && semaforoEntrarAscensor[plantaActual].hasQueuedThreads()) {
							contCapacidad++;;
							semaforoEntrarAscensor[plantaActual].release();
							semaforoEsperaPersonaEntra.acquire();
							
							System.out.printf("capaciad: %d\n",contCapacidad);
						}
					}

					//si hay alguien que se quiera bajar
					if(semaforoSalirAscensor[plantaActual].hasQueuedThreads()) {
						contCapacidad = contCapacidad - semaforoSalirAscensor[plantaActual].getQueueLength();
						System.out.printf("capaciad: %d\n",contCapacidad);
						semaforoSalirAscensor[plantaActual].release(semaforoSalirAscensor[plantaActual].getQueueLength());
						semaforoEsperaPersonaSale.acquire();
					}


					plantaActual++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if(bucleHecho)
				plantaActual--; //resto 1 ya que sale del bucle con 1 de mas
			
			bucleHecho = false;
			aux = plantaActual;
			while(hayPlantaBajando(plantaActual) || hayBajadaBajando(plantaActual)) {
				bucleHecho = true;
				try {
					System.out.printf("[PLANTA %d]\n",plantaActual);
					if(plantaActual != aux)
						ObjAscensor.bajar();
					Thread.sleep(TIEMPO_MOVIMIENTO);
					ObjAscensor.preparaAscensor();

					//compruebo si hay capacidad en el ascensor
					if(contCapacidad >= CAPACIDAD)
						System.out.printf("¡Está lleno! (%d)\n",contCapacidad);
					else {
						while(contCapacidad < CAPACIDAD && semaforoEntrarAscensor[plantaActual].hasQueuedThreads()) {
							contCapacidad++;;
							semaforoEntrarAscensor[plantaActual].release();
							semaforoEsperaPersonaEntra.acquire();
							System.out.printf("capaciad: %d\n",contCapacidad);
						}
					}

					//si hay alguien que se quiera bajar
					if(semaforoSalirAscensor[plantaActual].hasQueuedThreads()) {
						contCapacidad = contCapacidad - semaforoSalirAscensor[plantaActual].getQueueLength();
						System.out.printf("capaciad: %d\n",contCapacidad);
						semaforoSalirAscensor[plantaActual].release(semaforoSalirAscensor[plantaActual].getQueueLength());
						semaforoEsperaPersonaSale.acquire();
					}


					plantaActual--;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(bucleHecho)
				plantaActual++; //sumo 1 ya que sale del bucle con 1 de menos

			
			/*if(plantaActual >= PLANTAS)
				plantaActual--;*/
			
		}while(activo);
	}
	
	//METODOS
	public void setActivo(boolean b) {
		activo = b;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	//devuelve la planta siguiente en sentido de subida a partir de una posicion indicada
	public int damePlantaSubiendo(int plantaActual) {
		int i = plantaActual;
		while(i<PLANTAS && !semaforoEntrarAscensor[i].hasQueuedThreads()) {
			i++;
		}
		return i; //si i==5 no hay nadie
	}
	
	public int dameBajadaSubiendo(int plantaActual) {
		int i = plantaActual;
		
		while(i<PLANTAS && !semaforoSalirAscensor[i].hasQueuedThreads()) {
			i++;
		}
		
		return i;
	}
	
	public boolean hayPlantaSubiendo(int plantaActual) {
		int i = plantaActual;
		
		while(i<PLANTAS && !semaforoEntrarAscensor[i].hasQueuedThreads()) {
			i++;
		}
		
		return i >= PLANTAS ? false:true;
	}
	
	public boolean hayBajadaSubiendo(int plantaActual) {
		int i = plantaActual;

		while(i<PLANTAS && !semaforoSalirAscensor[i].hasQueuedThreads()) {
			i++;
		}

		return i >= PLANTAS ? false:true; //si i==PLANTAS no hay gente que quiera bajarse en sentido subida
	}
	
	public int damePlantaBajando(int plantaActual) {
		int i = plantaActual;
		
		while(i>=0 && !semaforoEntrarAscensor[i].hasQueuedThreads()) {
			i--;
		}
		
		return i; //si i==-1 no hay nadie mas esperando el ascensor
	}
	
	public int dameBajadaBajando(int plantaActual) {
		int i = plantaActual;
		
		while(i>=0 && !semaforoSalirAscensor[i].hasQueuedThreads()) {
			i--;
		}
		
		return i; //si i==-1 no hay nadie que quiera bajarse en setido bajada
	}
	
	public boolean hayPlantaBajando(int plantaActual) {
		int i = plantaActual;
		
		while(i>=0 && !semaforoEntrarAscensor[i].hasQueuedThreads()) {
			i--;
		}
		
		return i < 0 ? false:true;
	}
	
	public boolean hayBajadaBajando(int plantaActual) {
		int i = plantaActual;

		while(i>=0 && !semaforoSalirAscensor[i].hasQueuedThreads()) {
			i--;
		}

		return i < 0 ? false:true;
	}
}
