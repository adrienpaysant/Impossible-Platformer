
package ch.hearc.wp2.p2.jeu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ch.hearc.wp2.p2.jeu.menus.MainMenu;

@SuppressWarnings("serial")
public class Game extends JFrame
	{

	private JTabbedPane tabbedPane;

	private MainMenu mainMenu;
	private Map map;

	public Game(String name)
		{
		super(name);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(500, 500));
		setVisible(true);

		mainMenu = new MainMenu(this);
		map = new Map(this);

		tabbedPane = new JTabbedPane(SwingConstants.TOP);

		tabbedPane.addTab("mainMenu",mainMenu);
		tabbedPane.addTab("map",map);
		tabbedPane.setComponentAt(0, mainMenu);
		tabbedPane.setComponentAt(1, map);
		tabbedPane.setEnabledAt(1, false);


		setLayout(new BorderLayout());
		add(tabbedPane,BorderLayout.CENTER);
		}


	public JTabbedPane getTabbedPane()
		{
		return this.tabbedPane;
		}


	public MainMenu getMainMenu()
		{
		return this.mainMenu;
		}


	public Map getMap()
		{
		return this.map;
		}

	}
