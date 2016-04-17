package mirrg.bullet.nickel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Label implements IComponent
{

	public static final int TOP = 2;
	public static final int MIDDLE = 1;
	public static final int BOTTOM = 0;

	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;

	private Point2D.Double base;
	private int alignVertical;
	private int alignHorizontal;

	public Label(Point2D.Double base, String text, Font font)
	{
		this(base, text, font, MIDDLE, CENTER);
	}

	public Label(Point2D.Double base, String text, Font font, int alignVertical, int alignHorizontal)
	{
		this.base = base;
		this.text = text;
		this.font = font;
		this.alignVertical = alignVertical;
		this.alignHorizontal = alignHorizontal;
	}

	private Color color = Color.white;

	public Label setColor(Color color)
	{
		this.color = color;
		return this;
	}

	private String text;

	public Label setText(String text)
	{
		this.text = text;
		return this;
	}

	private Font font;

	public Label setFont(Font font)
	{
		this.font = font;
		return this;
	}

	@Override
	public void move()
	{

	}

	@Override
	public void paint(Graphics2D graphics)
	{
		graphics.setFont(font);
		graphics.setColor(color);
		graphics.drawString(
			text,
			(int) (base.x - graphics.getFontMetrics().stringWidth(text) * alignHorizontal / 2),
			(int) (base.y + font.getSize() * alignVertical / 2));
	}

}
