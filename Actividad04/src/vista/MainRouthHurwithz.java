package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.*;

import routhHurwitz.RouthHurwitz;

/**
 * Esta clase sirve como punto de ejecucion para el problema 2.
 * @author Juan Luis
 *
 */
public class MainRouthHurwithz {

	
	static RouthHurwitz rh;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static int gradoMaximo = 101;
	static double[] coeficientes;
	static int coeficientesLeidos = 0;
	static Logger logger = Logger.getLogger(MainRouthHurwithz.class.getName());
	
	private MainRouthHurwithz(){}
	
	/**
	 * Punto de inicio del programa.
	 * @param args argumento del main.
	 * @throws IOException excepcion lanzada al cometer un error en la lectura de los datos.
	 */
	public static void main(String[] args) throws IOException {
		
		leerGradoMaximo();
		
		coeficientes = new double[gradoMaximo + 1];
		
		leerCoeficientes();
		
		rh = new RouthHurwitz(gradoMaximo, coeficientes);
		rh.comprobarSistema();
		
	}
	
	private static void leerCoeficientes() throws IOException {
		logger.info("Introduzca los coeficientes de cada t�rmino (empezando por el grado m�s alto)");
		do {
			try {				
				coeficientes[coeficientesLeidos] = Double.parseDouble(bf.readLine());
				coeficientesLeidos++;
			} catch (NumberFormatException e) {
				logger.severe("ERROR: Debe introducir un n�mero real");
				coeficientesLeidos--;
			} catch (IOException e) {
				logger.info(e.getMessage());
				throw e;
			}
		} while (coeficientesLeidos < (gradoMaximo + 1));
	}

	private static void leerGradoMaximo() throws IOException {
		logger.info("Introduzca el grado m�ximo del polinomio");
		do {
			try {
				gradoMaximo = Integer.parseInt(bf.readLine());
				if (gradoMaximo < 0) {
					logger.severe("ERROR: Debe introducir un n�mero entero mayor que 0");
				}
			} catch (NumberFormatException e) {
				logger.severe("ERROR: Debe introducir un n�mero entero menor o igual que 100");
			} catch (IOException e) {
				logger.info(e.getMessage());
				throw e;
			}
		} while (gradoMaximo > 100 || gradoMaximo < 0);
	}

}
