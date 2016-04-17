package mirrg.bullet.nickel.item;

import java.util.ArrayList;

public abstract class StackAbstract<T> implements IStack
{

	public final T item;
	public final int amount;

	public StackAbstract(T item, int amount)
	{
		this.item = item;
		this.amount = amount;
	}

	@Override
	public String getNameLocalizedInButtle()
	{
		return amount == 1 ? getNameLocalized() : getNameLocalized() + "×" + amount;
	}

	@Override
	public int getAmount()
	{
		return amount;
	}

	@Override
	public void getMessages(ArrayList<String> messages)
	{
		messages.add("個数：" + amount);
	}

}
