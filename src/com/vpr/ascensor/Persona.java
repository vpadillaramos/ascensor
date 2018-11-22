package com.vpr.ascensor;

import com.vpr.ascensor.Metodos;
import com.vpr.grafico.ObjPersona;
import com.vpr.grafico.Ventana;

import java.util.concurrent.Semaphore;

import com.vpr.ascensor.Ascensor;

public class Persona extends Thread{
	//Constantes
	
	//Atributos
	private static int cont;
	private int id;
	private int plantaSubir, plantaBajar;
	private int posicionEntrada, posicionSalida;
	private ObjPersona persona;
	
	//Constructor
	public Persona() {
		cont++;
		id = cont;
	}
	
	//Metodos
	public void run() {
		
		try {
			//defino la planta donde se subira y donde se bajara de forma aleatoria
			setPlantas();
			
			
			
			//Espera el ascensor
			posicionEntrada = Ascensor.semaforoEntrarAscensor[plantaSubir].getQueueLength();
			persona = Principal.addPersonaEsperado(posicionEntrada, plantaSubir);
			esperarAscensor();
			
			//Entra al ascensor y espera su planta para bajar
			Principal.removePersona(persona);
			esperarBajada();
			
			//Sale del ascensor
			Principal.addPersonaSaliendo(plantaBajar);
			salirAscensor();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void setPlantas() {
		plantaSubir = Metodos.intRandom(0, Ascensor.semaforoEntrarAscensor.length-1);
		do {
			plantaBajar = Metodos.intRandom(0, Ascensor.semaforoSalirAscensor.length-1);
		}while(plantaBajar == plantaSubir); //se debe bajar en una planta diferente a la que se subio
	}
	
	private void esperarAscensor() throws InterruptedException {
		System.out.printf("Persona %d pulsa el botón en la planta %d (%d)\n",id, plantaSubir, plantaBajar);
		Ventana.consolaPersonaEspera("[ESPERANDO] Persona " + id + " en planta " + plantaSubir);
		Ascensor.semaforoEntrarAscensor[plantaSubir].acquire();
	}
	
	private void esperarBajada() throws InterruptedException {
		System.out.printf("Persona %d se subió al ascensor. Pulsa el botón para ir a la planta %d\n",id, plantaBajar);
		Ventana.consolaPersonaSube("[EN ASCENSOR] Persona " + id + " va a planta " + plantaBajar);
		Ascensor.semaforoEsperaPersonaEntra.release();
		Ascensor.semaforoSalirAscensor[plantaBajar].acquire();
	}
	
	private void salirAscensor() {
		System.out.printf("Persona %d se baja en planta %d\n",id,plantaBajar);
		Ventana.consolaPersonaSale("[SE VA] Persona " + id + " se baja en planta " + plantaBajar);
		Ascensor.semaforoEsperaPersonaSale.release();
	}
}
