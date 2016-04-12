package mirrg.bullet.nickel.phases;

import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.item.IStack;
import mirrg.bullet.nickel.item.StackWeapon;

public class PhaseInventory extends PhaseListAbstract
{

	public PhaseInventory(GameNickel game)
	{
		super(game);
	}

	@Override
	protected int getItemCount()
	{
		return game.inventory.stacks.size();
	}

	@Override
	protected void putRecord(GameNickel game, Rectangle2D.Double area, int row)
	{
		IStack stack = game.inventory.stacks.get(row + lineCount * page);

		if (stack instanceof StackWeapon) {

			{
				Rectangle2D.Double area2 = new Rectangle2D.Double(area.x + 5, area.y + 5 + (25 + 5) * row, 25, 25);
				Label e = new Label(new Point2D.Double(area2.getCenterX(), area2.getCenterY()),
					"", new Font(Font.SANS_SERIF, Font.PLAIN, 24));
				hookReflesh(() -> {
					e.text = game.weaponMain.equals(stack) ? "★" : "←";
				});
				components.add(new Button(game, area2)
					.setOnClick(() -> {
						game.weaponMain = (StackWeapon) stack;
						fireReflesh();
					}));
				components.add(e);
			}

			{
				Rectangle2D.Double area2 = new Rectangle2D.Double(area.x + 35, area.y + 5 + (25 + 5) * row, 25, 25);
				Label e = new Label(new Point2D.Double(area2.getCenterX(), area2.getCenterY()),
					"", new Font(Font.SANS_SERIF, Font.PLAIN, 24));
				hookReflesh(() -> {
					e.text = game.weaponSub.equals(stack) ? "★" : "→";
				});
				components.add(new Button(game, area2)
					.setOnClick(() -> {
						game.weaponSub = (StackWeapon) stack;
						fireReflesh();
					}));
				components.add(e);
			}

		}

		{
			Rectangle2D.Double area2 = new Rectangle2D.Double(area.x + 65, area.y + 5 + (25 + 5) * row, 330, 25);
			components.add(new Button(game, area2)
				.setOnMouseIn(() -> {
					messages.clear();
					messages.add(stack.getName() + "：");
					stack.getMessages(messages);
				}));

			components.add(new Label(new Point2D.Double(area.x + 70, area.y + 5 + 12 + (25 + 5) * row),
				stack.getName(),
				new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.LEFT)
					.setColor(stack.getColor().brighter()));
			components.add(new Label(new Point2D.Double(area.x + area.width - 10, area.y + 5 + 12 + (25 + 5) * row),
				"" + stack.getAmount(),
				new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.RIGHT));
		}

	}

}
