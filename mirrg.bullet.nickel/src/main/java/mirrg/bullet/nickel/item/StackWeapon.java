package mirrg.bullet.nickel.item;

import java.awt.Color;
import java.util.ArrayList;

import mirrg.bullet.nickel.weapon.card.CardWeapon;

public class StackWeapon extends StackAbstract<CardWeapon>
{

	public StackWeapon(CardWeapon weapon)
	{
		this(weapon, 1);
	}

	public StackWeapon(CardWeapon weapon, int amount)
	{
		super(weapon, amount);
	}

	@Override
	public String getName()
	{
		return item.getName();
	}

	@Override
	public Color getColor()
	{
		return item.getColor();
	}

	@Override
	public void getMessages(ArrayList<String> messages)
	{
		super.getMessages(messages);
		messages.add("Tier：" + item.getTier());
		messages.add("集中火力：" + (int) item.getDamagePerSecond(false));
		messages.add("拡散火力：" + (int) item.getDamagePerSecond(true));
	}

	@Override
	public IStack copy(int amount)
	{
		return new StackWeapon(item, amount);
	}

}
