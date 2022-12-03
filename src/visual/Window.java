package visual;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fractal.IFractal2D;
import input.controller.mouse.Magnification;
import input.controller.mouse.MouseCallbackZoomXY;
import input.controller.mouse.MouseController;

public class Window extends JFrame {

	private static final long serialVersionUID = -8949088764654693062L;
	private Display display;
	private MouseController mouseController;
	private IFractal2D fractal2d;

	public Window (IFractal2D fractal2d) {

		this.setInitialGuiProperties();
		this.fractal2d = fractal2d;

		this.display = new Display();
		MouseCallbackZoomXY mouseCallbackZoomXY = (newX, newY, magnification) -> {
			this.adjustZoom(newX, newY, magnification);
		};
		this.mouseController = MouseController.getInstance();
		this.mouseController.setMouseCallbackZoomXY(mouseCallbackZoomXY);
		this.display.addMouseListener(this.mouseController);
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

	public void adjustZoom (double newX, double newY, Magnification magnification) {

		this.display.setTopLeftX(this.display.getTopLeftX() + (newX / this.display.getZoom()));
		this.display.setTopLeftY(this.display.getTopLeftY() - (newY / this.display.getZoom()));

		double newZoom = this.display.getZoom();

		if (Magnification.ZOOM_IN.equals(magnification)) {
			newZoom *= this.display.getZoomFactor();
		} else if (Magnification.ZOOM_OUT.equals(magnification)) {
			newZoom /= this.display.getZoomFactor();
		}

		this.display.setZoom(newZoom);

		this.display
				.setTopLeftX(this.display.getTopLeftX() - (WindowProperties.getWidth() / 2.0d) / this.display.getZoom());
		this.display
				.setTopLeftY(this.display.getTopLeftY() + (WindowProperties.getHeight() / 2.0d) / this.display.getZoom());

		this.update();

	}

}
