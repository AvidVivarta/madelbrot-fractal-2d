package input.controller.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {

	@Override
	public void mouseClicked (MouseEvent e) {

		double x = e.getX();
		double y = e.getY();

		switch (e.getButton()) {
			case MouseEvent.BUTTON1: // left
				break;
			case MouseEvent.BUTTON2: // scroll
				break;
			case MouseEvent.BUTTON3: // right
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

}
