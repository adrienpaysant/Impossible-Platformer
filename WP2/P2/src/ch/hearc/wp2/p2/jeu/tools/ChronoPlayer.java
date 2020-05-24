package ch.hearc.wp2.p2.jeu.tools;

import ch.hearc.wp2.p2.jeu.Map;

public class ChronoPlayer implements Runnable {

	@Override
	public void run() {
		while (true) {

			Map.getInstance().getPlayer().setImage();
			Map.getInstance().repaint();
			try {
				Thread.sleep(Map.getInstance().getPlayer().getSleepFreq());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
