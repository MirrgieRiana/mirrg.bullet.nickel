package mirrg.bullet.nickel.weapons;

import java.awt.Color;

import mirrg.bullet.nickel.InstanceWeapon;
import mirrg.bullet.nickel.entities.bullets.BulletStraight;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.weapons.util.WeaponBase;

public class WeaponBulletsCalcite extends WeaponBase
{

	public WeaponBulletsCalcite(int grade)
	{
		super(grade, new Color(192, 192, 192));
	}

	@Override
	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		for (int i = 0; i <= waysEnemy[grade]; i++) {
			BulletStraight bullet = new BulletStraight(living.getX(), living.getY(), 0, 0, 0.003, colorEnemy, 50);
			double theta = Math.atan2(phase.player.getY() - bullet.y, phase.player.getX() - bullet.x)
				+ 1 * (i - (waysEnemy[grade] - 1) * 0.5) * Math.PI / 180;
			double speed = speedEnemy[grade];
			bullet.xx = speed * Math.cos(theta);
			bullet.yy = speed * Math.sin(theta);

			phase.addBulletEnemy(bullet);
		}
	}

	@Override
	public int getSpanEnemy()
	{
		return spanEnemy[grade];
	}

	private static final int[] spanEnemy = {
		30, 25, 18, 10,
	};

	private static final int[] waysEnemy = {
		3, 4, 5, 7,
	};

	private static final double[] speedEnemy = {
		0.004, 0.005, 0.006, 0.007,
	};

	@Override
	public void firePlayer(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		for (int i = 0; i < waysPlayer[grade]; i++) {
			double theta = 1.5 * Math.PI + anglePlayer[grade] * (i - (waysPlayer[grade] - 1) * 0.5) * Math.PI / 180;
			double speed = 0.04;
			phase.addBulletPlayer(new BulletStraight(
				living.getX(),
				living.getY(),
				speed * Math.cos(theta),
				speed * Math.sin(theta),
				0.005,
				colorPlayer,
				25));
		}
	}

	@Override
	public int getSpanPlayer()
	{
		return spanPlayer[grade];
	}

	private static final int[] spanPlayer = {
		15, 10, 7, 5,
	};

	private static final int[] waysPlayer = {
		5, 8, 12, 15,
	};

	private static final int[] anglePlayer = {
		15, 12, 10, 8,
	};

	@Override
	public String getName()
	{
		return "カルサイト" + label[grade] + "バレット";
	}

	private static final String[] label = {
		"L", "", "H", "V"
	};

}
