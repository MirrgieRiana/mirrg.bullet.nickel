package mirrg.bullet.nickel.entity;

import java.awt.Graphics2D;
import java.awt.Shape;

import mirrg.bullet.nickel.Game;

public interface IEntity
{

	/**
	 * @return true: Die
	 */
	public boolean move(Game game);

	public default void onDie(Game game)
	{

	}

	public void render(Game game, Graphics2D graphics);

	public default void renderOverlay(Game game, Graphics2D graphics)
	{

	}

	public Shape getShape(double additionalRadius);

	public void damage();

}
