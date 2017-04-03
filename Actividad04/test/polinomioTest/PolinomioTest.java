
package polinomioTest;

import polinomio.Polinomio;

import java.util.ArrayList;

import junit.framework.TestCase;

public class PolinomioTest extends TestCase {

 
  public void testSuma() 
  {
	  Polinomio polinomioA = new Polinomio();
	  polinomioA.setMonomio(2,4);
	  polinomioA.setMonomio(3,-3);
	  polinomioA.setMonomio(0,2);
	  polinomioA.setMonomio(1,1);
	  polinomioA.setMonomio(2,-2);
	  polinomioA.setMonomio(1,-1);
	  //System.out.println(polinomioA);
	  
	  Polinomio polinomioB = new Polinomio();
	  polinomioB.setMonomio(1,7);
	  polinomioB.setMonomio(2,-2);
	  polinomioB.setMonomio(1,-5);
	  polinomioB.setMonomio(1,-1);
	  polinomioB.setMonomio(0,1);
	  //System.out.println(polinomioB);
	  
	  Polinomio polinomioSuma = polinomioA.sumaPolinomio(polinomioB);
	  //System.out.println(polinomioSuma);
	  assertEquals(polinomioSuma.toString(), "-3X^3+X+3");
  }

  public void testSimplifica() 
  {
	  Polinomio polinomioA = new Polinomio();
	  polinomioA.setMonomio(2,4);
	  polinomioA.setMonomio(1,1);
	  polinomioA.setMonomio(2,-2);
	  polinomioA.setMonomio(1,-1);
	  polinomioA.setMonomio(0,1);
	  //System.out.println(polinomioA);
	  assertEquals(polinomioA.toString(), "2X^2+1");
	 
	  
	  Polinomio polinomioB = new Polinomio();
	  polinomioB.setMonomio(1,7);
	  polinomioB.setMonomio(2,-2);
	  polinomioB.setMonomio(1,-5);
	  polinomioB.setMonomio(1,-1);
	  polinomioB.setMonomio(0,1);
	  assertEquals(polinomioB.toString(), "-2X^2+X+1");
	  //System.out.println(polinomioB);
	  
	  Polinomio polinomioC = new Polinomio();
	  polinomioC.setMonomio(1,7);
	  //System.out.println(polinomioC); 
	  assertEquals(polinomioC.toString(), "7X");
  }
	
	public void testEvaluarPolinomio(){
		Polinomio polinomioA = new Polinomio();
		//Asignamos una ecuacion x^2
		polinomioA.setMonomio(2, 1);
		
		//evaluar la ecuacion
		assertEquals(polinomioA.evaluatePolinoimo(2.0).equals(4.0),true);
	}
  
	 public void testMultiplicaPorMonomio() 
	 { 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(2,-3);
		  polinomioA.setMonomio(4,2);
		  polinomioA.setMonomio(0,-8);
		  polinomioA.setMonomio(3,-1);
		  polinomioA.setMonomio(1,5);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(4,-5);
		  
		  Polinomio polinomioMultiplica = polinomioA.multiplicaPolinomio(polinomioB);
		  //System.out.println(polinomioMultiplica);
		  assertEquals(polinomioMultiplica.toString(),"-10X^8+5X^7+15X^6-25X^5+40X^4");
	  }
	 
	 public void testMultiplicaPolinomiosCompletos() 
	 { 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(3,4);
		  polinomioA.setMonomio(2,-5);
		  polinomioA.setMonomio(1,2);
		  polinomioA.setMonomio(0,1);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(1,3);
		  polinomioB.setMonomio(0,-6);
		  
		  Polinomio polinomioMultiplica = polinomioA.multiplicaPolinomio(polinomioB);
		 // System.out.println(polinomioMultiplica);
		  assertEquals(polinomioMultiplica.toString(),"12X^4-39X^3+36X^2-9X-6"); 
	 }
	
	
	 public void testMultiplicaPolinomiosIncompletosYDesordenados() 
	 { 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(2,-9);
		  polinomioA.setMonomio(1,1);
		  polinomioA.setMonomio(4,5);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(0,3);
		  polinomioB.setMonomio(2,-2);
		 
		  Polinomio polinomioMultiplica = polinomioA.multiplicaPolinomio(polinomioB);
		 // System.out.println(polinomioMultiplica);
		  assertEquals(polinomioMultiplica.toString(),"-10X^6+33X^4-2X^3-27X^2+3X"); 
	  }
	 

