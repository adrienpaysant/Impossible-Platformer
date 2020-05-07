
package ch.hearc.wp2.p2.jeu.items.blocs.actions;

import java.awt.Image;
import java.awt.geom.Rectangle2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

public class CheckPointBloc extends Bloc implements ActionBloc {

	public CheckPointBloc(Bloc it) {
		super(it);
		// TODO Auto-generated constructor stub
	}

	public CheckPointBloc(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interract(Map map) {
		// TODO Auto-generated method stub

	}

}
