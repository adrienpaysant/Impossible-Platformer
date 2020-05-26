
package ch.hearc.wp2.p2.jeu.items.blocs.actions;

import java.awt.Image;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

@SuppressWarnings("serial")
public class CheckPointBloc extends Bloc {

	private boolean isCheck;

	public CheckPointBloc(Bloc it) {
		super(it);
		this.isCheck = false;
	}

	public CheckPointBloc(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v, texture);
		this.isCheck = false;
	}

	// getters & setters

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

}
