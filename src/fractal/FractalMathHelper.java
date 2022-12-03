package fractal;

public class FractalMathHelper {
	
	public static double calculateCenter(double val) {
		return (val / (2 * Math.pow(10, (int) Math.log10(val))));
	}

}
