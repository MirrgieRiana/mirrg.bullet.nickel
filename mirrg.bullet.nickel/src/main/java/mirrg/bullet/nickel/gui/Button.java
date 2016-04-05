package mirrg.bullet.nickel.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.GameNickel;

public class Button implements IComponent
{

	public GameNickel game;
	public Rectangle2D.Double rectangle;
	public Runnable onClick;
	public boolean isDown = false;
	public boolean isHover = false;

	public Button(GameNickel game, Rectangle2D.Double shape, Runnable onClick)
	{
		this.game = game;
		this.rectangle = shape;
		this.onClick = onClick;
	}

	@Override
	public void move()
	{
		isHover = rectangle.contains(
			game.panel.responceApplyStandard.moduleInputStatus.getMouseX(),
			game.panel.responceApplyStandard.moduleInputStatus.getMouseY());

		if (isDown) {
			if (game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON1) == -1) {
				if (isHover) {
					onClick.run();
				}
				isDown = false;
			}
		} else {
			if (game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON1) == 1) {
				if (isHover) {
					isDown = true;
				}
			}
		}
	}

	@Override
	public void paint(Graphics2D graphics)
	{
		if (isHover) {
			graphics.setColor(new Color(192, 0, 0));
		} else {
			graphics.setColor(new Color(128, 0, 0));
		}
		graphics.fill(rectangle);

		if (isDown) {
			graphics.setColor(Color.darkGray);
		} else {
			graphics.setColor(Color.lightGray);
		}
		graphics.draw(new Line2D.Double(rectangle.x, rectangle.y, rectangle.getMaxX(), rectangle.y));
		graphics.draw(new Line2D.Double(rectangle.x, rectangle.y, rectangle.x, rectangle.getMaxY()));

		if (isDown) {
			graphics.setColor(Color.lightGray);
		} else {
			graphics.setColor(Color.darkGray);
		}
		graphics.draw(new Line2D.Double(rectangle.x, rectangle.getMaxY(), rectangle.getMaxX(), rectangle.getMaxY()));
		graphics.draw(new Line2D.Double(rectangle.getMaxX(), rectangle.y, rectangle.getMaxX(), rectangle.getMaxY()));
	}

}
