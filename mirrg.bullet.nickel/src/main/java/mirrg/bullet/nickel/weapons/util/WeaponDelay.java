package mirrg.bullet.nickel.weapons.util;

import mirrg.bullet.nickel.IWeapon;
import mirrg.bullet.nickel.InstanceWeapon;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class WeaponDelay implements IWeapon
{

	private int delay;
	private IWeapon weapon;

	public WeaponDelay(int delay, IWeapon weapon)
	{
		this.delay = delay;
		this.weapon = weapon;
	}

	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		weapon.fireEnemy(instanceWeapon, living, phase);
	}

	public int getSpanEnemy()
	{
		return weapon.getSpanEnemy();
	}

	public void firePlayer(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		weapon.firePlayer(instanceWeapon, living, phase);
	}

	public int getSpanPlayer()
	{
		return weapon.getSpanPlayer();
	}

	public String getName()
	{
		return weapon.getName();
	}

	public int getDelay()
	{
		return weapon.getDelay() + delay;
	}

}
