package mirrg.bullet.nickel.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.core.GameNickel;

public class Button implements IComponent
{

	private GameNickel game;
	private Rectangle2D.Double rectangle;

	public Button(GameNickel game, Rectangle2D.Double shape)
	{
		this.game = game;
		this.rectangle = shape;
	}

	private Runnable onMouseUp;

	public Button setOnMouseUp(Runnable onMouseUp)
	{
		this.onMouseUp = onMouseUp;
		return this;
	}

	private Runnable onMouseDown;

	public Button setOnMouseDown(Runnable onMouseDown)
	{
		this.onMouseDown = onMouseDown;
		return this;
	}

	private Runnable onMouseIn;

	public Button setOnMouseIn(Runnable onMouseIn)
	{
		this.onMouseIn = onMouseIn;
		return this;
	}

	private Runnable onMouseOut;

	public Button setOnMouseOut(Runnable onMouseOut)
	{
		this.onMouseOut = onMouseOut;
		return this;
	}

	private Color color = new Color(128, 0, 0);

	public Button setColor(Color color)
	{
		this.color = color;
		return this;
	}

	private boolean isEnable = true;

	public Button setEnable(Boolean isEnable)
	{
		this.isEnable = isEnable;
		return this;
	}

	private boolean isEditable = true;

	public Button setEditable(boolean isEditable)
	{
		this.isEditable = isEditable;
		return this;
	}

	private boolean isDown = false;
	private boolean isHover = false;

	@Override
	public void move()
	{
		if (isEnable) {
			boolean _isHover = rectangle.contains(
				game.panel.responceApplyStandard.moduleInputStatus.getMouseX(),
				game.panel.responceApplyStandard.moduleInputStatus.getMouseY());

			if (isHover) {
				if (!_isHover) {

					if (onMouseOut != null) onMouseOut.run();

					isHover = false;
				}
			} else {
				if (_isHover) {

					if (onMouseIn != null) onMouseIn.run();

					isHover = true;
				}
			}
		} else {
			isHover = false;
		}

		if (isEditable) {
			int state = game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON1);

			if (isDown) {
				if (state == -1) {
					if (isHover) {

						if (onMouseUp != null) onMouseUp.run();

					}
					isDown = false;
				}
			} else {
				if (state == 1) {
					if (isHover) {

						if (onMouseDown != null) onMouseDown.run();

						isDown = true;
					}
				}
			}
		} else {
			isDown = false;
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
