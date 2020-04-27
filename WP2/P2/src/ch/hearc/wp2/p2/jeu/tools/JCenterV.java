package ch.hearc.wp2.p2.jeu.tools;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class JCenterV extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JCenterV(JComponent component)
		{
		super(BoxLayout.Y_AXIS);

		this.component = component;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		add(Box.createVerticalGlue());
		add(component);
		add(Box.createVerticalGlue());
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JComponent component;

	// Tools

	}
