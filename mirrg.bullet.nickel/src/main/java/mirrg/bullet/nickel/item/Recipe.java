package mirrg.bullet.nickel.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Supplier;

public class Recipe
{

	public Inventory in = new Inventory();
	public HashSet<String> keys = new HashSet<>();
	public ArrayList<Supplier<? extends IStack>> out = new ArrayList<>();

	public Recipe addIn(IStack in, boolean isKey)
	{
		this.in.addStack(in);
		if (isKey) keys.add(in.getNameOre());
		return this;
	}

	public Recipe addOut(Supplier<? extends IStack> out)
	{
		this.out.add(out);
		return this;
	}

}
