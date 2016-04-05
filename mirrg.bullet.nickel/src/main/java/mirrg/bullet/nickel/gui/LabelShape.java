package mirrg.bullet.nickel.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class LabelShape implements IComponent
{

	public Shape shape;
	public Color color;

	public LabelShape(Shape shape, Color color)
	{
		this.shape = shape;
		this.color = color;
	}

	@Override
	public void move()
	{

	}

	@Override
	public void paint(Graphics2D graphics)
	{
		graphics.setColor(color);
		graphics.fill(shape);
	}

}
