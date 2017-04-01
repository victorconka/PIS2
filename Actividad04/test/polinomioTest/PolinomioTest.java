
package polinomioTest;

import polinomio.Polinomio;
import junit.framework.TestCase;

public class PolinomioTest extends TestCase {
/*
 
  public void testSuma() 
  {
	  Polinomio polinomioA = new Polinomio();
	  polinomioA.setMonomio(2,4);
	  polinomioA.setMonomio(3,-3);
	  polinomioA.setMonomio(0,2);
	  polinomioA.setMonomio(1,1);
	  polinomioA.setMonomio(2,-2);
	  polinomioA.setMonomio(1,-1);
	  System.out.println(polinomioA);
	  
	  Polinomio polinomioB = new Polinomio();
	  polinomioB.setMonomio(1,7);
	  polinomioB.setMonomio(2,-2);
	  polinomioB.setMonomio(1,-5);
	  polinomioB.setMonomio(1,-1);
	  polinomioB.setMonomio(0,1);
	  System.out.println(polinomioB);
	  
	  Polinomio polinomioSuma = polinomioA.sumaPolinomio(polinomioB);
	  System.out.println(polinomioSuma);
	  //assertEquals(resultadoEsperado, resultadoReal, 0.01);
  }*/
/*	
  public void testSimplifica() 
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
	*/
	
	 public void testMultiplica() 
	 { 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(2,-3);
		  polinomioA.setMonomio(4,2);
		  polinomioA.setMonomio(0,-8);
		  polinomioA.setMonomio(3,-1);
		  polinomioA.setMonomio(1,5);
		  System.out.println(polinomioA);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(4,-5);
		  System.out.println(polinomioB);
		  
		  Polinomio polinomioMultiplica = polinomioA.multiplicaPolinomio(polinomioB);
		  System.out.println(polinomioMultiplica);
		  //assertEquals(resultadoEsperado, resultadoReal, 0.01);
	  }
}
