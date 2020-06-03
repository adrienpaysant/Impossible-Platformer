
package ch.hearc.wp2.p2.jeu.tools.position;

import java.awt.Dimension;

import javax.swing.JComponent;

//class to set properly the widht or height of a JComponent

public class JComponents {

	public static void setWidth(JComponent component, int width) {
		component.setMinimumSize(new Dimension(width, component.getMinimumSize().height));
		component.setMaximumSize(new Dimension(width, component.getMaximumSize().height));
		component.setPreferredSize(new Dimension(width, component.getPreferredSize().height));
	}

	public static void setHeight(JComponent component, int height) {
		component.setMinimumSize(new Dimension(component.getMinimumSize().width, height));
		component.setMaximumSize(new Dimension(component.getMaximumSize().width, height));
		component.setPreferredSize(new Dimension(component.getPreferredSize().width, height));
	}
}
