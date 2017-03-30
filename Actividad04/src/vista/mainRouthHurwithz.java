package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import routhHurwitz.RouthHurwitz;

public class mainRouthHurwithz {

	public static void main(String[] args) {
		RouthHurwitz rh;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int gradoMaximo = 101;
		double[] coeficientes;
		int coeficientesLeidos = 0;
		
		System.out.println("Introduzca el grado m�ximo del polinomio");
		do {
			try {
				gradoMaximo = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Debe introducir un n�mero entero menor o igual que 100");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (gradoMaximo > 100);
		
		coeficientes = new double[gradoMaximo + 1];
		
		System.out.println("Introduzca los coeficientes de cada t�rmino (empezando por el grado m�s alto)");
		do {
			try {				
				coeficientes[coeficientesLeidos] = Double.parseDouble(bf.readLine());
				coeficientesLeidos++;
			} catch (NumberFormatException e) {
				System.out.println("ERROR: DEbe introducir un n�mero real");
				coeficientesLeidos--;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (coeficientesLeidos < (gradoMaximo + 1));
		
		rh = new RouthHurwitz(gradoMaximo, coeficientes);
		rh.ComprobarSistema();
		
	}

}
