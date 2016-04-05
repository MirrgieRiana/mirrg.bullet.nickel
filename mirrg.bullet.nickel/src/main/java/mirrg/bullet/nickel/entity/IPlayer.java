package mirrg.bullet.nickel.entity;

import mirrg.bullet.nickel.IWeapon;

public interface IPlayer extends ILiving
{

	public IWeapon getWeaponMain();

	public IWeapon getWeaponSub();

}
