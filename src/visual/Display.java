package visual;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fractal.FractalMathHelper;

public class Display extends JPanel {

	private static final double DEFAULT_ZOOM = 100.0d;
	private static final double DEFAULT_ZOOM_FACTOR = 2.0d;
	private static final double DEFAULT_TOP_LEFT_X = -1
			* FractalMathHelper.calculateCenter(WindowProperties.getWidth());
	private static final double DEFAULT_TOP_LEFT_Y = 1
			* FractalMathHelper.calculateCenter(WindowProperties.getHeight());

	private double zoom = DEFAULT_ZOOM;
	private double zoomFactor = DEFAULT_ZOOM_FACTOR;
	private double topLeftX = DEFAULT_TOP_LEFT_X;
	private double topLeftY = DEFAULT_TOP_LEFT_Y;

	private BufferedImage fractalImage;
	
	
	private static final long serialVersionUID = -2412962973177298795L;

	public Display () {

		fractalImage = new BufferedImage(WindowProperties.getWidth(), WindowProperties.getHeight(),
				BufferedImage.TYPE_INT_RGB);

	}

	@Override
	public Dimension getPreferredSize () {

		return new Dimension(WindowProperties.getWidth(), WindowProperties.getHeight());

	}

	@Override
	protected void paintComponent (Graphics g) {

		g.drawImage(fractalImage, 0, 0, null);

	}

	public void setFractalImageRGB (int[][] colorValues) {

		// x: col, y: row.
		for (int x = 0; x < WindowProperties.getWidth(); x++) {

			for (int y = 0; y < WindowProperties.getHeight(); y++) {
				fractalImage.setRGB(x, y, colorValues[x][y]);
			}

		}

		this.repaint();

	}

	public double getZoom () {

		return zoom;

	}

	public void setZoom (double zoom) {

		this.zoom = zoom;

	}

	public double getZoomFactor () {

		return zoomFactor;

	}

	public void setZoomFactor (double zoomFactor) {

		this.zoomFactor = zoomFactor;

	}

	public double getTopLeftX () {

		return topLeftX;

	}

	public void setTopLeftX (double topLeftX) {

		this.topLeftX = topLeftX;

	}

	public double getTopLeftY () {

		return topLeftY;

	}

	public void setTopLeftY (double topLeftY) {

		this.topLeftY = topLeftY;

	}

}
