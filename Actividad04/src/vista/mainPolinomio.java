package vista;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import polinomio.Polinomio;


public class mainPolinomio {

	/**
	 * @param args
	 */
	static Polinomio[] totalPolinomios = new Polinomio[2];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Polinomio polinomioA = new Polinomio();
		//Polinomio polinomioB = new Polinomio();
		
		
		
		//totalPolinomios.add(new Polinomio());
		//totalPolinomios.add(new Polinomio());
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		int seleccion = -1;
		
		System.out.println("Menú de operación:");
		System.out.println("0- Suma de expresiones.");
		System.out.println("1- Producto de expresiones.");
		System.out.println("2- Divisón método de Ruffini");
		
		
		do {
			System.out.print("Seleccione operación:");
			try {	
				try
				{
					seleccion =Integer.parseInt(bf.readLine());
				}
				catch (NumberFormatException e)
				{
					throw new Exception("ERROR: No numerico.");
				}
				
				if (seleccion < 0 || seleccion > 2) throw new ArithmeticException();
				
				switch(seleccion)
				{
					case 0:
						introducirPolinomio(false);
						System.out.println("Vamos a ver el polinomio: " );
						System.out.println(totalPolinomios[1]);
						Polinomio polinomioSuma = totalPolinomios[0].sumaPolinomio(totalPolinomios[1]);
						System.out.println("Resultado: " + polinomioSuma.toString());
						break;
					case 1:
						introducirPolinomio(false);
						Polinomio polinomioMultiplica = totalPolinomios[0].multiplicaPolinomio(totalPolinomios[1]);
						System.out.println("Resultado: " + polinomioMultiplica.toString());
						break;
					case 2:
						introducirPolinomio(true);
						
						try
						{
							ArrayList polinomioDivide = totalPolinomios[0].dividePolinomio(totalPolinomios[1]);
							System.out.println("Polinomio1: " + totalPolinomios[0]);
							System.out.println("Polinomio2: " + totalPolinomios[1]);
							System.out.println("Cociente: " + polinomioDivide.get(1));
							System.out.println("Resto: " + polinomioDivide.get(0));
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
				System.out.println(e);
			} 
		} while (seleccion < 0 || seleccion > 2);		
		

		
		
		 
	}
	
	public static void introducirPolinomio(Boolean ruffini)
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String linea;
		Double coeficiente;
		int exponente;
		int contadorVueltas = 0;
		
		//totalPolinomios.add(new Polinomio());
		
		Polinomio polinomio = new Polinomio();
		
		System.out.println("Introduzca polinomio:");
		do {
			System.out.print("Introduzca monomio (coeficiente exponente) EJ: '1 1' -> 'X' : ");
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
							System.out.println("Segunda expresion incorrecta. EJ: 'x-2' ");
						}
						else
						{
							totalPolinomios[contadorVueltas] = polinomio;
							contadorVueltas++;
							System.out.println("Introduccion de polinomio finalizada.");
						}
						
					}
					else
					{
						totalPolinomios[contadorVueltas] = polinomio;
						polinomio = new Polinomio();
						contadorVueltas++;
						System.out.println("Introduccion de polinomio finalizada.");
					}
				}
				else
				{
					
					polinomio.setMonomio(exponente, coeficiente);
				}
					
				
				
			} catch (Exception e) {
				System.out.println("ERROR: Debe introducir un número real (coeficiente) y un número entero no negativo (exponente)" + e);
			} 
		} while (contadorVueltas < 2);
	}

}
