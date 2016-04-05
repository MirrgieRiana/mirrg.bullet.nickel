package mirrg.bullet.nickel;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface IPhase
{

	public void move();

	public void paint(Graphics2D graphics);

	public default void paintScore(Graphics2D graphics, Dimension size, Counter counter)
	{

	}

}
