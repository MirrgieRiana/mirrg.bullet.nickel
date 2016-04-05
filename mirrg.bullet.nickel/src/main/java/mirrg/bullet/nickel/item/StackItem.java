package mirrg.bullet.nickel.item;

public class StackItem implements IStack
{

	public final Item item;
	public final int amount;

	public StackItem(Item item, int amount)
	{
		this.item = item;
		this.amount = amount;
	}

	@Override
	public String getNameInButtle()
	{
		return amount == 1 ? item.name : item.name + "×" + amount;
	}

	@Override
	public String getName()
	{
		return item.name;
	}

	@Override
	public int getAmount()
	{
		return amount;
	}

}
