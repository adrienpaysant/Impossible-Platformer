
package ch.hearc.wp2.p2.jeu.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class JFrameBaseLine extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameBaseLine(JComponent jcomponent, boolean isFullScreen)
		{
		this.jcomponent = jcomponent;

		geometry();
		control();
		appearance(isFullScreen);
		}

	public JFrameBaseLine(JComponent jcomponent)
		{
		this(jcomponent, false);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//V1 par defaut une jframe emploie un borderlayout partie centrale!
			{
			//			add(jcomponent);
			}

		//V2 idem V1
			{
			BorderLayout borderLayout = new BorderLayout();
			this.setLayout(borderLayout);
			add(jcomponent, BorderLayout.CENTER);
			}
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance(boolean isFullScreen)
		{
		if (isFullScreen)
			{
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setSize(dim);
			setUndecorated(true);
			}
		else
			{
			setSize(1920/2 ,1080/2);
			}

		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JComponent jcomponent;

	// Tools

	}
