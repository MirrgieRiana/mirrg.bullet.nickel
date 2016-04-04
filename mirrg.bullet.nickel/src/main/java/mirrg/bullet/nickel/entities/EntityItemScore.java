package mirrg.bullet.nickel.entities;

import java.awt.Color;

import mirrg.bullet.nickel.Game;

public class EntityItemScore extends EntityItemAbstract
{

	private int score;

	public EntityItemScore(double x, double y, double xOffset, double yOffset, int score)
	{
		super(x, y, xOffset, yOffset);
		this.score = score;
	}

	@Override
	public void onCought(Game game)
	{
		game.score += score;
		game.addParticle(new ParticleText(x, y, "+" + score, 20, new Color(255, 255, 0, 64)));
	}

	@Override
	public Color getColor()
	{
		return Color.blue;
	}

}
