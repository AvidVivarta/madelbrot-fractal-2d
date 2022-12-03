package fractal;

public interface IFractal2D {

	int MAX_ITERATIONS = 200;
	int BUFFER_SIZE = 2;

	public int calculateIterations (double cReal, double cImaginary);

	public int[][] getColorValue (double zoomFactor, double topLeftX, double topLeftY);

}
