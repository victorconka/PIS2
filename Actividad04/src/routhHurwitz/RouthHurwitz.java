package routhHurwitz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import polinomio.*;

public class RouthHurwitz {
	
	Polinomio polinomio;
	Collection<Double> coeficientes;
	int tamanioCoeficientes;
	ArrayList<ArrayList<Double>> matriz = new ArrayList<ArrayList<Double>>();
	ArrayList<Boolean> filasCero = new ArrayList<Boolean>();
	boolean inestable = false;
	boolean degeneracion = false;
	boolean criticamenteEstable = false;
	int  cambioSigno;
	
	public RouthHurwitz(int gradoMaximo, double[] coeficientes) {
		polinomio = new Polinomio();
		crearHashMapRouthHurwitz(gradoMaximo, coeficientes);
	}

	private void crearHashMapRouthHurwitz(int gradoMaximo, double[] coeficientes) {
		for (int i = 0; i < (gradoMaximo + 1); i++) {
			polinomio.setMonomio(i, coeficientes[i]);
		}
	}
	
	public void comprobarSistema() {
		inestable = comprobarCasosTriviales();
		if (!inestable){
			crearMatriz();
			mostrarMatriz();
			if (!degeneracion)
				comprobarRaicesPositivas();
		}
		mostrarResultado();
	}
	
	private void crearMatriz() {
		ArrayList<Double> fila = new ArrayList<Double>();
		Object[] aux = coeficientes.toArray();		
		int j = 0;
		int i = 0;
		
		// Creacion de la primera fila
		while (j < tamanioCoeficientes) {
			fila.add(i, (Double) aux[j]);
			i++;
			j += 2;
		}
		matriz.add(0, fila);
		filasCero.add(false);
		
		//Creacion de la segunda fila
		j = 1;
		i = 0;
		fila  = new ArrayList<Double>();
		while (j < tamanioCoeficientes) {
			fila.add(i, (Double) aux[j]);
			i++;
			j += 2;
		}
		matriz.add(1, fila);
		filasCero.add(false);
		
		// Creacion del resto de filas
		i = 0; //indica la fila superior
		j = 0; // indica la columna		
		/*
		 * 
		 *						(matriz[i+1][0] * matriz[i][j+1]) - (matriz[i][0] * matriz[i+1][j+1])
		 * matriz[i+2][j] = --------------------------------------------------------------------------
		 * 													matriz[i+1][0]
		 * 
		 */		
		
		double resultado, v1, v2, v3, v4, v5;
		int tamanioFilaAnterior, tamanioFilaAnteriorAnterior;
		boolean todosElementosCero = true;
		
		for (int k = 2; k < tamanioCoeficientes; k++) {
			tamanioFilaAnteriorAnterior = matriz.get(i).size();
			tamanioFilaAnterior = matriz.get(i+1).size();
			fila = new ArrayList<Double>();
			j = 0;
			while (j < tamanioFilaAnterior) {
				v1 = matriz.get(i+1).get(0);
				v2 = ((j+1) >= tamanioFilaAnteriorAnterior)? 0.0 : matriz.get(i).get(j+1);
				v3 = matriz.get(i).get(0);
				v4 = ((j+1) >= tamanioFilaAnterior)? 0.0 : matriz.get(i+1).get(j+1);
				v5 = matriz.get(i+1).get(0);
				resultado = ((v1 * v2) - (v3 * v4)) / v5;
				fila.add(j, resultado);				
				
				if (resultado != 0) {
					todosElementosCero = false;
					if (fila.get(0) == 0) {
						filasCero.add(k, false);
						degeneracion = true;
						matriz.add(fila);
						return;
					}
				} else if (j > 0){
					fila.remove(j);
				}
				j++;				
			}					
			
			filasCero.add(k, false);
			if (todosElementosCero) {
				fila.clear();
				v5 = tamanioCoeficientes - k; // en v5 almacenamos el grado de la fila anterior (s^v5)
				for (j = 0; j < tamanioFilaAnterior; j++) {
					resultado = matriz.get(i+1).get(j) * ((v5) - (j * 2));
					fila.add(j, resultado);
					if (resultado != 0) {
						todosElementosCero = false;
						if (fila.get(0) == 0) {
							degeneracion = true;
							matriz.add(fila);
							return;
						}
					} else if (j > 0){
						fila.remove(j);
					}
					j++;	
				}
				filasCero.set(k, true);
				criticamenteEstable = true;
			}
			
			matriz.add(fila);
			i++;
			todosElementosCero = true;
		}
	}
		
	private void mostrarMatriz() {
		int grado = tamanioCoeficientes - 1;
		boolean cambiarGrado = true;
		
		for (int i = 0; i < matriz.size(); i++) {
			System.out.print("S^" + grado + " -->");
			for (int j = 0; j < matriz.get(i).size(); j++) {
				if (filasCero.get(i)) {
					cambiarGrado = false;
					System.out.print("\t0.0");
				} else {
					System.out.print("\t" + String.format("%.2f", matriz.get(i).get(j)));
				}
			}
			
			if (!cambiarGrado) {
				System.out.print("\nS^" + grado + " -->");
				for (int j = 0; j < matriz.get(i).size(); j++)
					System.out.print("\t" + String.format("%.2f", matriz.get(i).get(j)));
			}
			
			System.out.println();
			grado--;
			cambiarGrado = true;
		}
	}
	
	private void comprobarRaicesPositivas() {
		cambioSigno = 0;
		for (int i = 1; i < tamanioCoeficientes; i++) {
			if (matriz.get(i).get(0) > 0 && matriz.get(i-1).get(0) < 0) {
				cambioSigno++;
				continue;
			}
			if (matriz.get(i).get(0) < 0 && matriz.get(i-1).get(0) > 0) {
				cambioSigno++;
			}
		}
		if (cambioSigno > 0) {
			inestable = true;
		}
	}
	
	// Devuelve true si el sistema es inestable
	public boolean comprobarCasosTriviales() {
		boolean coefNegativo = false;
		boolean coefPositivo = false;
		int coefNegativos = 0;
		coeficientes = polinomio.getPolinomio().values();
		tamanioCoeficientes = coeficientes.size();
		
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
		
		if (coefNegativos == tamanioCoeficientes) {
			for (Entry<Integer, Double> var : polinomio.getPolinomio().entrySet()) {
				polinomio.setMonomio(var.getKey(), var.getValue() * -2);
			}
		}
			
		return false;
	}
	
	private void mostrarResultado() {
		String estado = "";
		
		if (inestable) estado = "es inestble --> " + cambioSigno + " raíces positivas (hay " + cambioSigno + " cambios de signo en la primera columna";
		else if (degeneracion) estado = "ha sufrido una degeneración en el cálculo --> primer elemento de fila es cero";
		else if (criticamenteEstable) estado = "es críticamente estable --> fila cero y no hay raíces positivas";
		else estado = "es estable --> no hay raíces en semiplano derecho";
		
		System.out.println("El sistema " + estado);
	}
	
	public Polinomio getObjetoPolinomio() {
		return polinomio;
	}
	
	public HashMap<Integer, Double> getPolinomio() {
		return polinomio.getPolinomio();
	}

	public void setObjetoPolinomio(Polinomio polinomio) {
		this.polinomio = polinomio;
	}

}
