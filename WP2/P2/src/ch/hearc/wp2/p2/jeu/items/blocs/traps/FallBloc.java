
package ch.hearc.wp2.p2.jeu.items.blocs.traps;

import java.awt.Image;
import java.awt.geom.Rectangle2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

public class FallBloc extends  Bloc implements TrapBloc {


	public FallBloc(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void trapAction() {
		// TODO Auto-generated method stub

	}


	@Override
	public void revertAction() {
		// TODO Auto-generated method stub
		
	}
	

}
