package fractal;

import java.awt.Color;

public class MandelBrot2D implements IFractal2D {

	@Override
	public int calculateIterations (double cReal, double cImaginary) {

		int iterCount = 0;
		double zReal = 0.0d, zImaginary = 0.0d;

		/*
		 * point calculation: z' = z² + c;
		 * 
		 * z'_r + z'_i = (z_r + z_i)(z_r + z_i) + c_r + c_i;
		 * 
		 * z'_r + z'_i = z_r * z_r + 2 * z_r * z_i - z_i * z_i + c_r + c_i;
		 * 
		 * z'_r = z_r * z_r - z_i * z_i + c_r;
		 * 
		 * z'_i = 2 * z_r * z_i + c_i;
		 * 
		 * set condition: |z| <= 2;
		 * 
		 * √(z_r² + z_i²) <= 2.0;
		 * 
		 * √(z_r * z_r + z_i * z_i) <= 2.0;
		 * 
		 * (z_r * z_r + z_i * z_i) <= 4.0;
		 */

		while ((zReal * zReal + zImaginary * zImaginary) <= 4.0) {
			double zRealTemp = zReal;
			zReal = zReal * zReal - zImaginary * zImaginary + cReal;
			zImaginary = 2 * zRealTemp * zImaginary + cImaginary;
			iterCount++;
			if (iterCount >= IFractal2D.MAX_ITERATIONS) return IFractal2D.MAX_ITERATIONS;
		}

		return iterCount;

	}
	
	@Override
	public int makeColor (int iterCount) {

		int color = 0x8866ff;
		int mask = 0b01111;
		int shiftFactor = iterCount / mask;
		return (iterCount == IFractal2D.MAX_ITERATIONS) ? Color.BLACK.getRGB() :  (color ^ (mask << shiftFactor)) % 100000;

	}

}
