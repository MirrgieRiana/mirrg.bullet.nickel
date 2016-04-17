package mirrg.bullet.nickel.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Supplier;

public class Inventory
{

	private ArrayList<IStack> stacks = new ArrayList<>();
	private HashSet<String> unlockeds = new HashSet<>();

	public void addStack(IStack stack)
	{
		for (int i = 0; i < stacks.size(); i++) {
			if (stacks.get(i).getNameOre().equals(stack.getNameOre())) {
				stacks.set(i, stacks.get(i).copy(stacks.get(i).getAmount() + stack.getAmount()));
				return;
			}
		}

		unlockeds.add(stack.getNameOre());

		stacks.add(stack);
	}

	public boolean isCraftable(Recipe recipe)
	{
		for (IStack stack : recipe.in.stacks) {
			IStack stack2 = search(stack.getNameOre()).orElse(null);
			if (stack2 == null) return false;
			if (stack2.getAmount() < stack.getAmount()) return false;
		}
		return true;
	}

	public boolean isVisible(Recipe recipe)
	{
		for (String key : recipe.keys) {
			if (!unlockeds.contains(key)) return false;
		}
		return true;
	}

	public Optional<IStack> search(String oreName)
	{
		for (IStack stack : stacks) {
			if (stack.getNameOre().equals(oreName)) {
				return Optional.of(stack);
			}
		}
		return Optional.empty();
	}

	public void craft(Recipe recipe)
	{
		for (Supplier<? extends IStack> supplierStack : recipe.out) {
			addStack(supplierStack.get());
		}
		for (IStack stack : recipe.in.stacks) {
			IStack stack2 = search(stack.getNameOre()).get();

			int amount = stack2.getAmount() - stack.getAmount();
			if (amount > 0) {
				stacks.set(stacks.indexOf(stack2), stack.copy(amount));
			} else {
				stacks.remove(stack2);
			}
		}
	}

	public int size()
	{
		return stacks.size();
	}

	public IStack get(int index)
	{
		return stacks.get(index);
	}

}
