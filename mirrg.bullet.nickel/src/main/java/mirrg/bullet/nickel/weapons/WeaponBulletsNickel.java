package mirrg.bullet.nickel.weapons;

import java.awt.Color;

import mirrg.bullet.nickel.InstanceWeapon;
import mirrg.bullet.nickel.entities.bullets.BulletStraight;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.weapons.util.WeaponBase;

public class WeaponBulletsNickel extends WeaponBase
{

	public WeaponBulletsNickel(int grade)
	{
		super(grade, new Color(128, 128, 128));
	}

	@Override
	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		BulletStraight bullet = new BulletStraight(living.getX(), living.getY(), 0, 0, 0.008, colorEnemy, 100);
		double theta = Math.atan2(phase.player.getY() - bullet.y, phase.player.getX() - bullet.x);
		double speed = speedEnemy[grade];
		bullet.xx = speed * Math.cos(theta);
		bullet.yy = speed * Math.sin(theta);

		phase.addBulletEnemy(bullet);
	}

	@Override
	public int getSpanEnemy()
	{
		return spanEnemy[grade];
	}

	private static final int[] spanEnemy = {
		50, 30, 30, 20,
	};

	private static final double[] speedEnemy = {
		0.01, 0.01, 0.015, 0.015,
	};

	@Override
	public void firePlayer(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		double theta = 1.5 * Math.PI;
		double speed = 0.04;

		phase.addBulletPlayer(new BulletStraight(
			living.getX() - 0.01,
			living.getY(),
			speed * Math.cos(theta),
			speed * Math.sin(theta),
			0.01,
			colorPlayer,
			damagePlayer[grade]));
		phase.addBulletPlayer(new BulletStraight(
			living.getX() + 0.01,
			living.getY(),
			speed * Math.cos(theta),
			speed * Math.sin(theta),
			0.01,
			colorPlayer,
			damagePlayer[grade]));
		if (grade >= 1) {
			phase.addBulletPlayer(new BulletStraight(
				living.getX() - 0.01,
				living.getY(),
				speed * Math.cos(theta - 5 * Math.PI / 180),
				speed * Math.sin(theta - 5 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() + 0.01,
				living.getY(),
				speed * Math.cos(theta + 5 * Math.PI / 180),
				speed * Math.sin(theta + 5 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
		}
		if (grade >= 3) {
			phase.addBulletPlayer(new BulletStraight(
				living.getX() - 0.01,
				living.getY(),
				speed * Math.cos(theta - 10 * Math.PI / 180),
				speed * Math.sin(theta - 10 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() + 0.01,
				living.getY(),
				speed * Math.cos(theta + 10 * Math.PI / 180),
				speed * Math.sin(theta + 10 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
		}
	}

	@Override
	public int getSpanPlayer()
	{
		return spanPlayer[grade];
	}

	private static final int[] spanPlayer = {
		30, 24, 18, 12,
	};

	private static final int[] damagePlayer = {
		50, 100, 150, 200,
	};

	@Override
	public String getName()
	{
		return "ニッケル" + label[grade] + "バレット";
	}

	private static final String[] label = {
		"L", "", "H", "V"
	};

}
