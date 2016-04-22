package mirrg.bullet.nickel.item;

import java.awt.Color;

public enum Category
{
	fire("火", Color.decode("#E02900")),
	aqua("水", Color.decode("#006EFF")),
	earth("土", Color.decode("#D39E00")),
	air("風", Color.decode("#1FAA00")),

	;

	private final String nameLocalized;
	private final Color color;

	private Category(String nameLocalized, Color color)
	{
		this.nameLocalized = nameLocalized;
		this.color = color;
	}

	public String getNameOre()
	{
		return name();
	}

	public String getNameLocalized()
	{
		return nameLocalized;
	}

	public Color getColor()
	{
		return color;
	}

}
