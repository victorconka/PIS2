
package polinomioTest;

import polinomio.Polinomio;
import junit.framework.TestCase;

public class PolinomioTest extends TestCase {

/* 
  public void testPrueba() 
  {
	  Polinomio polinomioA = new Polinomio();
	  polinomioA.setMonomio(0,3);
	  polinomioA.setMonomio(0,-1);
	  polinomioA.setMonomio(1,9);
	 // System.out.println(polinomioA);
	  
	  Polinomio polinomioB = new Polinomio();
	  polinomioB.setMonomio(0,3);
	  polinomioB.setMonomio(1,-1);
	  polinomioB.setMonomio(2,9);
	  //System.out.println(polinomioB);
	  
	  Polinomio polinomioSuma = polinomioA.sumaPolinomio(polinomioB);
	  System.out.println(polinomioSuma);
	  //assertEquals(resultadoEsperado, resultadoReal, 0.01);
  }*/
	
  public void testPrueba2() 
  {
	  Polinomio polinomioA = new Polinomio();
	  polinomioA.setMonomio(2,4);
	  polinomioA.setMonomio(1,1);
	  polinomioA.setMonomio(2,-2);
	  polinomioA.setMonomio(1,-1);
	  polinomioA.setMonomio(0,1);
	  System.out.println(polinomioA);
	  
	  Polinomio polinomioB = new Polinomio();
	  polinomioB.setMonomio(1,7);
	  polinomioB.setMonomio(2,-2);
	  polinomioB.setMonomio(1,-5);
	  polinomioB.setMonomio(1,-1);
	  polinomioB.setMonomio(0,1);
	  
	  System.out.println(polinomioB);
	  
	  Polinomio polinomioC = new Polinomio();
	  polinomioC.setMonomio(1,7);
	  System.out.println(polinomioC); 


	  //assertEquals(resultadoEsperado, resultadoReal, 0.01);
  }
	
}
