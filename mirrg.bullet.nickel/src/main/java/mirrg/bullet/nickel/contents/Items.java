package mirrg.bullet.nickel.contents;

import static mirrg.bullet.nickel.item.Category.*;

import java.awt.Color;

import mirrg.bullet.nickel.item.Category;
import mirrg.bullet.nickel.item.IItem;

public enum Items implements IItem
{
	stone("ストーン", Color.decode("#706B7A"), earth),
	brick("ブリック", Color.decode("#DD5B3E"), earth),
	glass("ガラス", Color.decode("#D8D8D8"), aqua),

	calcite("カルサイト", Color.decode("#DDDDCC"), aqua),

	water("ウォーター", Color.decode("#4C71E0"), aqua),
	lava("ラヴァ", Color.decode("#BF3609"), fire),
	oil("オイル", Color.decode("#260905"), air),

	dirt("ダート", Color.decode("#CE8F6D"), earth),
	clay("クレイ", Color.decode("#CCC0AB"), earth),
	sand("サンド", Color.decode("#EFCF94"), earth),

	iron("アイアン", Color.decode("#9E9999"), fire),
	bronze("ブロンズ", Color.decode("#EFA27F"), fire),
	nickel("ニッケル", Color.decode("#A2A5C4"), fire),
	steal("スチール", Color.decode("#686565"), fire),

	tin("ティン", Color.decode("#C7C6CE"), fire),

	copper("カッパー", Color.decode("#FF6A2B"), fire),
	silver("シルバー", Color.decode("#DEE7E8"), fire),
	gold("ゴールド", Color.decode("#E09C1D"), fire),

	emerald("エメラルド", Color.decode("#00D659"), aqua),
	ruby("ルビー", Color.decode("#c6002e"), aqua),

	grass("グラス", Color.decode("#41BA05"), air),
	wood("ウッド", Color.decode("#D8814B"), air),
	leaf("リーフ", Color.decode("#1A7518"), air),

	// エッセンシア
	logic("ロジック", Color.decode("#D10000"), aqua),
	mechanic("メカニック", Color.decode("#D18000"), aqua),
	chaos("カオス", Color.decode("#454721"), aqua),
	organic("オーガニック", Color.decode("#28DD00"), aqua),
	magic("マジック", Color.decode("#B200D6"), aqua),
	order("オーダー", Color.decode("#99FBFF"), aqua),

	// クラフト
	money("マネー", Color.decode("#DBC629"), fire),
	furnace("ファーネス", Color.decode("#682A1E"), fire),
	fuel("フューエル", Color.decode("#38160B"), fire),

	;

	private final String name;
	private final Color color;
	private final Category category;

	private Items(String name, Color color, Category category)
	{
		this.name = name;
		this.color = color;
		this.category = category;
	}

	@Override
	public String getNameLocalized()
	{
		return name;
	}

	@Override
	public String getNameOre()
	{
		return name();
	}

	@Override
	public Color getColor()
	{
		return color;
	}

	@Override
	public Category getCategory()
	{
		return category;
	}

}
