package mirrg.bullet.nickel.core;

import java.awt.Graphics2D;

public interface IGame
{

	public void init(int width, int height);

	public void resized(int width, int height);

	public void move();

	public void paint(Graphics2D graphics);

}
