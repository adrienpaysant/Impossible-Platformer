
package ch.hearc.wp2.p2.jeu.items.blocs.actions;

import java.awt.Image;
import java.awt.geom.Rectangle2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

public class CheckPointBloc extends Bloc implements ActionBloc {
	
	private boolean isCheck;
	
	public CheckPointBloc(Bloc it) {
		super(it);
		this.isCheck=false;
	}

	public CheckPointBloc(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v, texture);
		this.isCheck=false;
	}

	@Override
	public void interract(Map map) {
		// TODO Auto-generated method stub

	}

}
