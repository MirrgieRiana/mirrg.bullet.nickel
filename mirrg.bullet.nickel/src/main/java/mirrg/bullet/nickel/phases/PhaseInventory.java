package mirrg.bullet.nickel.phases;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.GameNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.gui.LabelShape;
import mirrg.bullet.nickel.item.StackWeapon;

public class PhaseInventory extends PhaseGUIBase
{

	public PhaseInventory(GameNickel game)
	{
		super(game);

		{
			int width = 400;
			int height = 400;
			Point2D.Double center = new Point2D.Double(game.sizeGame.width / 2, game.sizeGame.height / 2);
			Rectangle2D.Double area = new Rectangle2D.Double(center.x - width / 2, center.y - height / 2, width, height);
			components.add(new LabelShape(area, new Color(64, 0, 0)));

			for (int i = 0; i < game.inventory.stacks.size(); i++) {
				if (game.inventory.stacks.get(i) instanceof StackWeapon) {
					int i2 = i;

					{
						Rectangle2D.Double area2 = new Rectangle2D.Double(area.x + 5, area.y + 5 + (25 + 5) * i, 25, 25);
						Label e = new Label(new Point2D.Double(area2.getCenterX(), area2.getCenterY()),
							"", new Font(Font.SANS_SERIF, Font.PLAIN, 24));
						hookReflesh(() -> {
							e.text = game.weaponMain.equals(game.inventory.stacks.get(i2)) ? "★" : "←";
						});
						components.add(new Button(game, area2, () -> {
							game.weaponMain = (StackWeapon) game.inventory.stacks.get(i2);
							fireReflesh();
						}));
						components.add(e);
					}

					{
						Rectangle2D.Double area2 = new Rectangle2D.Double(area.x + 35, area.y + 5 + (25 + 5) * i, 25, 25);
						Label e = new Label(new Point2D.Double(area2.getCenterX(), area2.getCenterY()),
							"", new Font(Font.SANS_SERIF, Font.PLAIN, 24));
						hookReflesh(() -> {
							e.text = game.weaponSub.equals(game.inventory.stacks.get(i2)) ? "★" : "→";
						});
						components.add(new Button(game, area2, () -> {
							game.weaponSub = (StackWeapon) game.inventory.stacks.get(i2);
							fireReflesh();
						}));
						components.add(e);
					}

				}

				{
					components.add(new Label(new Point2D.Double(area.x + 65, area.y + 5 + 12 + (25 + 5) * i),
						game.inventory.stacks.get(i).getName(),
						new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.LEFT));
				}

				{
					components.add(new Label(new Point2D.Double(area.x + width - 5, area.y + 5 + 12 + (25 + 5) * i),
						"" + game.inventory.stacks.get(i).getAmount(),
						new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.RIGHT));
				}
			}
		}

		fireReflesh();
	}

}
