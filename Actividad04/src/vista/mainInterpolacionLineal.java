package vista;

import interpolacionLineal.InterpolacionLineal;

public class mainInterpolacionLineal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InterpolacionLineal il = new InterpolacionLineal(1.0,2.0,0.0001);
		il.calcular();	
		
	}

}
