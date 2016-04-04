package mirrg.bullet.nickel;

import java.applet.Applet;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main
{

	public static void main(String[] args)
	{
		JFrame jFrame = new JFrame();

		Applet panel = new PanelNickel();
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
