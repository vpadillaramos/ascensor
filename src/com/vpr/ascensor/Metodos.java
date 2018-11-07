package com.vpr.ascensor;

import java.util.Random;

public class Metodos {
	//Metodo que devuelve un numero entero aleatorio
	public static int intRandom(int min, int max) {
		Random num = new Random();
		return (min + num.nextInt(max-min+1));
	}
}
