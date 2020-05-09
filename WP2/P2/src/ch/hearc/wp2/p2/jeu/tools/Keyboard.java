
package ch.hearc.wp2.p2.jeu.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.hearc.wp2.p2.jeu.Map;

public class Keyboard implements KeyListener {

	private boolean left = false;
	private boolean right = false;
	private String whoIsLast;

	private void test() {
		if (left && whoIsLast == "left")
			Map.getInstance().setdX(-1);
		else if (right && whoIsLast == "right")
			Map.getInstance().setdX(1);
		else
			Map.getInstance().setdX(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.left = true;
			this.whoIsLast = "left";
		} // right
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.right = true;
			this.whoIsLast = "right";
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			Map.getInstance().getPlayer().jump();
		}
		test();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.left = false;
			if (whoIsLast == "left" && right)
				whoIsLast = "right";
		} // right
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.right = false;
			if (whoIsLast == "right" && left)
				whoIsLast = "left";
		}
		test();
	}

}