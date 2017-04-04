package routhHurwitz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import junit.framework.TestCase;

public class RouthHurwitzTest extends TestCase {

	int grado = 4;
	double[] coef = { 1, 10, 35, 50, 124 };
	RouthHurwitz rh = new RouthHurwitz(grado, coef);

	public void testPolinomioClavesCorrectaLongitud() throws Exception {
		int a = rh.polinomio.getPolinomio().keySet().size();
		int b = grado + 1;
		assertEquals(a, b);
	}

	public void testPolinomioEntradasCorrectaLongitud() throws Exception {
		int a = rh.polinomio.getPolinomio().entrySet().size();
		int b = coef.length;
		assertEquals(a, b);
	}

	public void testPolinomioClavesCorrectas() throws Exception {
		boolean b = true;
		int i = grado;
		for (Integer c : rh.polinomio.getPolinomio().keySet()) {
			if (c != (grado - i)) {
				b = false;
				break;
			}
			i--;
		}
		assertTrue(b);
	}

	public void testCasoTrivialInestableCoeficiente0() throws Exception {
		rh.polinomio.getPolinomio().put(0, 0.0);
		assertTrue(rh.comprobarCasosTriviales());
	}

	public void testCasoTrivialInestableCoeficienteNegativoYPositivo()
			throws Exception {
		rh.polinomio.getPolinomio().put(0, 3.0);
		rh.polinomio.getPolinomio().put(0, -3.0);
		assertTrue(rh.comprobarCasosTriviales());
	}

	public void testNoCasoTrivial() throws Exception {
		assertFalse(rh.comprobarCasosTriviales());
	}

	public void testTodosCoeficientesNegativos() throws Exception {
		HashMap<Integer, Double> polinomio = rh.polinomio.getPolinomio();
		for (Entry<Integer, Double> var : polinomio.entrySet()) {
			var.setValue(var.getValue() * -1);
		}
		rh.comprobarCasosTriviales();
		assertEquals(rh.cambioSigno, rh.tamanioCoeficientes);
	}

	public void testMatrizPrimeraFilaCorrecta() throws Exception {
		rh.comprobarCasosTriviales();
		rh.crearMatriz();
		ArrayList<ArrayList<Double>> matriz = rh.matriz;
		for (int i = 0; i < matriz.get(0).size(); i++) {
			assertEquals(matriz.get(0).get(i), coef[2 * i]);
		}
	}

	public void testMatrizSegundaFilaCorrecta() throws Exception {
		rh.comprobarCasosTriviales();
		rh.crearMatriz();
		ArrayList<ArrayList<Double>> matriz = rh.matriz;
		for (int i = 0; i < matriz.get(1).size(); i++) {
			assertEquals(matriz.get(1).get(i), coef[2 * i + 1]);
		}
	}

	public void testMatrizDegeneracionDetectada() throws Exception {
		grado = 5;
		double[] nuevoCoef = { 1, 3, 2, 6, 3, 3 };
		rh = new RouthHurwitz(grado, nuevoCoef);

		rh.comprobarCasosTriviales();
		rh.crearMatriz();
		assertEquals(rh.degeneracion, true);
	}

	public void testMatrizFilaCompletaCero() throws Exception {
		grado = 4;
		double[] nuevoCoef = { 1, 2, 11, 18, 18 };
		rh = new RouthHurwitz(grado, nuevoCoef);

		rh.comprobarCasosTriviales();
		rh.crearMatriz();
		assertEquals(rh.criticamenteEstable, true);
	}

	public void testMatrizSistemaEstable() throws Exception {
		rh.comprobarCasosTriviales();
		rh.crearMatriz();
		rh.comprobarRaicesPositivas();

		assertEquals(rh.degeneracion, false);
		assertEquals(rh.criticamenteEstable, false);
		assertEquals(rh.inestable, false);
	}

	public void testMatrisSistemaInestable() throws Exception {
		grado = 5;
		double[] nuevoCoef = { 1, 4, 2, 5, 3, 6 };
		rh = new RouthHurwitz(grado, nuevoCoef);

		rh.comprobarCasosTriviales();
		rh.crearMatriz();
		rh.comprobarRaicesPositivas();

		assertEquals(rh.degeneracion, false);
		assertEquals(rh.criticamenteEstable, false);
		assertEquals(rh.inestable, true);
	}
	
	public void testComprobarSistemaDegeneracion() throws Exception {
		String resultado = "";
		grado = 5;
		double[] nuevoCoef = { 1, 3, 2, 6, 3, 3 };
		rh = new RouthHurwitz(grado, nuevoCoef);

		rh.comprobarCasosTriviales();
		resultado = rh.comprobarSistema();
		assertEquals(resultado, "El sistema ha sufrido una degeneración en el cálculo --> primer elemento de fila es cero");
	}
	
	public void testComprobarSistemaCriticamenteEstable() throws Exception {
		String resultado = "";
		grado = 4;
		double[] nuevoCoef = { 1, 2, 11, 18, 18 };
		rh = new RouthHurwitz(grado, nuevoCoef);

		rh.comprobarCasosTriviales();
		resultado = rh.comprobarSistema();
		assertEquals(resultado, "El sistema es críticamente estable --> fila cero y no hay raíces positivas");
	}
	
	public void testComprobarSistemaEstable() throws Exception {
		String resultado = "";

		rh.comprobarCasosTriviales();
		resultado = rh.comprobarSistema();
		assertEquals(resultado, "El sistema es estable --> no hay raíces en semiplano derecho");
	}

	public void testComprobarSistemaInestable() throws Exception {
		String resultado = "";
		grado = 5;
		double[] nuevoCoef = { 1, 4, 2, 5, 3, 6 };
		rh = new RouthHurwitz(grado, nuevoCoef);

		rh.comprobarCasosTriviales();
		resultado = rh.comprobarSistema();
		assertEquals(resultado, "El sistema es inestble --> " + rh.cambioSigno + " raíces positivas (hay " + rh.cambioSigno + " cambios de signo en la primera columna");
	}
}
