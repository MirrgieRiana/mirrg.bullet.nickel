package mirrg.bullet.nickel.contents.entities;

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
		phase.session.data.getInventory().addStack(stack);
		phase.addParticle(new ParticleText(x, y,
			stack.getNameLocalizedInButtle(),
			20, new Color(255, 128, 128, 64)));
	}

	@Override
	public Color getColor()
	{
		return stack.getColor();
	}

}
