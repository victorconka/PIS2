package polinomio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;

public class Polinomio 
{
	// HashMap<Exponente , Coeficiente>
	// Polinomio: combinacion de MONOMIOS
	HashMap<Integer, Double> polinomio = new HashMap<Integer, Double>();


	
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
	// Obtener valor de una clave
	public Double getMonomio(int key) 
	{
		return polinomio.get(key);
	}
	

	// Put directo a monomio
	public void putMonomio(int exponente, Double coeficiente)
	{
		polinomio.put(exponente, coeficiente);
	}
	
	public Polinomio sumaPolinomio(Polinomio polinomioASumar)
	{
		
		Polinomio resultado = new Polinomio();
		
		HashMap<Integer, Double> hashPolinomioASumar = polinomioASumar.getPolinomio(); // POLINOMIO DE ENTRADA
		
		resultado.setPolinomio(this.polinomio); // PUTALL DEL POLINOMIO DE LA CLASE
		
		for (Entry<Integer, Double> mapaIterado : hashPolinomioASumar.entrySet()) {
			
			resultado.setMonomio(mapaIterado.getKey(),  mapaIterado.getValue()); 
		}
		
		return resultado;
	}
	

	
	public Polinomio multiplicaPolinomio(Polinomio polinomioAMultiplicar)
	{
		Polinomio resultado = new Polinomio();
		
		HashMap<Integer, Double> hashPolinomioAMultiplicar = polinomioAMultiplicar.getPolinomio(); // POLINOMIO DE ENTRADA
		
// EJEMPLO:		
//		   -3x2  +  2x4  -  8  -  x3   +  5x			FACTOR ARRIBA
//
//		   *					        -5x4			FACTOR ABAJO
//		   _________________________________
//		   15x6 - 10x8 + 40x4 + 5 x7 - 25x5				RESULTADO
		
		for (Entry<Integer, Double> mapaIteradoAbajo : hashPolinomioAMultiplicar.entrySet()) 
		{
			for (Entry<Integer, Double> mapaIteradoArriba : polinomio.entrySet()) 
			{
				Double numMultiplicado = mapaIteradoAbajo.getValue() * mapaIteradoArriba.getValue();
				int exponenteSumado    = mapaIteradoAbajo.getKey()   + mapaIteradoArriba.getKey();
				resultado.setMonomio(exponenteSumado,  numMultiplicado); 
			}
		}
		
		return resultado;
	}
	// Explicacion ruffini: http://www.vitutor.com/ab/p/a_8.html
	public ArrayList dividePolinomio(Polinomio polinomioADividir)
	{
		Polinomio resultado = new Polinomio();
		
		
		if (polinomioADividir.getMonomio(1) > 1)
		{
			Double coeficienteX = polinomioADividir.getMonomio(1) / polinomioADividir.getMonomio(1);
			Double coeficienteIndependiente = polinomioADividir.getMonomio(0) / polinomioADividir.getMonomio(1);
			polinomioADividir.putMonomio(0, coeficienteIndependiente);
			polinomioADividir.putMonomio(1, coeficienteX);
		}
		
		// Descubre ultima Key para tratar de LAST a FIRST
		int ultimaKeyMapa = 0;
		for (Entry<Integer, Double> mapaIterado : polinomio.entrySet()) 
		{
			ultimaKeyMapa = mapaIterado.getKey();
		}
		
		Double terminoIndependiente = polinomioADividir.polinomio.get(0);  			// Obtener termino independiente
		Double terminoIndependienteOpuesto = terminoIndependiente * (-1);			// Obtener contrario
		
		// Primera operacion de ruffini 
		Double resultadoOperacion = terminoIndependienteOpuesto * polinomio.get(ultimaKeyMapa); 
		
		//System.out.println("Primera: " + polinomio.get(ultimaKeyMapa) + " Exponente: " + (ultimaKeyMapa-1) );

		resultado.setMonomio(ultimaKeyMapa-1, polinomio.get(ultimaKeyMapa)); 					// Monta Coeficiente
		
		// Bulce ruffini
		for(int x = ultimaKeyMapa-1; x >= 0; x--)
		{
			// Si es nulo, es decir que no hay clave, no se suma
			if (polinomio.get(x) != null)
				resultadoOperacion = resultadoOperacion + polinomio.get(x);
			
			//System.out.println("Resultado operacion: " + resultadoOperacion + " Exponente: " + (x-1));
			
			// Si se llega al final, no se multiplica mas
			if (x != 0)
			{
				resultado.setMonomio(x-1, resultadoOperacion); 									// Monta Coeficiente
				resultadoOperacion = resultadoOperacion * terminoIndependienteOpuesto;
				
			}
		}
		
		ArrayList resultadoFinal = new ArrayList();
		
		resultadoFinal.add(resultadoOperacion);
		resultadoFinal.add(resultado);
		
		
		return resultadoFinal;
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
			//if ((mapaIterado.getKey() == 1 && mapaIterado.getValue() == 1))
			if ((mapaIterado.getKey() >= 1 && mapaIterado.getValue() == 1))
				coeficienteLeido = "";

			//Elimina el 1 deL "-1X"
			//if ((mapaIterado.getKey() == 1 && mapaIterado.getValue() == -1))
			if ((mapaIterado.getKey() >= 1 && mapaIterado.getValue() == -1))
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
		
		return resultado;
	}

}
