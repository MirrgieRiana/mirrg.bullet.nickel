package mirrg.bullet.nickel.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import mirrg.bullet.nickel.entity.IParticle;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class ParticleText implements IParticle
{

	public double x;
	public double y;
	public String text;
	public int limit;
	public Color color;

	public ParticleText(double x, double y, String text, int limit, Color color)
	{
		this.x = x;
		this.y = y;
		this.text = text;
		this.limit = limit;
		this.color = color;
	}

	@Override
	public boolean move(PhaseBattle phase)
	{
		limit--;

		return limit <= 0;
	}

	@Override
	public void renderOverlay(PhaseBattle phase, Graphics2D graphics)
	{
		graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		graphics.setColor(color);

		graphics.drawString(text, (int) (x * phase.game.sizeGame.width), (int) (y * phase.game.sizeGame.height));
	}

}
