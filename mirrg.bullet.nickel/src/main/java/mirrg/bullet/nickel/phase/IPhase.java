package mirrg.bullet.nickel.phase;

import java.awt.Dimension;
import java.awt.Graphics2D;

import mirrg.bullet.nickel.gui.Counter;

public interface IPhase
{

	public default void init()
	{

	}

	public default void onResized()
	{

	}

	public void move();

	public void paint(Graphics2D graphics);

	public default void paintScore(Graphics2D graphics, Dimension size, Counter counter)
	{

	}

}
