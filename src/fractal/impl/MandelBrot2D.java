package fractal.impl;

import java.awt.Color;

import fractal.IFractal2D;
import visual.WindowProperties;

public class MandelBrot2D implements IFractal2D {

	private int[][] colorValues;

	public MandelBrot2D() {
		this.colorValues = new int[WindowProperties.getWidth()][ WindowProperties.getHeight()];
	}

	@Override
	public int calculateIterations(double cReal, double cImaginary) {
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
			if (iterCount >= IFractal2D.MAX_ITERATIONS)
				return IFractal2D.MAX_ITERATIONS;
		}

		return iterCount;
	}

	@Override
	public int[][] getColorValue(double zoom, double topLeftX, double topLeftY) {

		
		for (int x = 0; x < WindowProperties.getWidth(); x++) {
			for (int y = 0; y < WindowProperties.getHeight(); y++) {
				double cReal = this.getXPosition(x, zoom, topLeftX);
				double cImaginary = this.getYPosition(y, zoom, topLeftY);
				int iterCount = this.calculateIterations(cReal, cImaginary);
				int pixelColor = this.makeColor(iterCount);
				this.colorValues[x][y] = pixelColor;
				// if (pixelColor == Color.BLACK.getRGB())
				// System.out.format("[x: %d, y: %d, color val: %d, iterCount: %d]\n", x, y, pixelColor, iterCount);
			}
//			System.out.println();
		}

		return colorValues;
	}

	private int makeColor(int iterCount) {
		return (iterCount == IFractal2D.MAX_ITERATIONS) ? Color.BLACK.getRGB() : Color.BLUE.getRGB();
	}

	private double getYPosition(double y, double zoom, double topLeftY) {
		return y / zoom - topLeftY;
	}

	private double getXPosition(double x, double zoom, double topLeftX) {
		return x / zoom + topLeftX;
	}

}