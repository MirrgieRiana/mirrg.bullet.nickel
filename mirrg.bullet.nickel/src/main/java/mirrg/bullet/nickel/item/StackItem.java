package mirrg.bullet.nickel.item;

import java.awt.Color;

public class StackItem extends StackAbstract<IItem>
{

	public StackItem(IItem item, int amount)
	{
		super(item, amount);
	}

	@Override
	public String getNameLocalized()
	{
		return item.getNameLocalized();
	}

	@Override
	public String getNameOre()
	{
		return item.getNameOre();
	}

	@Override
	public Color getColor()
	{
		return item.getColor();
	}

	@Override
	public IStack copy(int amount)
	{
		return new StackItem(item, amount);
	}

}
