
package ch.hearc.wp2.p2.jeu.items;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public  class Item {

	// origin-position0
	private Point2D.Double pt0;
	// size-position1
	private Point2D.Double pt1;

	// visible
	private boolean isVisible;

	// rectangle
	private Rectangle2D.Double rect;

	// constructors
	public Item(double x, double y, double w, double h, boolean v) {
		this.pt0 = new Point2D.Double(x, y);
		this.pt1 = new Point2D.Double(x + w, x + h);
		this.isVisible = v;
		this.rect = new Rectangle2D.Double(pt0.x, pt0.y, pt1.x, pt1.y);
	}

	public Item(Item it) {
		this(it.getPt0().x, it.getPt0().y, it.getPt1().x, it.getPt1().y, it.isVisible);
	}

	public Item(Rectangle2D.Double srect, boolean v) {
		this.pt0 = new Point2D.Double(srect.x, srect.y);
		this.pt1 = new Point2D.Double(srect.x + pt0.x, srect.y + pt0.y);
		this.isVisible = v;
	}

	// getters & setters
	public Point2D.Double getPt0() {
		return pt0;
	}

	public Point2D.Double getPt1() {
		return pt1;
	}

	public boolean isVisible() {
		return this.isVisible;
	}

	public double getHeight() {
		return pt1.y - pt0.y;
	}

	public double getWidth() {
		return pt1.x - pt0.x;
	}

	public Rectangle2D.Double getRect() {
		return rect;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	// moving the rectanle to the point pt
	public void moveTo(Point2D.Double pt) {
		pt0 = pt;
		rect.setRect(pt.x, pt.y, pt.x + this.getWidth(), pt.y + this.getHeight());
	}

	@Override
	public String toString() {
		return "Item [rect=" + this.rect + "isVisible=" + this.isVisible + "]";
	}
}
