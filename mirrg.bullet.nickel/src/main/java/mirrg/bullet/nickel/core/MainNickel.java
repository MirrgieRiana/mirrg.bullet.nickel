package mirrg.bullet.nickel.core;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainNickel
{

	public static void main(String[] args)
	{
		JFrame jFrame = new JFrame();

		PanelNickel panel = new PanelNickel();
		panel.setGame(new GameNickel(panel));
		jFrame.add(panel);

		jFrame.setLocationByPlatform(true);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setLayout(new CardLayout());
		jFrame.setSize(1300, 1000);

		panel.init();

		jFrame.setVisible(true);

		panel.start();
	}

}
