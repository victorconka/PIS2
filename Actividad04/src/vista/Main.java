package vista;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import vista.mainPolinomio;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		int seleccion = -1;

		do {
			System.out.println("***************************************");
			System.out.println("Menú de operación:");
			System.out.println("1- Ejercicio 1.");
			System.out.println("2- Ejercicio 2.");
			System.out.println("3- Ejercicio 3.");
			System.out.print("Seleccione operación:");
			try {	
				
				seleccion =Integer.parseInt(bf.readLine());
		
				switch (seleccion)
				{
					case 1:
						System.out.println("***************************************");
						mainPolinomio.main(args);
						break;
					case 2:
						System.out.println("***************************************");
						mainRouthHurwithz.main(args);
						break;
					case 3:
						System.out.println("***************************************");
						mainInterpolacionLineal.main(args);
						break;
					default:
						System.out.println("Opción incorrecta");
						break;
				}
			}
			catch (Exception e)
			{
				System.out.println("Error");
			}
		} while (true);
		
		
		
	}

}
