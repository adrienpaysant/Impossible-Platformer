
package ch.hearc.wp2.p2.jeu.tools;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.FallBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.SpikeBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TrapBloc;

public class ChronoTrap implements Runnable {
	private final int DELAY = 2000;// timer between 2 repaint 60FPS

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (TrapBloc tBloc : Map.getInstance().getListTrap()) {
				if (tBloc instanceof SpikeBloc) {
					if (((SpikeBloc) tBloc).isVisible()) {
						tBloc.revertAction();
					}
				}
				if (tBloc instanceof FallBloc) {
					if (((FallBloc) tBloc).isVisible()) {
						tBloc.revertAction();
					}
				}
			}
		}

	}
}