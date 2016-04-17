package mirrg.bullet.nickel.phases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.core.SessionNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Counter;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.gui.LabelShape;

public abstract class PhaseListAbstract extends PhaseHomeBase
{

	protected int page = 0;
	protected int lineCount = 20;
	protected Rectangle2D.Double area;

	public PhaseListAbstract(GameNickel game, SessionNickel session)
	{
		super(game, session);
	}

	protected abstract int getItemCount();

	@Override
	protected synchronized void reform()
	{
		super.reform();

		int pageMax = Math.max((getItemCount() - 1) / lineCount, 0);

		{
			int width = 400;
			int height = 5 + (25 + 5) * lineCount;
			Point2D.Double center = new Point2D.Double(game.sizeGame.width / 2, game.sizeGame.height / 2);
			area = new Rectangle2D.Double(center.x - width / 2, center.y - height / 2, width, height);
			components.add(new LabelShape(area, new Color(64, 0, 0)));

			for (int row = 0; row < lineCount; row++) {
				if (getItemCount() > row + lineCount * page) {
					putRecord(game, area, row);
				}
			}

			components.add(new Button(game, new Rectangle2D.Double(area.x + 5, area.getMaxY() + 5, 25, 25))
				.setOnMouseUp(() -> {
					hookInvokeLater(() -> {
						if (page > 0) {
							page--;
							fireReform();
						}
					});
				}));
			components.add(new Label(new Point2D.Double(area.x + 5 + 12.5, area.getMaxY() + 5 + 12.5),
				"←", new Font(Font.SANS_SERIF, Font.PLAIN, 24)));

			components.add(new Button(game, new Rectangle2D.Double(area.x + 35, area.getMaxY() + 5, 25, 25))
				.setOnMouseUp(() -> {
					hookInvokeLater(() -> {
						if (page < pageMax) {
							page++;
							fireReform();
						}
					});
				}));
			components.add(new Label(new Point2D.Double(area.x + 35 + 12.5, area.getMaxY() + 5 + 12.5),
				"→", new Font(Font.SANS_SERIF, Font.PLAIN, 24)));

			components.add(new Label(new Point2D.Double(area.x + 65, area.getMaxY() + 5 + 12.5),
				(page + 1) + " / " + (pageMax + 1), new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.LEFT));
		}

	}

	protected abstract void putRecord(GameNickel game, Rectangle2D.Double area, int row);

	protected ArrayList<String> messages = new ArrayList<>();

	@Override
	public void paintScore(Graphics2D graphics, Dimension size, Counter counter)
	{
		graphics.setColor(Color.white);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));

		for (String message : messages) {
			graphics.drawString(message,
				0, counter.value + graphics.getFont().getSize());
			counter.add(graphics.getFont().getSize());
		}

	}

}
