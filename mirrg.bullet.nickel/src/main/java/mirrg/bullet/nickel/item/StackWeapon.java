package mirrg.bullet.nickel.item;

import mirrg.bullet.nickel.IWeapon;

public class StackWeapon implements IStack
{

	public final IWeapon weapon;

	public StackWeapon(IWeapon weapon)
	{
		this.weapon = weapon;
	}

	@Override
	public String getNameInButtle()
	{
		return weapon.getName();
	}

	@Override
	public String getName()
	{
		return weapon.getName();
	}

	@Override
	public int getAmount()
	{
		return 1;
	}

}
