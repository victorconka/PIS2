package polinomio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;

public class Polinomio 
{
	// HashMap<Exponente , Coeficiente>
	// Polinomio: combinacion de MONOMIOS
	HashMap<Integer, Double> polinomio = new HashMap<Integer, Double>();


	public Polinomio()
	{
		
	}
	
	public HashMap<Integer, Double> getPolinomio() {
		return polinomio;
	}

	public void setPolinomio(HashMap<Integer, Double> polinomio) {
		this.polinomio.putAll(polinomio);
	}

	public void setMonomio(int exponente, double coeficiente) 
	{
		// Si el exponente existe, realizar cuenta.
		if (polinomio.get(exponente) != null)
		{
			double coefGet = polinomio.get(exponente);
			coefGet += coeficiente;
			polinomio.put(exponente, coefGet);
		}
		else // Si el exponente no existe, dar de alta
		{
			polinomio.put(exponente, coeficiente);
		}
		
	}
	
	public Polinomio sumaPolinomio(Polinomio polinomioASumar)
	{
		
		Polinomio resultado = new Polinomio();
		
		HashMap<Integer, Double> hashPolinomioASumar = polinomioASumar.getPolinomio(); // POLINOMIO DE ENTRADA
		
		resultado.setPolinomio(this.polinomio); // PUTALL DEL POLINOMIO DE LA CLASE
		
		for (Entry<Integer, Double> mapaIterado : hashPolinomioASumar.entrySet()) {
			
			resultado.setMonomio(mapaIterado.getKey(),  mapaIterado.getValue()); 
		    //System.out.println("clave=" + mapaIterado.getKey() + ", valor=" + mapaIterado.getValue());
		}
		
		//resultado.setMonomio(exponente, coeficiente);
		
		
		return resultado;
	}
	

	
	public void multiplicaPolinomio()
	{
		
	}
	
	public void dividePolinomio()
	{
		
	}
	
	@Override
	public String toString()
	{
		DecimalFormat formatoDecimalCeros = new DecimalFormat();
		formatoDecimalCeros.setDecimalSeparatorAlwaysShown(false);
		
		String resultado ="";
		int contadorMonomios=0;
		for (Entry<Integer, Double> mapaIterado : polinomio.entrySet()) 
		{
			contadorMonomios++;
			// Si el coeficiente es 0, no se muestra
			if (mapaIterado.getValue() == 0)
				continue;
			
			//Eliminar formato Double si es necesario
			String coeficienteLeido=formatoDecimalCeros.format(mapaIterado.getValue());
			
			
			//Elimina el 1 deL "1X"
			if ((mapaIterado.getKey() == 1 && mapaIterado.getValue() == 1))
				coeficienteLeido = "";

			//Elimina el 1 deL "-1X"
			if ((mapaIterado.getKey() == 1 && mapaIterado.getValue() == -1))
				coeficienteLeido = "-";
			
			//Comprobar si el coeficiente es negativo o no para mostrar su signo.
			//Si no es el monomio de mayor grado, mostrar signo +
			if (polinomio.size() != contadorMonomios && mapaIterado.getValue() > 0)
					coeficienteLeido = "+" +  coeficienteLeido;
			
			// Comprobar el exponente para mostrar X o no
			switch (mapaIterado.getKey()) {
				case 0: // Exponente 0 -> Sin "X"
					resultado = coeficienteLeido  + resultado;
					break;
				case 1: // Exponente 1 -> Con "X"
					resultado = coeficienteLeido + "X" + resultado;
					break;
				default: // Exponente > 1 -> Con "X^"
					resultado = coeficienteLeido + "X^" + mapaIterado.getKey()  + resultado;
					break;
			}
			
		
		}
		
		System.out.println(polinomio.values());
		return resultado;
	}

}
