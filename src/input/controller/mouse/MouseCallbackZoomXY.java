package input.controller.mouse;

@FunctionalInterface
public interface MouseCallbackZoomXY {
	
	public void adjust(double selectedX, double selectedY, Magnification magnification);

}
