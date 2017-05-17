package interpolacionlineal;

import java.util.Locale;
import java.util.logging.Logger;

import polinomio.Polinomio;
/**
 * El numero de decimales que se usan durante las operaciones es 15 (16 se usa para redondeo)
 * @author Usuario
 *
 */
public class InterpolacionLineal {
	
	private static Logger logger = Logger.getLogger("InfoLogging"); //logger para sustituir syso
	private Polinomio polinomio;			//polinomio que alberga la funcion
	private int precision = 5; 				//numero de digitos de precisión a mostrar.
	private Double x1;						//valores del intervalo [x1,x2]
	private Double x2;	
	private Double xR = Double.NaN;			//Variable que almacenara la evaluacion de la ecuacion.
	private Double fXr = Double.NaN;		//evaluación fxR = f(xR)
	private Double ePrecision = Double.NaN;			//Valor de precision
	private Integer it = 0; 				//numero de iteraciónes
	private Double error = Double.NaN; 		//precision obtenida
	private int iMax = 40; 					//numero maximo de iteraciónes para el caso de divergencia

	/**
	 * Inicializacion por defecto de la clase
	 */
	public InterpolacionLineal(){
		initDefaultFunc();
	}
	/**
	 * inicializa la clase estableciendo valores:
	 * @param x1 - valor a la izquierda
	 * @param x2 - valor a la derecha
	 * @param errorAdmisible - error admisible
	 */
	public InterpolacionLineal(Double x1, Double x2, Double errorAdmisible){
		initDefaultFunc();
		this.setParameters(x1, x2, errorAdmisible);
	}
	
	/**
	 * Establece el error admisible
	 * @param errorAdmisible - valor del error admisible
	 */
	public void setError(Double errorAdmisible){
		this.ePrecision = Math.abs(errorAdmisible);
		this.verifyPrecision();
	}
	public Double getError(){
		return this.ePrecision;
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
	
	public String getPolinomio(){
		return this.polinomio.toString();
	}
	
	/**
	 * Returns an array of both positions [x1,x2]
	 * @return Double[]{x1,x2}
	 */
	public Double[] getInterval(){
		return new Double[]{x1,x2};
	}
	
	private void verifyPrecision(){
		//para lograr representacion no cientifica, mostramos numero con ceros a la derecha y los borramos
		String[] p = ((String.format(Locale.US, "%.32f", this.ePrecision)).replaceAll("0+$", "")).split("\\.");
		if(p.length>1 && p[1].length() > this.precision){
				this.precision = p[1].length();
		}
	}
	/**
	 * set precision para la representación de los datos
	 * @param precision - entero que indica el numero de digitos de precision
	 */
	public void setPrecision(int precision){
		this.precision = precision;
	}
	public int getPrecision(){
		return this.precision;
	}
	
	/**
	 * Establece el intervalo [x1,x2] y el error admisible
	 * @param x1 - valor a la izquierda
	 * @param x2 - valor a la derecha
	 * @param errAdmisible - valor del error admisible
	 */
	public void setParameters(Double x1, Double x2, Double errAdmisible){
		this.setIterval(x1, x2);
		this.setError(errAdmisible);
	}
	
	/**
	 * Realiza la busqueda de la raíz
	 */
	public void calcular(){
		it = 0;
		Double fX1;
		Double fX2;
		error = 0.0;
		
		while (it < iMax){
			fX1 = this.evaluatePolinoimo(this.x1);
			fX2 = this.evaluatePolinoimo(this.x2);
			
			xR = x2 - ((x2-x1)*fX2) / (fX2-fX1) ;

			fXr = this.evaluatePolinoimo(xR);
			
			error = Math.abs(fXr);
			
			if(error >= ePrecision){
				if((fX1*fXr) < 0 ){
					x2 = xR;
				}
				if((fX1*fXr) > 0 ){
					x1 = xR;
				}
				
				if( Double.compare(fX1*fXr, 0.0) == 0 ){
					break;
				}
			}
					
			it++;
		}
	}
	/**
	 * Metodo que verifica si se cumple el teorema del bolzano para los valores x1 y x2
	 * @return true - se cumple bolzano/false - no se cumple bolzano
	 */
	public boolean verificarBolzano(){
		boolean bolzano = false;
		
		if(x1 != null && x2 != null){
			
			Double fX1 = this.evaluatePolinoimo(x1);
			Double fX2 = this.evaluatePolinoimo(x2);
			
			if(fX1 < 0 && fX2 > 0){
				bolzano = true;
			}
			if(fX1 > 0 && fX2 < 0){
				bolzano = true;
			}
			
		}
		
		return bolzano;
	}
	
	@Override
	public String toString(){
		String ret = "Iteraciónes: " + it;
		ret += "\n Intervalo [" + this.adaptarPrecision(this.x1) + "," + this.adaptarPrecision(this.x2)+  "]";
		ret += "\n error = " + this.adaptarPrecision(error);
		ret += "\n Xr = " + this.adaptarPrecision(xR);
		ret += "\n f(Xr) = " + this.adaptarPrecision(fXr);
		return ret;
	}
	
	/**
	 * Adapta la precision deseada a los calculos
	 * @param val - valor cuya precision queremos adaptar
	 * @return devuelve valor con precision "precision"
	 */
	private Double adaptarPrecision(Double val){
		String format = "%." + this.precision + "f";
		String str = String.format(Locale.US, format, val);
		return Double.valueOf(str);
	}
	
	/**
	 * Llama el metodo del polinomio para la evaluación
	 * @param val - punto a evaluar
	 * @return - resultado para dicho punto
	 */
	private Double evaluatePolinoimo(Double val){
		return this.polinomio.evaluatePolinoimo(val);
	}
	
	/**
	 * establece el numero de iteraciónes que ha de ejecutar el programa
	 * @param n
	 */
	public void setNumeroIteraciones(int n){
		
		if(n < 1){
			logger.info("Numero no puede ser negativo");
		}else{
			this.iMax = n;
		}
	}
	public Integer getNumeroIteraciones(){
		return this.iMax;
	}
	/**
	 * Funcion que inicializa la función que se analiza
	 * f(x) = x^5 - x^4 + x^3 - 3
	 */
	private void initDefaultFunc(){
		polinomio = new Polinomio();
		polinomio.setMonomio(5, 1);
		polinomio.setMonomio(4, -1);
		polinomio.setMonomio(3, 1);
		polinomio.setMonomio(0, -3);
	}
}
