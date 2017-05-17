package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.logging.*;

import interpolacionlineal.InterpolacionLineal;
/**
 * interfaz textual de la clase interpolacion lineal
 * @author Usuario
 *
 */
public class MainInterpolacionLineal {
	
	private static Logger logger = Logger.getLogger("InfoLogging"); //logger para sustituir syso
	private InterpolacionLineal il;
	private Double a; //valores del intervalo a,b
	private Double b;
	private Double error = Double.MAX_VALUE;
	private Integer iteraciones = 0;
	private boolean salir = false;
	
	/**
	 * Inicializador por defecto que inicializa la ecuacion
	 */
	public MainInterpolacionLineal(){
		il = new InterpolacionLineal();
	}
	
	private Double leerDoble(){
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		boolean val = false;
		Double value = 0.0;

		do{
			try {
				value = Double.parseDouble(bf.readLine());
				val = true;
			}catch (IOException e) {
				logger.info("Debe introducir valor separado por punto" + e);
			}

		}while(!val);
		
		
		return value;
	}
	/**
	 * Pedir error por consola
	 */
	public void leerError(){
		logger.info("Introduzca el de error deseado en formato decimal, p.e. 0.0001 ");
		error = this.leerDoble();
		if(error.equals(Double.MAX_VALUE)){
			this.leerError();
		}
		il.setError(error);
	}
	/**
	 * Pedir numero de iteraciones
	 */
	public void leerIteraciones(){
		logger.info("Introduzca el numero de iteraciones. Si introduce 0 o un numero negativo, se usara el valor por defecto");
		iteraciones = this.leerDoble().intValue();
		if(iteraciones == 0){
			logger.info("Se usara el numero de iteraciónes por defecto :" + il.getNumeroIteraciones());
		}
		il.setError(error);
	}
	
	/**
	 * Lee el intervalo [a,b]
	 */
	public void leerIntervalo(){
		logger.info("Introduzca el valor a");
		a = this.leerDoble();
		logger.info("Introduzca el valor b");
		b = this.leerDoble();
		if(a.equals(b) || a > b){
			if(a.equals(b)){
				salir = true;
				logger.info("los limites izquierdo y derecho coinciden,\n el programa se detendrá");
			}else{
				logger.info("Introdujo el intervalo de forma incorrecta: a > b");
				logger.info("Se le solicitaran de nuevo los puntos del intervalo [a,b] donde quiere hallar la raíz");
				this.leerIntervalo();
			}
		}
		if(!salir){
			this.il.setIterval(a, b);
			if(!il.verificarBolzano()){
				logger.info("el intervalo NO cumple las condiciónes de Bolzano");
				logger.info("Debe introducir un intervalo nuevo");
				this.leerIntervalo();
			}
			String raiz = "1.29486";
			String sa = String.format(Locale.US, "%.5f", a);
			String sb = String.format(Locale.US, "%.5f", b);
			if(raiz.equals(sa) || raiz.equals(sb)){
				logger.info("uno de los valores introducidos es igual a la raíz");
				logger.info("Debe introducir un intervalo nuevo");
				this.leerIntervalo();
			}
		}
	}
	/**
	 * representa el menu para el uso de la aplicación
	 */
	public void iniciarPrograma(){
		while(!salir){
			logger.info("La ecuación cuya raíz se va a calcular es la siguiente: " + il.getPolinomio());
			logger.info("Se le solicitaran los puntos del intervalo [a,b] donde quiere hallar la raíz");
			leerIntervalo();
			if(!salir){
				logger.info("el intervalo cumple las condiciónes de Bolzano");
				logger.info("Introduzca la exactitud deseada");
				leerError();
				leerIteraciones();
				logger.info("Valores introducidos correctamente");
				logger.info("Resultado de la busqueda de raíz \n");
				il.calcular();
				logger.info(il.toString());
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainInterpolacionLineal mil = new MainInterpolacionLineal();
		mil.iniciarPrograma();
	}

}
