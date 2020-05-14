package ch.hearc.wp2.p2.jeu.items.blocs;

import java.awt.Image;

public class MovingBloc extends Bloc {
	private boolean vThF;
	private boolean status;
	private double lenght;

	public MovingBloc(double x, double y, double w, double h, boolean v, Image texture,
			boolean verticalTrueHorizontalFalse, double lenght) {
		super(x, y, w, h, v, texture);
		this.vThF = verticalTrueHorizontalFalse;
		this.lenght = lenght;
		this.status = false;
	}

	public void moveMe() {
		if (!status) {
			// gotostop

			if (vThF) {
				// moving verticaly

			} else {
				// moving horizontaly

			}
		} else {
			// get back to start

			if (vThF) {
				// moving verticaly

			} else {
				// moving horizontaly

			}
		}
	}
}
