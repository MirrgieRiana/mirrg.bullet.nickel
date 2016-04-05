package mirrg.bullet.nickel.entity;

import java.awt.Graphics2D;
import java.awt.Shape;

import mirrg.bullet.nickel.phases.PhaseBattle;

public interface IEntity
{

	/**
	 * @return true: Die
	 */
	public boolean move(PhaseBattle phase);

	public default void onDie(PhaseBattle phase)
	{

	}

	public void render(PhaseBattle phase, Graphics2D graphics);

	public default void renderOverlay(PhaseBattle phase, Graphics2D graphics)
	{

	}

	public Shape getShape(double additionalRadius);

	public void damage(int value);

}
