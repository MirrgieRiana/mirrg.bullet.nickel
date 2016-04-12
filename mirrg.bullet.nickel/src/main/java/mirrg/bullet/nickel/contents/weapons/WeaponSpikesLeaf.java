package mirrg.bullet.nickel.contents.weapons;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.bullets.BulletStraight;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class WeaponSpikesLeaf extends WeaponAbstract
{

	public WeaponSpikesLeaf(int grade)
	{
		super(grade, Items.leaf.color);
	}

	@Override
	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		for (int i = 0; i < waysEnemy[grade]; i++) {
			BulletStraight bullet = new BulletStraight(living.getX(), living.getY(), 0, 0, 0.008, colorEnemy, 25);
			double theta = 90 * Math.PI / 180
				+ 5 * (i - (waysEnemy[grade] - 1) * 0.5) * Math.PI / 180;
			double speed = speedEnemy[grade] * (1 + 0.05 * (Math.random() * 2 - 1));
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
		60, 50, 40, 30,
	};

	private static final int[] waysEnemy = {
		4, 5, 6, 8,
	};

	private static final double[] speedEnemy = {
		0.003, 0.0035, 0.004, 0.0045,
	};

	@Override
	public void firePlayer(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		double theta = 1.5 * Math.PI;
		double speed = 0.01;

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
		if (grade >= 1) {
			phase.addBulletPlayer(new BulletStraight(
				living.getX() - 0.01,
				living.getY(),
				speed * Math.cos(theta - 15 * Math.PI / 180),
				speed * Math.sin(theta - 15 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() + 0.01,
				living.getY(),
				speed * Math.cos(theta + 15 * Math.PI / 180),
				speed * Math.sin(theta + 15 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() - 0.01,
				living.getY(),
				speed * Math.cos(theta - 20 * Math.PI / 180),
				speed * Math.sin(theta - 20 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() + 0.01,
				living.getY(),
				speed * Math.cos(theta + 20 * Math.PI / 180),
				speed * Math.sin(theta + 20 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
		}
		if (grade >= 2) {
			phase.addBulletPlayer(new BulletStraight(
				living.getX() - 0.01,
				living.getY(),
				speed * Math.cos(theta - 25 * Math.PI / 180),
				speed * Math.sin(theta - 25 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() + 0.01,
				living.getY(),
				speed * Math.cos(theta + 25 * Math.PI / 180),
				speed * Math.sin(theta + 25 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() - 0.01,
				living.getY(),
				speed * Math.cos(theta - 30 * Math.PI / 180),
				speed * Math.sin(theta - 30 * Math.PI / 180),
				0.01,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() + 0.01,
				living.getY(),
				speed * Math.cos(theta + 30 * Math.PI / 180),
				speed * Math.sin(theta + 30 * Math.PI / 180),
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
		20, 16, 13, 10,
	};

	private static final int[] damagePlayer = {
		15, 20, 30, 50,
	};

	@Override
	public String getName()
	{
		return "リーフ" + label[grade] + "スパイク";
	}

	private static final String[] label = {
		"L", "", "H", "V"
	};

	@Override
	public double getDPSConcentrate()
	{
		return 30.0 * damagePlayer[grade] * waysPlayerConcentrate[grade] / spanPlayer[grade];
	}

	@Override
	public double getDPSScatter()
	{
		return 30.0 * damagePlayer[grade] * waysPlayerAll[grade] / spanPlayer[grade];
	}

	private static final int[] waysPlayerConcentrate = {
		4, 4, 4, 4,
	};

	private static final int[] waysPlayerAll = {
		4, 8, 12, 12,
	};

}
