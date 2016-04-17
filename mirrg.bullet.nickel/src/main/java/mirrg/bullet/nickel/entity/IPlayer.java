package mirrg.bullet.nickel.entity;

import java.util.Optional;

import mirrg.bullet.nickel.weapon.instance.IWeapon;

public interface IPlayer extends ILiving
{

	public Optional<IWeapon> getWeaponMain();

	public Optional<IWeapon> getWeaponSub();

	public int getHP();

	public int getHPMax();

}
