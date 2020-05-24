
package ch.hearc.wp2.p2.jeu.tools;

import ch.hearc.wp2.p2.jeu.Map;

public class Chrono implements Runnable {
	private final int DELAY = 15;// timer between 2 repaint 60FPS

	@Override
	public void run() {
		while (true) {
			if(Map.getInstance().getdX()==0) {
				Map.getInstance().getPlayer().setRun(false);
				Map.getInstance().getPlayer().setWalk(false);
			}
			else {
				Map.getInstance().getPlayer().setWalk(true);
			}
			Map.getInstance().repaint();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
