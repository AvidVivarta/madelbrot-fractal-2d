package visual;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fractal.IFractal2D;

public class Window extends JFrame {

	private static final long serialVersionUID = -8949088764654693062L;

	private Display display;

	private IFractal2D fractal2d;

	public Window (IFractal2D fractal2d) {

		this.setInitialGuiProperties();

		this.fractal2d = fractal2d;

		this.display = new Display();
		this.display.setVisible(true);

		this.add(display, BorderLayout.CENTER);

		this.update();

	}

	public void setInitialGuiProperties () {

		this.setTitle(WindowProperties.getTitle());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(WindowProperties.getWidth(), WindowProperties.getHeight());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void update () {

		int[][] pixelValues = this.fractal2d.getColorValue(this.display.getZoom(), this.display.getTopLeftX(),
				this.display.getTopLeftY());
		this.display.setFractalImageRGB(pixelValues);

	}

}
