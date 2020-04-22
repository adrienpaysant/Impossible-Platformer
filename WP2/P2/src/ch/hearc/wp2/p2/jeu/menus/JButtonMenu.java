package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class JButtonMenu extends JButton{
	
	JButtonMenu(String name){
		super(name);
		setSize(new Dimension(40,10));
		setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(100,50));
	}
	protected void paintComponent(Graphics g)
	{
		//round borders
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		RoundRectangle2D.Float r2d =new RoundRectangle2D.Float(0, 0, getWidth() , getHeight(), 30, 30);
		g2d.clip(r2d);
 
		g2d.setPaint(Color.WHITE);
		g2d.fillRect(0,0,getWidth(),getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);	
		super.paintComponent(g);
	}
}
