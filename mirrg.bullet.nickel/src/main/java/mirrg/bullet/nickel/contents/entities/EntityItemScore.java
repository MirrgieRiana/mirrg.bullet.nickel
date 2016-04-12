package mirrg.bullet.nickel.contents.entities;

import java.awt.Color;

import mirrg.bullet.nickel.phases.PhaseBattle;

public class EntityItemScore extends EntityItemAbstract
{

	private int score;

	public EntityItemScore(double x, double y, double xOffset, double yOffset, int score)
	{
		super(x, y, xOffset, yOffset);
		this.score = score;
	}

	@Override
	public void onCought(PhaseBattle phase)
	{
		phase.addScore(score);
		phase.addParticle(new ParticleText(x, y, "+" + score, 20, new Color(255, 255, 0, 64)));
	}

	@Override
	public Color getColor()
	{
		return Color.blue;
	}

}
