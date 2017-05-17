package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.*;

import routhHurwitz.RouthHurwitz;

public class mainRouthHurwithz {

	public static void main(String[] args) throws IOException {
		RouthHurwitz rh;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int gradoMaximo = 101;
		double[] coeficientes;
		int coeficientesLeidos = 0;
		Logger logger = Logger.getLogger(mainRouthHurwithz.class.getName());

		logger.info("Introduzca el grado máximo del polinomio");
		do {
			try {
				gradoMaximo = Integer.parseInt(bf.readLine());
				if (gradoMaximo < 0) {
					logger.severe("ERROR: Debe introducir un número entero mayor que 0");
				}
			} catch (NumberFormatException e) {
				logger.severe("ERROR: Debe introducir un número entero menor o igual que 100");
			} catch (IOException e) {
				logger.info(e.getMessage());
				throw e;
			}
		} while (gradoMaximo > 100 || gradoMaximo < 0);
		
		coeficientes = new double[gradoMaximo + 1];
		
		logger.info("Introduzca los coeficientes de cada término (empezando por el grado más alto)");
		do {
			try {				
				coeficientes[coeficientesLeidos] = Double.parseDouble(bf.readLine());
				coeficientesLeidos++;
			} catch (NumberFormatException e) {
				logger.severe("ERROR: Debe introducir un número real");
				coeficientesLeidos--;
			} catch (IOException e) {
				logger.info(e.getMessage());
				throw e;
			}
		} while (coeficientesLeidos < (gradoMaximo + 1));
		
		rh = new RouthHurwitz(gradoMaximo, coeficientes);
		rh.comprobarSistema();
		
	}

}
