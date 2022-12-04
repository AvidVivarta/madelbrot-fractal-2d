package fractal;

import java.awt.Color;

import visual.WindowProperties;

@FunctionalInterface
public interface IFractal2D extends Runnable {

	int MAX_ITERATIONS = 200;
	int BUFFER_SIZE = 2;

	public int calculateIterations (double cReal, double cImaginary);

	public default int[][] getColorValue (double zoom, double topLeftX, double topLeftY) {

		int[][] colorValues = new int[WindowProperties.getWidth()][WindowProperties.getHeight()];

		for (int x = 0; x < WindowProperties.getWidth(); x++) {

			for (int y = 0; y < WindowProperties.getHeight(); y++) {
				double cReal = this.getXPosition(x, zoom, topLeftX);
				double cImaginary = this.getYPosition(y, zoom, topLeftY);
				int iterCount = this.calculateIterations(cReal, cImaginary);
				int pixelColor = this.makeColor(iterCount);
				colorValues[x][y] = pixelColor;
			}

		}

		return colorValues;

	}

	default int makeColor (int iterCount) {

		int color = 0xff6699;
		int mask = 0b11;
		int shiftFactor = iterCount / mask;
		return (iterCount == IFractal2D.MAX_ITERATIONS) ? Color.BLACK.getRGB() : color | (mask << shiftFactor);

	}

	default double getYPosition (double y, double zoom, double topLeftY) {

		return y / zoom - topLeftY;

	}

	default double getXPosition (double x, double zoom, double topLeftX) {

		return x / zoom + topLeftX;

	}

	@Override
	default void run () {

	}

}
