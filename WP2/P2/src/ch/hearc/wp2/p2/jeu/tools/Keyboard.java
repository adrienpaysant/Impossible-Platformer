
package ch.hearc.wp2.p2.jeu.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.menus.PauseMenu;

public class Keyboard implements KeyListener {

	private boolean left = false;
	private boolean right = false;
	private boolean shift = false;
	private String whoIsLast;

	private void test() {
		if (left && whoIsLast == "left") {
			if (shift)
				Map.getInstance().setdX(-1.5);
			else
				Map.getInstance().setdX(-1);
		} else if (right && whoIsLast == "right") {
			if (shift)
				Map.getInstance().setdX(1.5);
			else
				Map.getInstance().setdX(1);
		} else {
			Map.getInstance().setdX(0);

		}
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
			Map.getInstance().getPlayer().setWalk(true);
		} // right
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.right = true;
			this.whoIsLast = "right";
			Map.getInstance().getPlayer().setWalk(true);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Map.getInstance().getPlayer().jump();
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shift = true;
			Map.getInstance().getPlayer().setRun(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_P) {
			Game.getInstance().setSize(Game.getInstance().getWidth() + 1, Game.getInstance().getHeight() + 1);
			Game.getInstance().setContentPane(PauseMenu.getInstance());
			// for the focus in pause menu
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					PauseMenu.getInstance().requestFocusInWindow();
				}
			});
			Game.getInstance().setSize(Game.getInstance().getWidth() - 1, Game.getInstance().getHeight() - 1);
			Map.getInstance().setdX(0);
			this.whoIsLast = "";
			this.left = false;
			this.right = false;
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
			Map.getInstance().getPlayer().setWalk(false);
		} // right
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.right = false;
			if (whoIsLast == "right" && left)
				whoIsLast = "left";
			Map.getInstance().getPlayer().setWalk(false);
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shift = false;
			Map.getInstance().getPlayer().setRun(false);
		}
		test();
	}

}