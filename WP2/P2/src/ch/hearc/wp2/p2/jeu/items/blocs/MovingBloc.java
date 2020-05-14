package ch.hearc.wp2.p2.jeu.items.blocs;

import java.awt.Image;

public class MovingBloc extends Bloc {
	private boolean vThF;
	private boolean status;
	private double start;
	private double stop;
	private double xInit;
	private double yInit;

	public MovingBloc(double x, double y, double w, double h, boolean v, Image texture,
			boolean verticalTrueHorizontalFalse, double start, double stop) {
		super(x, y, w, h, v, texture);
		this.vThF = verticalTrueHorizontalFalse;
		this.start = start;
		this.stop = stop;
		this.status = false;
		this.yInit = y;
		this.xInit = x;
	}

	public void moveMe() {
		if (!status) {
			// gotostop
			if (vThF) {
				// moving verticaly
				this.moveByY((stop - start) / (stop - start));// -1 or 1
				if (this.y == stop) {
					status = true;
				}
			} else {
				// moving horizontaly
				this.moveByX((stop - start) / (stop - start));
				if (this.x == stop) {
					status = true;
				}
			}
		} else {
			// get back to start
			// gotostart
			if (vThF) {
				// moving verticaly
				this.moveByY(-(stop - start) / (stop - start));// -1 or 1
				if (this.y == start) {
					status = true;
				}
			} else {
				// moving horizontaly
				this.moveByX(-(stop - start) / (stop - start));
				if (this.x == start) {
					status = true;
				}
			}
		}
	}

}
