package com.vpr.ascensor;

import java.util.concurrent.Semaphore;

public class Ascensor extends Thread{
	//Constantes
	private final int CAPACIDAD = 5;
	private static final int PLANTAS = 10;
	
	//Atributos
	public static Semaphore[] semaforoEntrarAscensor = new Semaphore[PLANTAS]; //1 semaforo por cada planta donde las personas esperan el ascensor
	public static Semaphore[] semaforoSalirAscensor = new Semaphore[PLANTAS];
	public static Semaphore semaforoEsperaPersonaEntra = new Semaphore(0);
	public static Semaphore semaforoEsperaPersonaSale = new Semaphore(0);
	private boolean activo = true;
	private int contCapacidad,plantaActual;
	
	private static int tamPrioridad = 0;
	private static boolean[] prioridad = new boolean[tamPrioridad]; //true es subir, false bajar
	private static Semaphore semaforoPrioridad = new Semaphore(1);
	private static int prioridadActual = 0;
	
	
	
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
		//la primera vez sube siempre ya que empieza en la planta 0
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//TODO arreglar el maximo de capacidad
		
		//la primera vez
		int primera = damePlantaSubiendo(0);
		contCapacidad = 0;
		for(plantaActual=0; plantaActual<=primera; plantaActual++) {
			try {
				System.out.printf("[PLANTA %d]\n",plantaActual);
				Thread.sleep(1500);
				if(semaforoEntrarAscensor[plantaActual].hasQueuedThreads()) {
					if(contCapacidad >= CAPACIDAD)
						System.out.printf("¡Está lleno! (%d)\n",contCapacidad);
					else {
						contCapacidad = contCapacidad + semaforoEntrarAscensor[plantaActual].getQueueLength();
						System.out.printf("capaciad: %d\n",contCapacidad);
						semaforoEntrarAscensor[plantaActual].release(semaforoEntrarAscensor[plantaActual].getQueueLength());
						semaforoEsperaPersonaEntra.acquire();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		do {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			for(boolean b:prioridad)
				System.out.print(b+" ");
			System.out.println("");
			
			//prioridad de subida
			if(prioridadActual < prioridad.length && prioridad[prioridadActual]) {
				boolean bucleHecho = false;
				while(hayPlantaSubiendo(plantaActual) || hayBajadaSubiendo(plantaActual)) {
					bucleHecho = true;
					try {
						System.out.printf("[PLANTA %d]\n",plantaActual);
						Thread.sleep(1500);
						//si en la planta actual hay alguien se sube
						if(semaforoEntrarAscensor[plantaActual].hasQueuedThreads()) {
							if(contCapacidad >= CAPACIDAD)
								System.out.printf("¡Está lleno! (%d)\n",contCapacidad);
							else {
								contCapacidad = contCapacidad + semaforoEntrarAscensor[plantaActual].getQueueLength();
								System.out.printf("capaciad: %d\n",contCapacidad);
								semaforoEntrarAscensor[plantaActual].release(semaforoEntrarAscensor[plantaActual].getQueueLength());
								semaforoEsperaPersonaEntra.acquire();
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
			}
			else {
				boolean bucleHecho = false;
				while(hayPlantaBajando(plantaActual) || hayBajadaBajando(plantaActual)) {
					bucleHecho = true;
					try {
						System.out.printf("[PLANTA %d]\n",plantaActual);
						Thread.sleep(1500);
						//si en la planta actual hay alguien se sube
						if(semaforoEntrarAscensor[plantaActual].hasQueuedThreads()) {
							if(contCapacidad >= CAPACIDAD)
								System.out.printf("¡Está lleno! (%d)\n",contCapacidad);
							else {
								contCapacidad = contCapacidad + semaforoEntrarAscensor[plantaActual].getQueueLength();
								System.out.printf("capaciad: %d\n",contCapacidad);
								semaforoEntrarAscensor[plantaActual].release(semaforoEntrarAscensor[plantaActual].getQueueLength());
								semaforoEsperaPersonaEntra.acquire();
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
			}
			
			if(plantaActual >= PLANTAS)
				plantaActual--;
			prioridadActual++;
			
		}while(activo);
	}
	
	//METODOS
	public void setActivo(boolean b) {
		activo = b;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public static void setPrioridad(boolean b) {
		try {
			semaforoPrioridad.acquire();
			
			boolean[] aux = new boolean[prioridad.length];
			System.arraycopy(prioridad, 0, aux, 0, prioridad.length);
			tamPrioridad++;
			prioridad = new boolean[tamPrioridad];
			System.arraycopy(aux, 0, prioridad, 0, aux.length);
			prioridad[prioridad.length-1] = b;
			
			semaforoPrioridad.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
