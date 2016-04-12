package mirrg.bullet.nickel.phases;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Container;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.item.IStack;
import mirrg.bullet.nickel.item.Recipe;

public class PhaseCraft extends PhaseListAbstract
{

	private Recipe[] recipes;
	private Container containerRecipe;

	public PhaseCraft(GameNickel game, Recipe[] recipes)
	{
		super(game);
		this.recipes = recipes;
	}

	@Override
	protected int getItemCount()
	{
		return recipes.length;
	}

	private Runnable refleshLabels = null;

	@Override
	protected synchronized void reform()
	{
		super.reform();

		containerRecipe = new Container();
		components.add(containerRecipe);

		hookReflesh(() -> {
			if (refleshLabels != null) refleshLabels.run();
		});
	}

	@Override
	protected void putRecord(GameNickel game, Rectangle2D.Double area, int row)
	{
		Recipe recipe = recipes[row + lineCount * page];

		Runnable onMouseIn = () -> {
			refleshLabels = () -> {
				containerRecipe.clear();

				for (int i = 0; i < recipe.in.stacks.size(); i++) {
					IStack stack = recipe.in.stacks.get(i);
					int a = stack.getAmount();
					IStack stack2 = game.inventory.search(stack.getOreName());
					int b = stack2 != null ? stack2.getAmount() : 0;

					Label label2 = new Label(new Point2D.Double(area.getCenterX() - 15, area.y - 15 - 30 * i),
						stack.getName() + " (" + b + " / " + a + ")",
						new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.RIGHT);
					if (b < a) label2.setColor(Color.yellow);
					containerRecipe.add(label2);
				}

				for (int i = 0; i < recipe.out.size(); i++) {
					IStack stack = recipe.out.get(i).get();
					int a = stack.getAmount();
					IStack stack2 = game.inventory.search(stack.getOreName());
					int b = stack2 != null ? stack2.getAmount() : 0;

					containerRecipe.add(new Label(new Point2D.Double(area.getCenterX() + 15, area.y - 15 - 30 * i),
						"(" + b + " → " + (a + b) + ") " + stack.getName(),
						new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.LEFT));
				}

				messages.clear();
				recipe.out.get(0).get().getMessages(messages);
			};
			fireReflesh();
		};

		Button button1 = new Button(game, new Rectangle2D.Double(area.x + 5, area.y + 5 + (25 + 5) * row, 60, 25))
			.setOnClick(() -> {

				if (game.inventory.isCraftable(recipe)) {
					game.inventory.craft(recipe);
				}

				fireReflesh();
			})
			.setOnMouseIn(onMouseIn);
		components.add(button1);
		Label label1 = new Label(new Point2D.Double(area.x + 35, area.y + 5 + 12 + (25 + 5) * row),
			"",
			new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		components.add(label1);

		components.add(new Button(game, new Rectangle2D.Double(area.x + 70, area.y + 5 + (25 + 5) * row, 325, 25))
			.setOnMouseIn(onMouseIn));

		Label label2 = new Label(new Point2D.Double(area.x + 75, area.y + 5 + 12 + (25 + 5) * row),
			recipe.out.get(0).get().getName(),
			new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.LEFT);
		components.add(label2);

		//

		hookReflesh(() -> {
			boolean isCraftable = game.inventory.isCraftable(recipe);

			button1.setEnable(isCraftable);
			if (isCraftable) {
				label1.text = "生産";
				label2.setColor(recipe.out.get(0).get().getColor().brighter());
			} else {
				label1.text = "×";
				label2.setColor(recipe.out.get(0).get().getColor().darker().darker());
			}
		});

	}

}
