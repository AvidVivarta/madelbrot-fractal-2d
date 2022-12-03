package input.controller.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.Serializable;

import input.controller.Magnification;

public class MouseController implements MouseListener, MouseWheelListener, Serializable {

	
	private static final long serialVersionUID = 5414219222002857612L;
	private static MouseController mouseController;
	private MouseCallbackForZoomXY mouseCallbackForZoomXY;

	private MouseController () {

	}
	

	@Override
	public void mouseWheelMoved (MouseWheelEvent e) {
		double x = e.getX(), y = e.getY();
		int step = e.getWheelRotation();
		if(step > 0) {
			this.mouseCallbackForZoomXY.adjust(x, y, Magnification.ZOOM_OUT);
		}else {
			this.mouseCallbackForZoomXY.adjust(x, y, Magnification.ZOOM_IN);
		}
	}

	@Override
	public void mouseClicked (MouseEvent e) {

		double x = e.getX(), y = e.getY();

		switch (e.getButton()) {
			case MouseEvent.BUTTON1: // left
				this.mouseCallbackForZoomXY.adjust(x, y, Magnification.ZOOM_IN);
				break;
			case MouseEvent.BUTTON3: // right
				this.mouseCallbackForZoomXY.adjust(x, y, Magnification.ZOOM_OUT);
				break;
			default:
				break;
		}

	}

	@Override
	public void mousePressed (MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased (MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered (MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited (MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	
	public static MouseController getInstance () {

		if (mouseController == null) return mouseController = new MouseController();
		return mouseController;

	}

	public MouseCallbackForZoomXY getMouseCallbackZoomXY () {

		return mouseCallbackForZoomXY;

	}

	public void setMouseCallbackZoomXY (MouseCallbackForZoomXY mouseCallbackForZoomXY) {

		this.mouseCallbackForZoomXY = mouseCallbackForZoomXY;

	}


}
