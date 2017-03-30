package routhHurwitz;

import junit.framework.TestCase;

public class RouthHurwitzTest extends TestCase {

	int grado = 4;
	double[] coef = new double[5];
	RouthHurwitz rh = new RouthHurwitz(grado, coef);
	
	public void testGradoMenor100() throws Exception {
		//assertTrue(rh.getGradoMaximo() < 100);
	}
	
	public void testCasoTrivialInestableCoeficiente0() throws Exception {
		assertTrue(rh.comprobarCasosTriviales());
	}
	
	public void testCasoTrivialInestableCoeficienteNegativoYPositivo() throws Exception {
		coef[0] = 3;
		coef[1] = -3;
		assertTrue(rh.comprobarCasosTriviales());
	}
}
