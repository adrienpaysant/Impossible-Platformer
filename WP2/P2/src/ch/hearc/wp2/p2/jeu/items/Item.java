
package ch.hearc.wp2.p2.jeu.items;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

//usefull class to DRY and insure compatibility 

@SuppressWarnings("serial")
public abstract class Item extends Rectangle2D.Double {

	private boolean isVisible;

	// constructors
	public Item(double x, double y, double w, double h, boolean v) {
		super(x, y, w, h);
		this.isVisible = v;
	}

	public Item(Item it) {
		this(it.x, it.y, it.width, it.height, it.isVisible);
	}

	// getters & setters
	public boolean isVisible() {
		return this.isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	// moving the rectangle to the point pt
	public void moveTo(Point2D.Double pt) {
		setRect(pt.x, pt.y, this.width, this.height);
	}

	// move from x unit the point p0 and then all the item
	public void moveByX(double x) {
		moveTo(new Point2D.Double(this.x + x, this.y));
	}

	public void moveByY(double y) {
		moveTo(new Point2D.Double(this.x, this.y + y));
	}

	@Override
	public String toString() {
		return "Item [rect=" + super.toString() + "isVisible=" + this.isVisible + "]";
	}
}
