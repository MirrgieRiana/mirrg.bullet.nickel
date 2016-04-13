package mirrg.bullet.nickel.weapon.instance;

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

}
