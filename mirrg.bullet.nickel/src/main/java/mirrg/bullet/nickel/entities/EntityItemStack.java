package mirrg.bullet.nickel.entities;

import java.awt.Color;

import mirrg.bullet.nickel.item.IStack;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class EntityItemStack extends EntityItemAbstract
{

	private IStack stack;

	public EntityItemStack(double x, double y, double xOffset, double yOffset, IStack stack)
	{
		super(x, y, xOffset, yOffset);
		this.stack = stack;
	}

	@Override
	public void onCought(PhaseBattle phase)
	{
		phase.game.inventory.addStack(stack);
		phase.addParticle(new ParticleText(x, y,
			stack.getNameInButtle(),
			20, new Color(255, 128, 128, 64)));
	}

	@Override
	public Color getColor()
	{
		return Color.red;
	}

}
