package mirrg.bullet.nickel.entities;

import java.awt.Color;

import mirrg.bullet.nickel.Game;
import mirrg.bullet.nickel.item.Stack;

public class EntityItemStack extends EntityItemAbstract
{

	private Stack stack;

	public EntityItemStack(double x, double y, double xOffset, double yOffset, Stack stack)
	{
		super(x, y, xOffset, yOffset);
		this.stack = stack;
	}

	@Override
	public void onCought(Game game)
	{
		game.addStack(stack);
		game.addParticle(new ParticleText(x, y,
			stack.amount == 1 ? stack.item.name : stack.item.name + "×" + stack.amount,
			20, new Color(255, 128, 128, 64)));
	}

	@Override
	public Color getColor()
	{
		return Color.red;
	}

}
