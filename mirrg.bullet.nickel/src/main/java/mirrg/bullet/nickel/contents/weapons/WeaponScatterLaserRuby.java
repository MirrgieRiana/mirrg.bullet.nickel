package mirrg.bullet.nickel.contents.weapons;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.bullets.BulletStraight;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class WeaponScatterLaserRuby extends WeaponAbstract
{

	public WeaponScatterLaserRuby(int grade)
	{
		super(grade, Items.ruby.color);
	}

	@Override
	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < bulletsEnemy[grade]; j++) {

				BulletStraight bullet = new BulletStraight(living.getX(), living.getY(), 0, 0, 0.005, colorEnemy, 50);
				double theta = (i * 2 - 1) * instanceWeapon.age * angleEnemy[grade] * Math.PI / 180
					+ 270 * Math.PI / 180;
				double speed = speedEnemy[grade] + 0.0006 * j;
				bullet.xx = speed * Math.cos(theta);
				bullet.yy = speed * Math.sin(theta);

				phase.addBulletEnemy(bullet);
			}
		}
	}

	@Override
	public int getSpanEnemy()
	{
		return 1;
	}

	private static final int[] bulletsEnemy = {
		7, 9, 12, 16,
	};

	private static final double[] angleEnemy = {
		8.2, 5.2, 4.2, 3.2,
	};

	private static final double[] speedEnemy = {
		0.01, 0.015, 0.02, 0.025,
	};

	@Override
	public void firePlayer(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int getSpanPlayer()
	{
		return spanPlayer[grade];
	}

	private static final int[] spanPlayer = {
		// TODO 自動生成されたメソッド・スタブ

	};

	@Override
	public String getName()
	{
		return "ルビー" + label[grade] + "レーザースキャッター";
	}

	private static final String[] label = {
		"L", "", "H", "V"
	};

	@Override
	public double getDPSConcentrate()
	{
		return 0;
	}

	@Override
	public double getDPSScatter()
	{
		return 0;
	}

}
