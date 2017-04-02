package interpolacionLineal;

import java.util.Locale;

import polinomio.Polinomio;

public class InterpolacionLineal {
	private Polinomio func;
	private int precision = 5; //numero de digitos de precisión.
	private Double x1, x2;//valores del intervalo [x1,x2]
	private Double xR;//Variable que almacenara la evaluacion de la ecuacion.
	private Double fXr; //evaluación fxR = f(xR)
	private Double E; //Valor de precision
	private Integer it; //numero de iteraciónes
	private Double error; //precision obtenida
	private int iMax = 1000; //numero maximo de iteraciónes para el caso de divergencia
	
	public InterpolacionLineal(){
		initDefaultFunc();
	}
	public InterpolacionLineal(Double x1, Double x2, Double E){
		initDefaultFunc();
		this.setParameters(x1, x2, E);
	}
	/**
	 * Establece el error admisible
	 * @param E - valor del error admisible
	 */
	public void setError(Double E){
		this.E = E;
	}
	/**
	 * Establece el intervalo del calculo [x1,x2]
	 * @param x1 - valor a la izquierda
	 * @param x2 - valor a la derecha
	 */
	public void setIterval(Double x1, Double x2){
		initDefaultFunc();
		this.x1 = x1;
		this.x2 = x2;
	}
	/**
	 * Establece el intervalo [x1,x2] y el error admisible
	 * @param x1 - valor a la izquierda
	 * @param x2 - valor a la derecha
	 * @param E - valor del error admisible
	 */
	public void setParameters(Double x1, Double x2, Double E){
		this.x1 = x1;
		this.x2 = x2;
		this.E = E;
	}
	/**
	 * Realiza la busqueda de la raíz
	 */
	public void calcular(){
		it = 0;
		Double fX1 = 0.0;
		Double fX2 = 0.0;
		error = 0.0;
		
		while (it < iMax){
			fX1 = this.evaluatePolinoimo(this.x1);
			fX2 = this.evaluatePolinoimo(this.x2);
			
			xR = this.adaptarPrecision(x2 - ( ((x2-x1)*fX2) / (fX2-fX1) ));
			fXr = this.evaluatePolinoimo(xR);
			error = Math.abs(fXr);
			
			System.out.println(this.toString());
			
			if(error <= E){
				System.out.println("Error admisible");
				break;
			}
			
			if((fX1*fXr) <0 ){
				x2 = xR;
			}
			if((fX1*fXr) >0 ){
				x1 = xR;
			}
			if((fX1*fXr) ==0 ){
				System.out.println("Raiz en xR");
				break;
			}
			
			it++;
		}
	}
	
	public String toString(){
		String ret = "";
		ret = "Nº iteraciones = " + it;
		ret += "\n precision = " + error;
		ret += "\n Xr = " + xR;
		return ret;
	}
	/**
	 * Adapta la precision deseada a los calculos
	 * @param val - valor cuya precision queremos adaptar
	 * @return devuelve valor con precision "precision"
	 */
	private Double adaptarPrecision(Double val){
		val = Double.valueOf(String.format(Locale.US, "%." + this.precision + "f", val));
		return val;
	}
	/**
	 * Llama el metodo del polinomio aplicando la precision que deseamos
	 * @param val - punto a evaluar
	 * @return - resultado para dicho punto
	 */
	private Double evaluatePolinoimo(Double val){
		Double ret = 0.0;
		val = this.adaptarPrecision(val);
		ret = this.func.evaluatePolinoimo(val);
		ret = this.adaptarPrecision(ret);
		return ret;
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
