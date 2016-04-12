package mirrg.bullet.nickel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.core.GameNickel;

public class PanelButtons extends Container
{

	public int rows;
	public int columns;
	public int buttonWidth;
	public int buttonHeight;
	public Point2D.Double center;
	public int margin = 5;
	public Rectangle2D.Double rectangle;

	public PanelButtons(int rows, int columns, int buttonWidth, int buttonHeight, Point2D.Double center)
	{
		this.rows = rows;
		this.columns = columns;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;
		this.center = center;

		rectangle = new Rectangle2D.Double(
			center.x - getPanelWidth() / 2,
			center.y - getPanelHeight() / 2,
			getPanelWidth(),
			getPanelHeight());
		add(new LabelShape(rectangle, new Color(64, 0, 0)));
	}

	public int getPanelWidth()
	{
		return margin + (buttonWidth + margin) * columns;
	}

	public int getPanelHeight()
	{
		return margin + (buttonHeight + margin) * rows;
	}

	public Button putButton(GameNickel game, int row, int column)
	{
		Button button = new Button(game, new Rectangle2D.Double(
			center.x - getPanelWidth() / 2 + margin + column * (buttonWidth + margin),
			center.y - getPanelHeight() / 2 + margin + row * (buttonHeight + margin),
			buttonWidth,
			buttonHeight));
		add(button);
		return button;
	}

	public Label putLabel(GameNickel game, int row, int column, String text, Font font)
	{
		Label label = new Label(new Point2D.Double(
			center.x - getPanelWidth() / 2 + margin + column * (buttonWidth + margin) + buttonWidth / 2,
			center.y - getPanelHeight() / 2 + margin + row * (buttonHeight + margin) + buttonHeight / 2),
			text, font);
		add(label);
		return label;
	}

}
