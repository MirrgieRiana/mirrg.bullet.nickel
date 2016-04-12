package mirrg.bullet.nickel.contents.weapons;

import java.awt.geom.Point2D;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class WeaponLaserRuby extends WeaponAbstract
{

	public WeaponLaserRuby(int grade)
	{
		super(grade, Items.ruby.color);
	}

	// 自機狙い槍
	@Override
	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		double theta1 = Math.random() * 2 * Math.PI;
		double length = Math.random() * living.getRadius();
		Point2D.Double point = new Point2D.Double(
			living.getX() + length * Math.cos(theta1),
			living.getY() + length * Math.sin(theta1));

		double theta2 = Math.atan2(
			phase.player.getY() - point.y,
			phase.player.getX() - point.x) + (Math.random() * 2 - 1) * noizEnemy[grade] * Math.PI / 180;
		double speed = speedEnemy[grade];

		for (int i = 0; i < bulletsEnemy[grade]; i++) {
			phase.addBulletEnemy(new BulletDelayed(
				point.x,
				point.y,
				speed * Math.cos(theta2),
				speed * Math.sin(theta2),
				0.003,
				colorEnemy,
				50,
				(double) i / 2 / 50 / speed));
		}
	}

	@Override
	public int getSpanEnemy()
	{
		return spanEnemy[grade];
	}

	private static final int[] spanEnemy = {
		50, 35, 26, 20,
	};

	private static final int[] bulletsEnemy = {
		20, 25, 30, 40,
	};

	private static final double[] noizEnemy = {
		2, 2.5, 3, 4,
	};

	private static final double[] speedEnemy = {
		0.02, 0.025, 0.03, 0.04,
	};

	@Override
	public void firePlayer(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		double theta = 270 * Math.PI / 180;
		double speed = speedPlayer[grade];

		for (int i = 0; i < bulletsPlayer[grade]; i++) {
			phase.addBulletPlayer(new BulletDelayed(
				living.getX() - 0.005,
				living.getY(),
				speed * Math.cos(theta - 1 * Math.PI / 180),
				speed * Math.sin(theta - 1 * Math.PI / 180),
				0.003,
				colorPlayer,
				damagePlayer[grade],
				(double) i / 2 / 50 / speed));
			phase.addBulletPlayer(new BulletDelayed(
				living.getX() + 0.005,
				living.getY(),
				speed * Math.cos(theta + 1 * Math.PI / 180),
				speed * Math.sin(theta + 1 * Math.PI / 180),
				0.003,
				colorPlayer,
				damagePlayer[grade],
				(double) i / 2 / 50 / speed));
			if (grade >= 1) {
				phase.addBulletPlayer(new BulletDelayed(
					living.getX() - 0.015,
					living.getY(),
					speed * Math.cos(theta + 2 * Math.PI / 180),
					speed * Math.sin(theta + 2 * Math.PI / 180),
					0.003,
					colorPlayer,
					damagePlayer[grade],
					(double) i / 2 / 50 / speed));
				phase.addBulletPlayer(new BulletDelayed(
					living.getX() + 0.015,
					living.getY(),
					speed * Math.cos(theta - 2 * Math.PI / 180),
					speed * Math.sin(theta - 2 * Math.PI / 180),
					0.003,
					colorPlayer,
					damagePlayer[grade],
					(double) i / 2 / 50 / speed));
			}
			if (grade >= 2) {
				phase.addBulletPlayer(new BulletDelayed(
					living.getX() - 0.02,
					living.getY() + 0.01,
					speed * Math.cos(theta - 3 * Math.PI / 180),
					speed * Math.sin(theta - 3 * Math.PI / 180),
					0.003,
					colorPlayer,
					damagePlayer[grade],
					(double) i / 2 / 50 / speed));
				phase.addBulletPlayer(new BulletDelayed(
					living.getX() + 0.02,
					living.getY() + 0.01,
					speed * Math.cos(theta + 3 * Math.PI / 180),
					speed * Math.sin(theta + 3 * Math.PI / 180),
					0.003,
					colorPlayer,
					damagePlayer[grade],
					(double) i / 2 / 50 / speed));
			}
			if (grade >= 3) {
				phase.addBulletPlayer(new BulletDelayed(
					living.getX() - 0.025,
					living.getY() + 0.02,
					speed * Math.cos(theta + 4 * Math.PI / 180),
					speed * Math.sin(theta + 4 * Math.PI / 180),
					0.003,
					colorPlayer,
					damagePlayer[grade],
					(double) i / 2 / 50 / speed));
				phase.addBulletPlayer(new BulletDelayed(
					living.getX() + 0.025,
					living.getY() + 0.02,
					speed * Math.cos(theta - 4 * Math.PI / 180),
					speed * Math.sin(theta - 4 * Math.PI / 180),
					0.003,
					colorPlayer,
					damagePlayer[grade],
					(double) i / 2 / 50 / speed));
			}
		}
	}

	@Override
	public int getSpanPlayer()
	{
		return spanPlayer[grade];
	}

	private static final int[] spanPlayer = {
		60, 40, 30, 20,
	};

	private static final int[] damagePlayer = {
		10, 12, 14, 16,
	};

	private static final int[] bulletsPlayer = {
		20, 30, 40, 60,
	};

	private static final double[] speedPlayer = {
		0.03, 0.04, 0.06, 0.08,
	};

	@Override
	public String getName()
	{
		return "ルビー" + label[grade] + "レーザー";
	}

	private static final String[] label = {
		"L", "", "H", "V"
	};

	@Override
	public double getDPSConcentrate()
	{
		return 30.0 * damagePlayer[grade] * bulletsPlayer[grade] * (grade + 1) * 2 / spanPlayer[grade];
	}

	@Override
	public double getDPSScatter()
	{
		return getDPSConcentrate();
	}

}
