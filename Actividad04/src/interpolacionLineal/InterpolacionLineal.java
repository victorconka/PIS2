package interpolacionLineal;

import polinomio.Polinomio;

public class InterpolacionLineal {
	Polinomio func;
	Double x1, x2;//valores del intervalo [x1,x2]
	Double xR;//Variable que almacenara la evaluacion de la ecuacion.
	
	public InterpolacionLineal(){
		initDefaultFunc();
	}
	
	
	/**
	 * Funcion que inicializa la función que se analiza
	 * f(x) = x^5 - x^4 + x^3 - 3
	 */
	private void initDefaultFunc(){
		func = new Polinomio();
		func.setMonomio(5, 1);
		func.setMonomio(4, -1);
		func.setMonomio(3, 1);
		func.setMonomio(0, -3);
	}
}
