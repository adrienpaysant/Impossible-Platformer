
package ch.hearc.wp2.p2.jeu.tools;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.MovingBloc;

public class ChronoMovingBloc implements Runnable {
	private final int DELAY = 2000;// timer between 2 repaint 60FPS

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (MovingBloc mbloc : Map.getInstance().getListMovingBloc()) {
				
				}
			}
		}

	}
