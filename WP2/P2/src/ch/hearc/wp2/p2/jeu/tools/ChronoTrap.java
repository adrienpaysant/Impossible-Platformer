
package ch.hearc.wp2.p2.jeu.tools;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.SpikeBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TrapBloc;


//timer to manage the trap
public class ChronoTrap implements Runnable {
	private final int DELAY = 3000;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (TrapBloc tBloc : Map.getInstance().getListTrap()) {
				if (tBloc instanceof SpikeBloc && ((SpikeBloc) tBloc).isVisible()) {
					tBloc.revertAction();
				}
			}
		}
	}
}
