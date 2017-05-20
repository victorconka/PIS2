package vista;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import polinomio.Polinomio;


public class mainPolinomio {

	/**
	 * @param args
	 */
	static Polinomio[] totalPolinomios = new Polinomio[2];
	private static Logger logger = Logger.getLogger("InfoLogging"); //logger para sustituir syso

	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		int seleccion = -1;
		
		logger.info("Menú de operación:");
		logger.info("0- Suma de expresiones.");
		logger.info("1- Producto de expresiones.");
		logger.info("2- Divisón método de Ruffini");
		
		
		do {
			logger.info("Seleccione operación:");
			try {	
				try
				{
					seleccion =Integer.parseInt(bf.readLine());
				}
				catch (Exception e)
				{
					throw new Exception("ERROR: No numerico." + e);
				}
				
				if (seleccion < 0 || seleccion > 2) throw new ArithmeticException();
				
				switch(seleccion)
				{
					case 0:
						introducirPolinomio(false);
						logger.info("Vamos a ver el polinomio: " );
						logger.info(String.valueOf(totalPolinomios[1]));
						Polinomio polinomioSuma = totalPolinomios[0].sumaPolinomio(totalPolinomios[1]);
						logger.info("Resultado: " + polinomioSuma.toString());
						break;
					case 1:
						introducirPolinomio(false);
						Polinomio polinomioMultiplica = totalPolinomios[0].multiplicaPolinomio(totalPolinomios[1]);
						logger.info("Resultado: " + polinomioMultiplica.toString());
						break;
					case 2:
						introducirPolinomio(true);
						
						try
						{
							ArrayList polinomioDivide = totalPolinomios[0].dividePolinomio(totalPolinomios[1]);
							logger.info("Polinomio1: " + totalPolinomios[0]);
							logger.info("Polinomio2: " + totalPolinomios[1]);
							logger.info("Cociente: " + polinomioDivide.get(1));
							logger.info("Resto: " + polinomioDivide.get(0));
						}
						
						catch (Exception e)
					    {
							throw new Exception("ERROR: Segundo monomio incorrecto. EJ: 'x-2'" + e);
					    } 
						

						break;
					default:
						throw new Exception("ERROR: Debe introducir un número del 0 al 2");
				}
				
				
			} catch (Exception e) {
				logger.info("Error: " +e);
			} 
		} while (seleccion < 0 || seleccion > 2);		
		

		
		
		 
	}
	/**
	 * pide introducir ruffini
	 * @param ruffini
	 */
	public static void introducirPolinomio(Boolean ruffini)
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String linea;
		Double coeficiente;
		int exponente;
		int contadorVueltas = 0;
				
		Polinomio polinomio = new Polinomio();
		
		logger.info("Introduzca polinomio:");
		do {
			logger.info("Introduzca monomio (coeficiente exponente) EJ: '1 1' -> 'X' : ");
			try {				
				linea = bf.readLine();
				
				
				String[] entradaSplit = linea.split(" ");
				
				
				coeficiente = Double.parseDouble(entradaSplit[0]);
				exponente = Integer.parseInt(entradaSplit[1]);
				
				if (coeficiente == 0 && exponente == 0)
				{
					if (contadorVueltas == 1 && ruffini)
					{
						if(polinomio.getPolinomio().size() > 2)
						{
							polinomio = new Polinomio();
							logger.info("Segunda expresion incorrecta. EJ: 'x-2' ");
						}
						else
						{
							totalPolinomios[contadorVueltas] = polinomio;
							contadorVueltas++;
							logger.info("Introduccion de polinomio finalizada.");
						}
						
					}
					else
					{
						totalPolinomios[contadorVueltas] = polinomio;
						polinomio = new Polinomio();
						contadorVueltas++;
						logger.info("Introduccion de polinomio finalizada.");
					}
				}
				else
				{
					
					polinomio.setMonomio(exponente, coeficiente);
				}
					
				
				
			} catch (Exception e) {
				logger.info("ERROR: Debe introducir un número real (coeficiente) y un número entero no negativo (exponente)" + e);
			} 
		} while (contadorVueltas < 2);
	}

}
