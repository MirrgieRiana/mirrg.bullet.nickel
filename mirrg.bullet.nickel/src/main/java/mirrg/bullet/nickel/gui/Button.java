package mirrg.bullet.nickel.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.core.GameNickel;

public class Button implements IComponent
{

	public GameNickel game;
	public Rectangle2D.Double rectangle;
	public Color color = new Color(128, 0, 0);
	public Runnable onClick;
	public Runnable onMouseIn;
	public boolean isDown = false;
	public boolean isHover = false;
	public boolean isEnable = true;

	public Button(GameNickel game, Rectangle2D.Double shape)
	{
		this.game = game;
		this.rectangle = shape;
	}

	public Button setOnClick(Runnable onClick)
	{
		this.onClick = onClick;
		return this;
	}

	public Button setOnMouseIn(Runnable onMouseIn)
	{
		this.onMouseIn = onMouseIn;
		return this;
	}

	public Button setColor(Color color)
	{
		this.color = color;
		return this;
	}

	public Button setEnable(Boolean isEnable)
	{
		this.isEnable = isEnable;
		return this;
	}

	@Override
	public void move()
	{
		boolean isHover = rectangle.contains(
			game.panel.responceApplyStandard.moduleInputStatus.getMouseX(),
			game.panel.responceApplyStandard.moduleInputStatus.getMouseY()) && isEnable;

		if (!this.isHover && isHover) {
			if (onMouseIn != null) onMouseIn.run();
		}
		this.isHover = isHover;

		if (isDown) {
			if (game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON1) == -1) {
				if (isHover) {
					if (onClick != null) onClick.run();
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
		graphics.setColor(color);
		graphics.fill(rectangle);
		if (isHover) {
			graphics.setColor(new Color(255, 255, 255, 64));
			graphics.fill(rectangle);
		}

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
