
package ch.hearc.wp2.p2.jeu.items;

import java.awt.geom.Rectangle2D;

public abstract class Item
	{

	//position
	private double xPos;
	private double yPos;

	//visible
	private boolean isVisible;

	//size
	private double width;
	private double height;

	//rectangle
	private Rectangle2D.Double rect;

	public Item(double x, double y, double w, double h, boolean v)
		{
		this.xPos = x;
		this.yPos = y;
		this.height = h;
		this.width = w;
		this.isVisible = v;
		this.rect = new Rectangle2D.Double(x, y, w, h);
		}

	public double getxPos()
		{
		return this.xPos;
		}

	public void setxPos(int xPos)
		{
		this.xPos = xPos;
		}

	public double getyPos()
		{
		return this.yPos;
		}

	public void setyPos(int yPos)
		{
		this.yPos = yPos;
		}

	public boolean isVisible()
		{
		return this.isVisible;
		}

	public void setVisible(boolean isVisible)
		{
		this.isVisible = isVisible;
		}

	public double getWidth()
		{
		return this.width;
		}

	public double getHeight()
		{
		return this.height;
		}

	public Rectangle2D.Double getRect()
		{
		return new Rectangle2D.Double(this.xPos, this.yPos, this.width, this.height);
		}

	@Override
	public String toString()
		{
		return "Item [rect=" + this.rect + "isVisible=" + this.isVisible + "]";
		}

	}
