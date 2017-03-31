package routhHurwitz;

import junit.framework.TestCase;

public class RouthHurwitzTest extends TestCase {

	int grado = 4;
	double[] coef = { 1, 1, 3, 5, 4 };
	RouthHurwitz rh = new RouthHurwitz(grado, coef);

	public void testPolinomioClavesCorrectaLongitud() throws Exception {
		assertTrue(rh.getPolinomio().keySet().size() == grado + 1);
	}

	public void testPolinomioEntradasCorrectaLongitud() throws Exception {
		assertTrue(rh.getPolinomio().entrySet().size() == coef.length);
	}

	public void testPolinomioClavesCorrectas() throws Exception {
		boolean b = true;
		int i = grado;
		for (Integer c : rh.getPolinomio().keySet()) {
			if (c != (grado - i)) {
				b = false;
				break;
			}
			i--;
		}
		assertTrue(b);
	}

	public void testCasoTrivialInestableCoeficiente0() throws Exception {
		rh.getPolinomio().put(0, 0.0);
		assertTrue(rh.comprobarCasosTriviales());
	}

	public void testCasoTrivialInestableCoeficienteNegativoYPositivo()
			throws Exception {
		rh.getPolinomio().put(0, 3.0);
		rh.getPolinomio().put(0, -3.0);
		assertTrue(rh.comprobarCasosTriviales());
	}

	public void testMatrizFilasCorrectas() throws Exception {

	}

	public void testMatrizColumnasCorrectas() throws Exception {

	}
}
