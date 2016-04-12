package mirrg.bullet.nickel.stage;

import mirrg.bullet.nickel.item.IItem;

public interface ISettingStage
{

	public IStage createStage();

	public String getName();

	public String getLabel();

	public IItem getItem();

	public int getR();

	public int getC();

}