	 public void testDividePolinomio() 
	 { 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(4,1);
		  polinomioA.setMonomio(2,-3);
		  polinomioA.setMonomio(0,2);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(1,1);
		  polinomioB.setMonomio(0,-3);
		 
		  ArrayList polinomioDivide = polinomioA.dividePolinomio(polinomioB);
		 //System.out.println("Cociente: " + polinomioDivide.get(1));
		 // System.out.println("Resto: " + polinomioDivide.get(0));
		  
		  assertEquals(polinomioDivide.get(1).toString(), "X^3+3X^2+6X+18");
		  assertEquals(polinomioDivide.get(0), 56.0);
	  }
	
	
	 public void testDividePolinomio2() 
	 {
		 //( x5 + x4 - x3 + x2 - 3x + 5) : (x - 1) = 4
		 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(5,1);
		  polinomioA.setMonomio(4,1);
		  polinomioA.setMonomio(3,-1);
		  polinomioA.setMonomio(2,1);
		  polinomioA.setMonomio(1,-3);
		  polinomioA.setMonomio(0,5);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(1,1);
		  polinomioB.setMonomio(0,-1);

		  ArrayList polinomioDivide = polinomioA.dividePolinomio(polinomioB);
		 // System.out.println("Cociente: " + polinomioDivide.get(1));
		 // System.out.println("Resto: " + polinomioDivide.get(0));

		  assertEquals(polinomioDivide.get(1).toString(), "X^4+2X^3+X^2+2X-1");
		  assertEquals(polinomioDivide.get(0), 4.0);
	 }
	
	 public void testDividePolinomio3() 
	 {
		 //(3x5 + 2x + 4) : (x + 2) = -96
		 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(5,3);
		  polinomioA.setMonomio(1,2);
		  polinomioA.setMonomio(0,4);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(1,1);
		  polinomioB.setMonomio(0,2);
		 
		  ArrayList polinomioDivide = polinomioA.dividePolinomio(polinomioB);
		//  System.out.println("Cociente: " + polinomioDivide.get(1));
		//  System.out.println("Resto: " + polinomioDivide.get(0));
		  
		  assertEquals(polinomioDivide.get(1).toString(), "3X^4-6X^3+12X^2-24X+50");
		  assertEquals(polinomioDivide.get(0), -96.0);

		  
	  }
	 
	 public void testDividePolinomio4() 
	 {
		 //(x4 - 5x2 + 2) : (5x - 10) = -2
		 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(4,1);
		  polinomioA.setMonomio(2,-5);
		  polinomioA.setMonomio(0,2);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(1,5);
		  polinomioB.setMonomio(0,-10);
		 
		  ArrayList polinomioDivide = polinomioA.dividePolinomio(polinomioB);
		//  System.out.println("Cociente: " + polinomioDivide.get(1));
		//  System.out.println("Resto: " + polinomioDivide.get(0));
		  
		  assertEquals(polinomioDivide.get(1).toString(), "X^3+2X^2-X-2");
		  assertEquals(polinomioDivide.get(0), -2.0);
		  
	  }

	 public void testDividePolinomio5() 
	 {
		 //(x3 + 2x2 - 5x + 2) : (2x + 3) == 10.625
		 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(3,1);
		  polinomioA.setMonomio(2,2);
		  polinomioA.setMonomio(1,-5);
		  polinomioA.setMonomio(0,2);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(1,2);
		  polinomioB.setMonomio(0,3);
		 
		  ArrayList polinomioDivide = polinomioA.dividePolinomio(polinomioB);
		//  System.out.println("Cociente: " + polinomioDivide.get(1));
		//  System.out.println("Resto: " + polinomioDivide.get(0));

		  assertEquals(polinomioDivide.get(1).toString(), "X^2+0,5X-5,75");
		  assertEquals(polinomioDivide.get(0), 10.625);
		  
	  }	 
	 public void testDividePolinomio6() 
	 {
		 //(6x3) : (x - 1) = 6
		 
		  Polinomio polinomioA = new Polinomio();
		  polinomioA.setMonomio(3,6);
		  
		  Polinomio polinomioB = new Polinomio();
		  polinomioB.setMonomio(1,1);
		  polinomioB.setMonomio(0,-1);
		 
		  ArrayList polinomioDivide = polinomioA.dividePolinomio(polinomioB);
		//  System.out.println("Cociente: " + polinomioDivide.get(1));
		 // System.out.println("Resto: " + polinomioDivide.get(0));
		  
		  assertEquals(polinomioDivide.get(1).toString(), "6X^2+6X+6");
		  assertEquals(polinomioDivide.get(0), 6.0);
	  }	 
}
