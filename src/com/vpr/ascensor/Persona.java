package com.vpr.ascensor;

import com.vpr.ascensor.Metodos;
import com.vpr.ascensor.Ascensor;

public class Persona extends Thread{
	//Constantes
	
	//Atributos
	private static int cont;
	private int id;
	private int plantaSubir, plantaBajar;
	
	//Constructor
	public Persona() {
		cont++;
		id = cont;
	}
	
	//Metodos
	public void run() {
		try {
			
			plantaSubir = Metodos.intRandom(0, Ascensor.semaforoEntrarAscensor.length-1);
			do {
				plantaBajar = Metodos.intRandom(0, Ascensor.semaforoSalirAscensor.length-1);
			}while(plantaBajar == plantaSubir); //se debe bajar en una planta diferente a la que se subio
			
			if(plantaSubir<plantaBajar)
				Ascensor.setPrioridad(true);
			else
				Ascensor.setPrioridad(false);
				
			
			//Espera el ascensor
			//System.out.printf("Persona %d est� en la planta %d. Se bajar� en la planta %d\n",id, plantaSubir, plantaBajar);
			System.out.printf("Persona %d pulsa el bot�n en la planta %d (%d)\n",id, plantaSubir, plantaBajar);
			Ascensor.semaforoEntrarAscensor[plantaSubir].acquire();
			
			//Entra al ascensor y espera su planta para bajar
			System.out.printf("Persona %d se subi� al ascensor. Pulsa el bot�n para ir a la planta %d\n",id, plantaBajar);
			Ascensor.semaforoEsperaPersonaEntra.release();
			Ascensor.semaforoSalirAscensor[plantaBajar].acquire();
			
			//Sale del ascensor
			System.out.printf("Persona %d se baja en planta %d\n",id,plantaBajar);
			Ascensor.semaforoEsperaPersonaSale.release();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
