package mirrg.bullet.nickel.item;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Recipe
{

	public Inventory in = new Inventory();
	public ArrayList<Supplier<? extends IStack>> out = new ArrayList<>();

	public Recipe addIn(IStack in)
	{
		this.in.addStack(in);
		return this;
	}

	public Recipe addOut(Supplier<? extends IStack> out)
	{
		this.out.add(out);
		return this;
	}

}
