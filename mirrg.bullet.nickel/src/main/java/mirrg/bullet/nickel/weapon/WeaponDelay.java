package mirrg.bullet.nickel.weapon;

import java.awt.Color;

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

	@Override
	public void move(ILiving living, PhaseBattle phase, boolean isFireable, boolean isPlayer)
	{
		if (delay <= 0) {
			weapon.move(living, phase, isFireable, isPlayer);
		} else {
			weapon.move(living, phase, false, isPlayer);
			delay--;
		}
	}

	@Override
	public String getName()
	{
		return weapon.getName();
	}

	@Override
	public int getDelay()
	{
		return weapon.getDelay() + delay;
	}

	@Override
	public double getDamagePerSecond(boolean isScatter)
	{
		return weapon.getDamagePerSecond(isScatter);
	}

	@Override
	public Color getColor()
	{
		return weapon.getColor();
	}

}
