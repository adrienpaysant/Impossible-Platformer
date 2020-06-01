
package ch.hearc.wp2.p2.jeu.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.Map;

public class KeyboardMenuPause implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_P) {

			Game.getInstance().setSize(Game.getInstance().getWidth() + 1, Game.getInstance().getHeight() + 1);
			Game.getInstance().setContentPane(Map.getInstance());
			// for the focus in map
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Map.getInstance().requestFocusInWindow();
				}
			});
			Game.getInstance().setSize(Game.getInstance().getWidth() - 1, Game.getInstance().getHeight() - 1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}