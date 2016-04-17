package mirrg.bullet.nickel.contents;

import java.util.function.Supplier;

import mirrg.bullet.nickel.contents.stages.StageLandOpening;
import mirrg.bullet.nickel.contents.stages.StagePlainDirt;
import mirrg.bullet.nickel.contents.stages.StagePlainGrass;
import mirrg.bullet.nickel.contents.stages.StagePlainStone;
import mirrg.bullet.nickel.contents.stages.StageQuarryCopper;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.stage.ICardStage;
import mirrg.bullet.nickel.stage.IStage;

public enum CardStages implements ICardStage
{
	landOpening(StageLandOpening::new, "LandOpening", "始まりの土地", Items.dirt, 10, 0),
	plainStone(StagePlainStone::new, "PlainStone", "石の平原", Items.stone, 9, 0),
	plainDirt(StagePlainDirt::new, "PlainDirt", "土の平原", Items.dirt, 9, 1),
	plainGrass(StagePlainGrass::new, "PlainGrass", "草の平原", Items.grass, 9, 2),
	quarryCopper(StageQuarryCopper::new, "QuarryCopper", "銅の鉱山", Items.copper, 8, 1);

	public Supplier<IStage> creator;
	public String name;
	public String label;
	public IItem item;
	public int r;
	public int c;

	private CardStages(Supplier<IStage> creator, String name, String label, IItem item, int r, int c)
	{
		this.creator = creator;
		this.name = name;
		this.label = label;
		this.item = item;
		this.r = r;
		this.c = c;
	}

	@Override
	public IStage createStage()
	{
		return creator.get();
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getLabel()
	{
		return label;
	}

	@Override
	public IItem getItem()
	{
		return item;
	}

	@Override
	public int getR()
	{
		return r;
	}

	@Override
	public int getC()
	{
		return c;
	}

	public static ICardStage get(int r, int c)
	{
		for (ICardStage cardStage : values()) {
			if (cardStage.getR() == r && cardStage.getC() == c) return cardStage;
		}
		return null;
	}

}
