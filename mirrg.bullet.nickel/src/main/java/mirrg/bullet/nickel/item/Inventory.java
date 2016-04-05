package mirrg.bullet.nickel.item;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import mirrg.bullet.nickel.Counter;

public class Inventory
{

	public ArrayList<IStack> stacks = new ArrayList<>();

	public void addStack(IStack stack)
	{
		if (stack instanceof StackItem) {
			for (int i = 0; i < stacks.size(); i++) {
				IStack stack2 = stacks.get(i);
				if (stack2 instanceof StackItem) {
					if (((StackItem) stack2).item.equals(((StackItem) stack).item)) {
						stacks.set(i, new StackItem(((StackItem) stack2).item, ((StackItem) stack2).amount + ((StackItem) stack).amount));
						return;
					}
				}
			}
		}
		stacks.add(stack);
	}

	public void drawScore(Graphics2D graphics, Dimension size, Counter counter)
	{
		graphics.setColor(Color.white);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		for (int i = 0; i < stacks.size(); i++) {

			graphics.drawString(stacks.get(i).getName(),
				0, counter.value + graphics.getFont().getSize());
			graphics.drawString(
				"" + stacks.get(i).getAmount(),
				size.width - 5 - graphics.getFontMetrics().stringWidth("" + stacks.get(i).getAmount()),
				counter.value + graphics.getFont().getSize());
			counter.add(graphics.getFont().getSize());

		}
	}

}
