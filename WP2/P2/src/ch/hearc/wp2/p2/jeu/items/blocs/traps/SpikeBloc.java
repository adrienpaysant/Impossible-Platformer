
package ch.hearc.wp2.p2.jeu.items.blocs.traps;

import java.awt.Image;
import java.awt.geom.Rectangle2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

public class SpikeBloc extends Bloc implements TrapBloc {

	public SpikeBloc(Bloc it) {
		super(it);
		// TODO Auto-generated constructor stub
	}

	
	public SpikeBloc(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void trapAction(Map map) {
		// TODO Auto-generated method stub

	}

}
