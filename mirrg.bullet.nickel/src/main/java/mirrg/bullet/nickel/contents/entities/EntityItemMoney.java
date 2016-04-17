package mirrg.bullet.nickel.contents.entities;

import java.awt.Color;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class EntityItemMoney extends EntityItemAbstract
{

	private int amount;

	public EntityItemMoney(double x, double y, double xOffset, double yOffset, int score)
	{
		super(x, y, xOffset, yOffset);
		this.amount = score;
	}

	@Override
	public void onCought(PhaseBattle phase)
	{
		phase.session.data.getInventory().addStack(new StackItem(Items.money, amount));
		phase.addParticle(new ParticleText(x, y, "+" + amount, 20, new Color(255, 255, 0, 64)));
	}

	@Override
	public Color getColor()
	{
		return Items.money.getColor();
	}

}
