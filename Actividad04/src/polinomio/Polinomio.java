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
		if (polinomio.get(exponente) != null)
		{
			double coefGet = polinomio.get(exponente);
			coefGet += coeficiente;
			polinomio.put(exponente, coefGet);
		}
		else
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
		String resultado ="";
		for (Entry<Integer, Double> mapaIterado : polinomio.entrySet()) {
			
			DecimalFormat formatoDecimalCeros = new DecimalFormat();
			formatoDecimalCeros.setDecimalSeparatorAlwaysShown(false);
			
			String coeficienteLeido = "";
			if (mapaIterado.getValue() < 0)
				coeficienteLeido = "-" + formatoDecimalCeros.format(mapaIterado.getValue());
			else
				coeficienteLeido = "+" +  formatoDecimalCeros.format(mapaIterado.getValue());
			
			switch (mapaIterado.getKey()) {
				case 0:
					resultado = coeficienteLeido  + resultado;
					break;
				case 1:
					resultado = coeficienteLeido + "X" + resultado;
					break;
				default:
					resultado = coeficienteLeido + "X^" + mapaIterado.getKey()  + resultado;
					break;
			}
			
				
		    
		}
		
		System.out.println(polinomio.values());
		return resultado;
	}

}
