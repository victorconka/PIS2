package vista;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;


import vista.mainPolinomio;

/**
 * Menu principal
 * @author Usuario
 *
 */
public class Main {
	
	/**
	 * constructor privado
	 */
	private Main(){
	}
	/**
	 * menu principal
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Logger logger = Logger.getLogger("InfoLogging"); //logger para sustituir syso
		String line= "***************************************";
		
		int seleccion;

		do {
			logger.info(line);
			logger.info("Menú de operación:");
			logger.info("1- Ejercicio 1.");
			logger.info("2- Ejercicio 2.");
			logger.info("3- Ejercicio 3.");
			logger.info("Seleccione operación:");
			try {	
				
				seleccion =Integer.parseInt(bf.readLine());
		
				switch (seleccion)
				{
					case 1:
						logger.info(line);
						mainPolinomio.main(args);
						break;
					case 2:
						logger.info(line);
						mainRouthHurwithz.main(args);
						break;
					case 3:
						logger.info(line);
						MainInterpolacionLineal.main(args);
						break;
					default:
						logger.info("Opción incorrecta");
						break;
				}
			}
			catch (Exception e)
			{
				logger.info("Error" + e);
			}
		} while (true);
		
		
		
	}

}
