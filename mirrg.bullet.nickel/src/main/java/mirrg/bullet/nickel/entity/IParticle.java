package mirrg.bullet.nickel.entity;

import java.awt.Graphics2D;

import mirrg.bullet.nickel.Game;

public interface IParticle
{

	/**
	 * @return true: Die
	 */
	public boolean move(Game game);

	public default void render(Game game, Graphics2D graphics)
	{

	}

	public default void renderOverlay(Game game, Graphics2D graphics)
	{

	}

}
