
package ch.hearc.wp2.p2.jeu.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.hearc.wp2.p2.jeu.Map;

public class Keyboard implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Map.getInstance().setdX(-1);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Map.getInstance().setdX(1);
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			Map.getInstance().setdY(-1);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Map.getInstance().setdY(1);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		Map.getInstance().setdX(0);
		Map.getInstance().setdY(0);
	}
}
