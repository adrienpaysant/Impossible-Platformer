
package ch.hearc.wp2.p2.jeu.items;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public abstract class Item {

	// visible
	private boolean isVisible;

	// rectangle
	private Rectangle2D.Double rect;

	// constructors
	public Item(double x, double y, double w, double h, boolean v) {
		this.isVisible = v;
		this.rect = new Rectangle2D.Double(x, y, w, h);
	}

	public Item(Item it) {
		this(it.getRect().x, it.getRect().y, it.getRect().width, it.getRect().height, it.isVisible);
	}

	public Item(Rectangle2D.Double srect, boolean v) {
		this.isVisible = v;
		this.rect = srect;
	}

	// getters & setters
	public boolean isVisible() {
		return this.isVisible;
	}

	public Rectangle2D.Double getRect() {
		return rect;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	//methode
	// moving the rectangle to the point pt
	public void moveTo(Point2D.Double pt) {
		rect.setRect(new Rectangle2D.Double(pt.x,pt.y,rect.width,rect.height));
	}

	// move from x unit the point p0 and then all the item
	public void moveByX(double x) {
		this.moveTo(new Point2D.Double(rect.x + x, rect.y));
	}

	public void moveByY(double y) {
		this.moveTo(new Point2D.Double(rect.x, rect.y + y));
	}

	@Override
	public String toString() {
		return "Item [rect=" + this.rect + "isVisible=" + this.isVisible + "]";
	}
}
