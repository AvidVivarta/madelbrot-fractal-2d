package visual;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fractal.IFractal2D;
import input.controller.Magnification;
import input.controller.keyboard.KeyCallbackForMoveDirection;
import input.controller.keyboard.KeyController;
import input.controller.mouse.MouseCallbackForZoomXY;
import input.controller.mouse.MouseController;

public class Window extends JFrame implements Runnable {

	private static final long serialVersionUID = -8949088764654693062L;
	private Display display;
	private MouseController mouseController;
	private KeyController keyController;
	private IFractal2D fractal2d;
	private ThreadGroup fractalThreadGroup;
	private Thread windowThread, fractal2dThread, displayThread;

	public Window (IFractal2D fractal2d) {

		this.fractalThreadGroup = new ThreadGroup("fractal");

		this.windowThread = new Thread(this.fractalThreadGroup, this, "window");

		this.fractal2dSetup(fractal2d);
		this.setInitialGuiProperties();
		this.addMouseController();
		this.addKeyController();
		this.addDisplay();
		

		this.windowThread.start();

	}

	private void fractal2dSetup (IFractal2D fractal2d) {

		this.fractal2d = fractal2d;
		this.fractal2dThread = new Thread(this.fractalThreadGroup, this.fractal2d, "fractal");
		this.fractal2dThread.start();

	}

	private void addDisplay () {

		this.display = new Display();
		this.displayThread = new Thread(this.fractalThreadGroup, display, "display");
		this.displayThread.start();

		this.display.addKeyListener(this.keyController);
		this.display.addMouseListener(this.mouseController);
		this.display.setVisible(true);
		this.add(display, BorderLayout.CENTER);

	}

	private void addKeyController () {

		KeyCallbackForMoveDirection keyCallbackForMoveDirection = (moveDirection) -> {

			switch (moveDirection) {
				case UP:
					this.moveUp();
					break;
				case DOWN:
					this.moveDown();
					break;
				case LEFT:
					this.moveLeft();
					break;
				case RIGHT:
					this.moveRight();
					break;
				default:
					break;
			}

		};
		this.keyController = KeyController.getInstance();
		this.keyController.setKeyCallbackForMoveDirection(keyCallbackForMoveDirection);

	}

	private void addMouseController () {

		MouseCallbackForZoomXY mouseCallbackForZoomXY = (newX, newY, magnification) -> {
			this.adjustZoom(newX, newY, magnification);
		};
		this.mouseController = MouseController.getInstance();
		this.mouseController.setMouseCallbackZoomXY(mouseCallbackForZoomXY);

	}

	private void setInitialGuiProperties () {

		this.setTitle(WindowProperties.getTitle());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(WindowProperties.getWidth(), WindowProperties.getHeight());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	private void update () {

		int[][] pixelValues = this.fractal2d.getColorValue(this.display.getZoom(), this.display.getTopLeftX(),
				this.display.getTopLeftY());
		this.display.setFractalImageRGB(pixelValues);

	}

	private void adjustZoom (double newX, double newY, Magnification magnification) {

		this.display.setTopLeftX(this.display.getTopLeftX() + (newX / this.display.getZoom()));
		this.display.setTopLeftY(this.display.getTopLeftY() - (newY / this.display.getZoom()));

		double newZoom = this.display.getZoom();

		if (Magnification.ZOOM_IN.equals(magnification)) {
			newZoom *= this.display.getZoomFactor();
		} else if (Magnification.ZOOM_OUT.equals(magnification)) {
			newZoom /= this.display.getZoomFactor();
		}

		this.display.setZoom(newZoom);

		this.display.setTopLeftX(
				this.display.getTopLeftX() - (WindowProperties.getWidth() / 2.0d) / this.display.getZoom());
		this.display.setTopLeftY(
				this.display.getTopLeftY() + (WindowProperties.getHeight() / 2.0d) / this.display.getZoom());

		this.update();

	}

	private void moveUp () {

		double currHeight = WindowProperties.getHeight() / this.display.getZoom();
		this.display.setTopLeftY(this.display.getTopLeftY() + currHeight / this.display.getMoveFactor());
		this.update();

	}

	private void moveDown () {

		double currHeight = WindowProperties.getHeight() / this.display.getZoom();
		this.display.setTopLeftY(this.display.getTopLeftY() - currHeight / this.display.getMoveFactor());
		this.update();

	}

	private void moveLeft () {

		double currWidth = WindowProperties.getWidth() / this.display.getZoom();
		this.display.setTopLeftX(this.display.getTopLeftX() - currWidth / this.display.getMoveFactor());
		this.update();

	}

	private void moveRight () {

		double currHeight = WindowProperties.getWidth() / this.display.getZoom();
		this.display.setTopLeftX(this.display.getTopLeftX() + currHeight / this.display.getMoveFactor());
		this.update();

	}

	@Override
	public void run () {

		this.update();

	}

}
