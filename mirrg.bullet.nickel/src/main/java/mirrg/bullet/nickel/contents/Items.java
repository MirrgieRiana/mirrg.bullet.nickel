package mirrg.bullet.nickel.contents;

import java.awt.Color;

import mirrg.bullet.nickel.item.IItem;

public enum Items implements IItem
{
	stone("ストーン", Color.decode("#706B7A")),
	brick("ブリック", Color.decode("#DD5B3E")),
	glass("ガラス", Color.decode("#D8D8D8")),

	calcite("カルサイト", Color.decode("#DDDDCC")),

	dirt("ダート", Color.decode("#CE8F6D")),
	clay("クレイ", Color.decode("#CCC0AB")),
	sand("サンド", Color.decode("#EFCF94")),

	iron("アイアン", Color.decode("#9E9999")),
	bronze("ブロンズ", Color.decode("#EFA27F")),
	nickel("ニッケル", Color.decode("#A2A5C4")),
	steal("スチール", Color.decode("#686565")),

	tin("ティン", Color.decode("#C7C6CE")),

	copper("カッパー", Color.decode("#FF6A2B")),
	silver("シルバー", Color.decode("#DEE7E8")),
	gold("ゴールド", Color.decode("#E09C1D")),

	emerald("エメラルド", Color.decode("#00D659")),
	ruby("ルビー", Color.decode("#c6002e")),

	grass("グラス", Color.decode("#41BA05")),
	wood("ウッド", Color.decode("#D8814B")),
	leaf("リーフ", Color.decode("#1A7518")),

	furnace("炉", Color.decode("#682A1E")),
	fuel("フューエル", Color.decode("#38160B"));

	private final String name;
	private final Color color;

	private Items(String name, Color color)
	{
		this.name = name;
		this.color = color;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public Color getColor()
	{
		return color;
	}

}
