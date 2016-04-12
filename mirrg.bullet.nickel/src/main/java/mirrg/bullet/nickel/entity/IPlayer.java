package mirrg.bullet.nickel.entity;

import mirrg.bullet.nickel.weapon.IWeapon;

public interface IPlayer extends ILiving
{

	public IWeapon getWeaponMain();

	public IWeapon getWeaponSub();

}
