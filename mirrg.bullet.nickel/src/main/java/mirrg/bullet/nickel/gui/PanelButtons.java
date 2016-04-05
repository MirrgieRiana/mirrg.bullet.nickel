package mirrg.bullet.nickel.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import mirrg.bullet.nickel.GameNickel;

public class PanelButtons implements IComponent
{

	public ArrayList<IComponent> components = new ArrayList<>();

	public int rows;
	public int columns;
	public Point2D.Double center;
	public int buttonWidth = 40;
	public int buttonHeight = 40;
	public int margin = 5;

	public PanelButtons(int rows, int columns, Point2D.Double center)
	{
		this.rows = rows;
		this.columns = columns;
		this.center = center;

		components.add(new LabelShape(new Rectangle2D.Double(
			center.x - getPanelWidth() / 2,
			center.y - getPanelHeight() / 2,
			getPanelWidth(),
			getPanelHeight()), new Color(64, 0, 0)));
	}

	public int getPanelWidth()
	{
		return margin + (buttonWidth + margin) * columns;
	}

	public int getPanelHeight()
	{
		return margin + (buttonHeight + margin) * rows;
	}

	public void putButton(GameNickel game, int row, int column, Runnable action)
	{
		components.add(new Button(game, new Rectangle2D.Double(
			center.x - getPanelWidth() / 2 + margin + column * (buttonWidth + margin),
			center.y - getPanelHeight() / 2 + margin + row * (buttonHeight + margin),
			buttonWidth,
			buttonHeight), action));
	}

	@Override
	public void move()
	{
		for (IComponent component : components) {
			component.move();
		}
	}

	@Override
	public void paint(Graphics2D graphics)
	{
		for (IComponent component : components) {
			component.paint(graphics);
		}
	}

}
