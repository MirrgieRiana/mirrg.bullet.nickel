package mirrg.bullet.nickel.entity;

import java.awt.Graphics2D;

import mirrg.bullet.nickel.phases.PhaseBattle;

public interface IParticle
{

	/**
	 * @return true: Die
	 */
	public boolean move(PhaseBattle phase);

	public default void render(PhaseBattle phase, Graphics2D graphics)
	{

	}

	public default void renderOverlay(PhaseBattle phase, Graphics2D graphics)
	{

	}

}
