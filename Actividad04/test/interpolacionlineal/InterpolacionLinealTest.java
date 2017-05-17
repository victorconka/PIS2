package interpolacionlineal;

import interpolacionlineal.InterpolacionLineal;
import junit.framework.TestCase;

public class InterpolacionLinealTest extends TestCase {
	InterpolacionLineal il;
	
	public void testInit(){
		il  = new InterpolacionLineal();
		assertEquals(il.equals(null),false);
	}
	
	public void testWrongError(){
		il  = new InterpolacionLineal(1.0,2.0,-0.0001);
		Double p = il.getError();
		assertEquals(p.equals(0.0001),true);
	}
	
	public void testSetInterval(){
		il  = new InterpolacionLineal(1.0,2.0,-0.0001);
		Double[] p = il.getInterval();
		assertEquals(p[0].equals(1.0),true);
		assertEquals(p[1].equals(2.0),true);
		
		il.setIterval(0.0, 3.0);
		p = il.getInterval();
		assertEquals(p[0].equals(0.0),true);
		assertEquals(p[1].equals(3.0),true);
	}
	
	public void testSetPrecision(){
		il  = new InterpolacionLineal(1.0,2.0,0.0001);
		//precision por defecto 5
		assertEquals(il.getPrecision(),5);
		
		//precision se establece correctamente
		il.setPrecision(10);
		assertEquals(il.getPrecision(),10);
		
		il  = new InterpolacionLineal(1.0,2.0,0.0000001);
		//precision se ajusta al error
		assertEquals(il.getPrecision(),7);		
	}
	
	public void testToString(){
		il  = new InterpolacionLineal(1.0,2.0,0.0001);
		assertEquals(il.toString().contains("[1.0,2.0]"),true);
	}
	
	public void testBolzano(){
		il  = new InterpolacionLineal(1.0,2.0,0.0001);
		assertEquals(il.verificarBolzano(),true);
		
		il  = new InterpolacionLineal(2.0,3.0,0.0001);
		assertEquals(il.verificarBolzano(),false);
	}
	
	public void testGetPolinomio(){
		il  = new InterpolacionLineal(1.0,2.0,0.0001);
		assertEquals(il.getPolinomio(),"X^5-X^4+X^3-3");
	}
	
	public void testCalcular(){
		//cobertura actualizar x1
		il  = new InterpolacionLineal(1.0,2.0,0.0001);
		il.calcular();
		
		//cobertura actualizar x2
		il  = new InterpolacionLineal(1.0,0.0,0.0001);
		il.calcular();
		
		//cobertura 
		//no se puede alcanzar error 0 porque la raíz es aproximada o imaginaría
	}
	
	public void testIteraciones(){
		il  = new InterpolacionLineal(1.0,2.0,0.0001);
		
		int n = 100;
		il.setNumeroIteraciones(n);
		
		System.out.println(il.getNumeroIteraciones());
		assertEquals(il.getNumeroIteraciones().equals(n), true);
		
		n = 0;
		il.setNumeroIteraciones(n);
		
		System.out.println(il.getNumeroIteraciones());
		assertEquals(il.getNumeroIteraciones().equals(n), false);
		
		n = -10;
		il.setNumeroIteraciones(n);
		
		System.out.println(il.getNumeroIteraciones());
		assertEquals(il.getNumeroIteraciones().equals(n), false);
		
	}
	
}
