package mirrg.bullet.nickel.item;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Inventory
{

	public ArrayList<IStack> stacks = new ArrayList<>();

	public void addStack(IStack stack)
	{
		for (int i = 0; i < stacks.size(); i++) {
			if (stacks.get(i).getOreName().equals(stack.getOreName())) {
				stacks.set(i, stacks.get(i).copy(stacks.get(i).getAmount() + stack.getAmount()));
				return;
			}
		}

		stacks.add(stack);
	}

	public boolean isCraftable(Recipe recipe)
	{
		for (IStack stack : recipe.in.stacks) {
			IStack stack2 = search(stack.getOreName());
			if (stack2 == null) return false;
			if (stack2.getAmount() < stack.getAmount()) return false;
		}
		return true;
	}

	public IStack search(String oreName)
	{
		for (IStack stack : stacks) {
			if (stack.getOreName().equals(oreName)) {
				return stack;
			}
		}
		return null;
	}

	public void craft(Recipe recipe)
	{
		for (Supplier<? extends IStack> supplierStack : recipe.out) {
			addStack(supplierStack.get());
		}
		for (IStack stack : recipe.in.stacks) {
			IStack stack2 = search(stack.getOreName());

			int amount = stack2.getAmount() - stack.getAmount();
			if (amount > 0) {
				stacks.set(stacks.indexOf(stack2), stack.copy(amount));
			} else {
				stacks.remove(stack2);
			}
		}
	}

}
