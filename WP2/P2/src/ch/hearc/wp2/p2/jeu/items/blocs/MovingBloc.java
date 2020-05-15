package ch.hearc.wp2.p2.jeu.items.blocs;

import java.awt.Image;

public class MovingBloc extends Bloc {
	private boolean vThF;
	private boolean status;
	private double lenght;
	private double tempLenght;

	public MovingBloc(double x, double y, double w, double h, boolean v, Image texture,
			boolean verticalTrueHorizontalFalse, double lenght) {
		super(x, y, w, h, v, texture);
		this.vThF = verticalTrueHorizontalFalse;

		this.lenght = lenght;
		this.tempLenght = lenght;
		this.status = false;
	}

	public void moveMe() {
		if (!status) {
			// gotostop

			if (vThF) {
				// moving verticaly
				if (tempLenght < 0) {
					// moving top
					moveByY(-1);
					++tempLenght;
					if (tempLenght == 0)
						status = true;
				} else {
					// moving down
					moveByY(1);
					--tempLenght;
					if (tempLenght == 0)
						status = true;
				}
			} else {
				// moving horizontaly
				if (tempLenght < 0) {
					// moving left
					moveByX(-1);
					++tempLenght;
					if (tempLenght == 0) {
						status = true;
					}

				} else {
					// moving right
					moveByX(1);
					--tempLenght;
					if (tempLenght == 0) {
						status = true;
					}
				}

			}
		} else {
			// get back to start

			if (vThF) {
				// moving verticaly
				if (lenght < 0) {
					// moving down
					moveByY(1);
					--tempLenght;
					if (tempLenght == lenght)
						status = false;
				} else {
					// moving up
					moveByY(-1);
					++tempLenght;
					if (tempLenght == lenght)
						status = false;
				}
			} else {
				// moving horizontaly
				if (lenght < 0) {
					// moving right
					moveByX(1);
					--tempLenght;
					if (tempLenght == lenght)
						status = false;
				} else {
					// moving left
					moveByX(-1);
					++tempLenght;
					if (tempLenght == lenght)
						status = false;
				}
			}
			// reset
			if (!status) {
				tempLenght = lenght;
			}
		}
	}
}
