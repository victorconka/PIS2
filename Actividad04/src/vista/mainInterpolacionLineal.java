package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import interpolacionLineal.InterpolacionLineal;

public class mainInterpolacionLineal {
	
	public InterpolacionLineal il;
	private Double a,b;
	private Double E = Double.MAX_VALUE;
	private Integer iteraciones = 0;
	public boolean salir = false;
	public mainInterpolacionLineal(){
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
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir valor separado por punto");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		while(!val);
		
		
		return value;
	}
	
	public void leerError(){
		System.out.println("Introduzca el de error deseado en formato decimal, p.e. 0.0001 ");
		E = this.leerDoble();
		if(E.equals(Double.MAX_VALUE)){
			this.leerError();
		}
		il.setError(E);
	}
	
	public void leerIteraciones(){
		System.out.println("Introduzca el numero de iteraciones. Si introduce 0 o un numero negativo, se usara el valor por defecto");
		iteraciones = this.leerDoble().intValue();
		if(iteraciones == 0){
			System.out.println("Se usara el numero de iteraciónes por defecto :" + il.getNumeroIteraciones());
		}
		il.setError(E);
	}
	
	/**
	 * Lee el intervalo [a,b]
	 */
	public void leerIntervalo(){
		System.out.println("Introduzca el valor a");
		a = this.leerDoble();
		System.out.println("Introduzca el valor b");
		b = this.leerDoble();
		if(a.equals(b) || a > b){
			if(a.equals(b)){
				salir = true;
				System.out.println("los limites izquierdo y derecho coinciden,\n el programa se detendrá");
			}else{
				System.out.println("Introdujo el intervalo de forma incorrecta: a > b");
				System.out.println("Se le solicitaran de nuevo los puntos del intervalo [a,b] donde quiere hallar la raíz");
				this.leerIntervalo();
			}
		}
		if(!salir){
			this.il.setIterval(a, b);
			if(!il.verificarBolzano()){
				System.out.println("el intervalo NO cumple las condiciónes de Bolzano");
				System.out.println("Debe introducir un intervalo nuevo");
				this.leerIntervalo();
			}
			String raiz = "1.29486";
			String sa = String.format(Locale.US, "%.5f", a);
			String sb = String.format(Locale.US, "%.5f", b);
			if(raiz.equals(sa) || raiz.equals(sb)){
				System.out.println("uno de los valores introducidos es igual a la raíz");
				System.out.println("Debe introducir un intervalo nuevo");
				this.leerIntervalo();
			}
		}
	}
	
	public void iniciarPrograma(){
		while(!salir){
			System.out.println("La ecuación cuya raíz se va a calcular es la siguiente: " + il.getPolinomio());
			System.out.println("Se le solicitaran los puntos del intervalo [a,b] donde quiere hallar la raíz");
			leerIntervalo();
			if(!salir){
				System.out.println("el intervalo cumple las condiciónes de Bolzano");
				System.out.println("Introduzca la exactitud deseada");
				leerError();
				leerIteraciones();
				System.out.println("Valores introducidos correctamente");
				System.out.println("Resultado de la busqueda de raíz \n");
				il.calcular();
				System.out.println(il.toString());
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		mainInterpolacionLineal mil = new mainInterpolacionLineal();
		mil.iniciarPrograma();
	}

}
