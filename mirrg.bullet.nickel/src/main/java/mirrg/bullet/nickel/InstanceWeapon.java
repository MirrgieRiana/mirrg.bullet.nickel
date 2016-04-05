package mirrg.bullet.nickel;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class InstanceWeapon
{

	public IWeapon weapon;
	public boolean isPlayer;
	public int wait = 0;
	public int age = 0;
	public int delay = 0;

	public InstanceWeapon(IWeapon weapon, boolean isPlayer)
	{
		this.weapon = weapon;
		this.isPlayer = isPlayer;
		delay = weapon.getDelay();
	}

	/**
	 * 発射するときに{@link #move(ILiving, PhaseBattle)}の前に呼ばれる
	 */
	public void shoot(ILiving living, PhaseBattle phase)
	{
		if (delay <= 0) {
			if (isPlayer) {
				if (wait <= 0) {
					wait = weapon.getSpanPlayer();
					weapon.firePlayer(this, living, phase);
				}
			} else {
				if (wait <= 0) {
					wait = weapon.getSpanEnemy();
					weapon.fireEnemy(this, living, phase);
				}
			}
		}
	}

	/**
	 * 常時呼ばれている
	 */
	public void move(ILiving living, PhaseBattle phase)
	{
		if (delay <= 0) {
			if (wait > 0) {
				wait--;
			}
			age++;
		} else {
			delay--;
		}
	}

}
