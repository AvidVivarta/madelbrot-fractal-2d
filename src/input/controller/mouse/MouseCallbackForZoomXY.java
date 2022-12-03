package input.controller.mouse;

import input.controller.Magnification;

@FunctionalInterface
public interface MouseCallbackForZoomXY {
	
	public void adjust(double selectedX, double selectedY, Magnification magnification);

}
