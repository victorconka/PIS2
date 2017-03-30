package routhHurwitz;

import java.util.Collection;

import polinomio.*;

public class RouthHurwitz {
	
	Polinomio polinomio;
	Collection<Double> coeficientes;
	double[][] matriz;
	
	public RouthHurwitz(int gradoMaximo, double[] coeficientes) {
		polinomio = new Polinomio();
		//polinomio.CrearHashMapRouthHurwitz(this.gradoMaximo, this.coeficientes);
	}
	
	public void ComprobarSistema() {
		if (comprobarCasosTriviales()) mostrarResultado("inestable");
		else crearMatriz();
	}
	
	private void crearMatriz() {
		Object[] aux = coeficientes.toArray();
		matriz = new double [polinomio.getPolinomio().keySet().size()][coeficientes.size()];
		int n = matriz.length;
		
		// Creacion de la primera fila
		for (int i = 0; i < matriz[0].length; i++) {
			matriz[0][i] = (Double) aux[n];
			n -= 2;
		}
		
		//Creacion de la segunda fila
		n = matriz.length - 1;
		for (int i = 0; i < matriz[1].length; i++) {
			matriz[1][i] = (Double) aux[n];
			n -= 2;
		}
		
		// Creacion del resto de filas
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = (Double) aux[n];
			}
		}
	}
	
	// Devuelve true si el sistema es inestable
	public boolean comprobarCasosTriviales() {
		boolean coefNegativo = false;
		boolean coefPositivo = false;
		int coefNegativos = 0;
		coeficientes = polinomio.getPolinomio().values();
		
		for (Double coef : coeficientes) {
			if (coef == 0) {
				return true;
			} else if (coef < 0) {
				coefNegativo = true;
				coefNegativos++;
			} else if (coef > 0) {
				coefPositivo = true;
			}
			
			if (coefNegativo && coefPositivo) return true;
		}
		
		if (coefNegativos == coeficientes.size()) {
			for (Double c : coeficientes) {
				c *= -1;
			}
		}
			
		return false;
	}
	
	private void mostrarResultado(String estado) {
		System.out.println("El sistema es " + estado);
	}
	
	public Polinomio getPolinomio() {
		return polinomio;
	}

	public void setPolinomio(Polinomio polinomio) {
		this.polinomio = polinomio;
	}

}
