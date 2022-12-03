package input.controller.keyboard;

import input.controller.MoveDirection;

@FunctionalInterface
public interface KeyCallbackForMoveDirection {

	public void call(MoveDirection moveDirection);
	
}
