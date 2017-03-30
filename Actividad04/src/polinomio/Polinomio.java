package polinomio;

import java.util.ArrayList;
import java.util.HashMap;

public class Polinomio 
{
	// HashMap<Exponente , Coeficiente>
	HashMap<Integer, Double> polinomios = new HashMap<Integer, Double>();


	public Polinomio()
	{
		
	}
	
	public HashMap<Integer, Double> getPolinomio() {
		return polinomios;
	}


	public void setMonomio(int exponente, double coeficiente) 
	{
		if (polinomios.get(exponente) != null)
		{
			double coefGet = polinomios.get(exponente);
			coefGet += coeficiente;
			polinomios.put(exponente, coefGet);
		}
		else
		{
			polinomios.put(exponente, coeficiente);
		}
		
	}
	
	public Polinomio sumaPolinomio(Polinomio PolinomioASumar)
	{
		ArrayList<Integer> exponentesVisitados = new ArrayList<Integer>();
		
		Polinomio resultado = new Polinomio();
		
		HashMap<Integer, Double> hasPolinomioASumar = PolinomioASumar.getPolinomio();
		
		
		
		
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
		System.out.println(polinomios.values());
		return "a";
	}

}
