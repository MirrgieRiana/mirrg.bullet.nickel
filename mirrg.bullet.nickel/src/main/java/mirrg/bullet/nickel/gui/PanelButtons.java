package mirrg.bullet.nickel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.core.GameNickel;

public class PanelButtons extends Container
{

	private int rows;
	private int columns;
	private int buttonWidth;
	private int buttonHeight;
	private Point2D.Double center;
	private int margin = 5;

	public PanelButtons(int rows, int columns, int buttonWidth, int buttonHeight, Point2D.Double center)
	{
		this.rows = rows;
		this.columns = columns;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;
		this.center = center;

		area = new Rectangle2D.Double(
			center.x - getPanelWidth() / 2,
			center.y - getPanelHeight() / 2,
			getPanelWidth(),
			getPanelHeight());
		add(new LabelShape(getArea(), new Color(64, 0, 0)));
	}

	private Rectangle2D.Double area;

	public Rectangle2D.Double getArea()
	{
		return area;
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
