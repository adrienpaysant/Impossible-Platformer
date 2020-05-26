
package ch.hearc.wp2.p2.jeu.items.blocs.traps;

import java.awt.Image;

import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

@SuppressWarnings("serial")
public abstract class TrapBloc extends Bloc {

	public TrapBloc(double x, double y, double w, double h, boolean v, Image texture, TypeTrap type) {
		super(x, y, w, h, v, texture);
		this.type = type;
	}

	public abstract void trapAction();

	public abstract void revertAction();

	public TypeTrap type;

	public TypeTrap getType() {
		return type;
	}
}
