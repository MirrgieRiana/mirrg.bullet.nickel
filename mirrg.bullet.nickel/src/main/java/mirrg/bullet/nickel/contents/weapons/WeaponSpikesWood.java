package mirrg.bullet.nickel.contents.weapons;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.bullets.BulletStraight;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class WeaponSpikesWood extends WeaponAbstract
{

	public WeaponSpikesWood(int grade)
	{
		super(grade, Items.wood.color);
	}

	@Override
	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		for (int i = 0; i < waysEnemy[grade]; i++) {
			BulletStraight bullet = new BulletStraight(living.getX(), living.getY(), 0, 0, 0.003, colorEnemy, 25);
			double theta = 90 * Math.PI / 180
				+ 20 * (i - (waysEnemy[grade] - 1) * 0.5) * Math.PI / 180;
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
		20, 17, 14, 10,
	};

	private static final int[] waysEnemy = {
		2, 3, 3, 4,
	};

	private static final double[] speedEnemy = {
		0.006, 0.008, 0.0010, 0.012,
	};

	@Override
	public void firePlayer(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		double theta = 1.5 * Math.PI;
		double speed = 0.04;

		phase.addBulletPlayer(new BulletStraight(
			living.getX() - 0.01,
			living.getY(),
			speed * Math.cos(theta - 5 * Math.PI / 180),
			speed * Math.sin(theta - 5 * Math.PI / 180),
			0.005,
			colorPlayer,
			damagePlayer[grade]));
		phase.addBulletPlayer(new BulletStraight(
			living.getX() + 0.01,
			living.getY(),
			speed * Math.cos(theta + 5 * Math.PI / 180),
			speed * Math.sin(theta + 5 * Math.PI / 180),
			0.005,
			colorPlayer,
			damagePlayer[grade]));
		if (grade >= 1) {
			phase.addBulletPlayer(new BulletStraight(
				living.getX() - 0.01,
				living.getY(),
				speed * Math.cos(theta - 15 * Math.PI / 180),
				speed * Math.sin(theta - 15 * Math.PI / 180),
				0.005,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() + 0.01,
				living.getY(),
				speed * Math.cos(theta + 15 * Math.PI / 180),
				speed * Math.sin(theta + 15 * Math.PI / 180),
				0.005,
				colorPlayer,
				damagePlayer[grade]));
		}
		if (grade >= 2) {
			phase.addBulletPlayer(new BulletStraight(
				living.getX() - 0.01,
				living.getY(),
				speed * Math.cos(theta - 25 * Math.PI / 180),
				speed * Math.sin(theta - 25 * Math.PI / 180),
				0.005,
				colorPlayer,
				damagePlayer[grade]));
			phase.addBulletPlayer(new BulletStraight(
				living.getX() + 0.01,
				living.getY(),
				speed * Math.cos(theta + 25 * Math.PI / 180),
				speed * Math.sin(theta + 25 * Math.PI / 180),
				0.005,
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
		25, 40, 70, 100,
	};

	@Override
	public String getName()
	{
		return "ウッド" + label[grade] + "スパイク";
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
		2, 2, 2, 2,
	};

	private static final int[] waysPlayerAll = {
		2, 4, 6, 6,
	};

}
