package mirrg.bullet.nickel.item;

public class Item
{

	public enum EnumItem
	{
		scrapIron(new Item("IronScrap")),
		scrapCopper(new Item("CopperScrap")),
		scrapTin(new Item("TinScrap")),
		scrapGold(new Item("GoldScrap")),
		scrapSilver(new Item("SilverScrap"));

		public final Item item;

		private EnumItem(Item item)
		{
			this.item = item;
		}

	}

	public final String name;

	public Item(String name)
	{
		this.name = name;
	}

}
