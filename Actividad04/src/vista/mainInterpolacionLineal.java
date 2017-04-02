package vista;

import java.util.Locale;

import interpolacionLineal.InterpolacionLineal;

public class mainInterpolacionLineal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InterpolacionLineal il = new InterpolacionLineal(1.0,2.0,0.0001);
		//System.out.println(il.toString());
		
		il.calcular();
		System.out.println(il.getPrecision());
		//System.out.println(String.format(Locale.US, "%." + 5 + "f", 1.286864));
		//System.out.println("0100000".replaceAll("0+$", ""));
	}

}
