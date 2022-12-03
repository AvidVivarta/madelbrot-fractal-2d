package main;

import fractal.IFractal2D;
import fractal.MandelBrot2D;
import visual.Window;
import visual.WindowProperties;

public class Main {

	public static void main (String[] args) {

		// WindowProperties.setWidth(500);
		// WindowProperties.setRatio(5.0d / 4.0d);
		WindowProperties.setHeight((int) (WindowProperties.getWidth() / WindowProperties.getRatio()));
		IFractal2D fractal2d = new MandelBrot2D();
		Window window = new Window(fractal2d);
		Thread windowThread = new Thread(window, "window");
		windowThread.start();

	}

}
