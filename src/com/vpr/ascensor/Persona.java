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
			}while(plantaBajar == plantaSubir);
			
			//Espera el ascensor
			System.out.printf("Persona %d está en la planta %d. Se bajará en la planta %d\n",id, plantaSubir, plantaBajar);
			Ascensor.semaforoEntrarAscensor[plantaSubir].acquire();
			
			//Entra al ascensor y espera su planta para bajar
			System.out.printf("Persona %d se subió al ascensor en la planta %d\n",id, plantaSubir);
			Ascensor.semaforoSalirAscensor[plantaBajar].acquire();
			
			//Sale del ascensor
			System.out.printf("Persona %d se baja en planta %d\n",id,plantaBajar);
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
