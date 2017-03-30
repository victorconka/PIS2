
package polinomioTest;

import polinomio.Polinomio;
import junit.framework.TestCase;

public class PolinomioTest extends TestCase {

 
  public void testPrueba() 
  {
	  Polinomio polinomioA = new Polinomio();
	  polinomioA.setMonomio(0,3);
	  polinomioA.setMonomio(0,-1);
	  polinomioA.setMonomio(1,9);
	  System.out.println(polinomioA);
	  
	  Polinomio polinomioB = new Polinomio();
	  polinomioB.setMonomio(0,3);
	  polinomioB.setMonomio(1,-1);
	  polinomioB.setMonomio(2,9);
	  System.out.println(polinomioB);
	  
	  //assertEquals(resultadoEsperado, resultadoReal, 0.01);
  }
	
	
}
