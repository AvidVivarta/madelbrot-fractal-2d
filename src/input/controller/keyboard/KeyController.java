package input.controller.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import input.controller.MoveDirection;

public class KeyController implements KeyListener {

	private static KeyController keyController;
	private boolean keys[] = new boolean[120]; // 65535 max chars
	private KeyCallbackForMoveDirection keyCallbackForMoveDirection;

	@Override
	public void keyTyped (KeyEvent e) {

		switch (e.getKeyCode()) {
			// case up
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				this.keyCallbackForMoveDirection.call(MoveDirection.UP);
				break;
			// down
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				this.keyCallbackForMoveDirection.call(MoveDirection.DOWN);
				break;
			// left
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				this.keyCallbackForMoveDirection.call(MoveDirection.LEFT);
				break;
			// right
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				this.keyCallbackForMoveDirection.call(MoveDirection.RIGHT);
				break;
			default:
				break;
		}

	}

	@Override
	public void keyPressed (KeyEvent e) {

		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased (KeyEvent e) {

		keys[e.getKeyCode()] = false;

	}

	private KeyController () {

	}

	public static KeyController getInstance () {

		if (keyController == null) return keyController = new KeyController();
		return keyController;

	}

	public KeyCallbackForMoveDirection getKeyCallbackForMoveDirection () {

		return keyCallbackForMoveDirection;

	}

	public void setKeyCallbackForMoveDirection (KeyCallbackForMoveDirection keyCallbackForMoveDirection) {

		this.keyCallbackForMoveDirection = keyCallbackForMoveDirection;

	}

}
