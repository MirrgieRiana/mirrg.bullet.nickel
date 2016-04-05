package mirrg.bullet.nickel.weapons;

import java.awt.Color;

import mirrg.bullet.nickel.InstanceWeapon;
import mirrg.bullet.nickel.entities.bullets.BulletStraight;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.weapons.util.WeaponBase;

public class WeaponScatterCalcite extends WeaponBase
{

	public WeaponScatterCalcite(int grade)
	{
		super(grade, new Color(192, 192, 192));
	}

	// 全方位ワインダー
	@Override
	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase)
	{
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < waysEnemy[grade]; i++) {
				double theta = instanceWeapon.age / spanEnemy[grade] * anglePerShotEnemy[grade] * (j * 2 - 1) * Math.PI / 180;
				double speed = speedEnemy[grade];

				phase.addBulletEnemy(new BulletStraight(
					living.getX(),
					living.getY(),
					speed * Math.cos(theta + i * 360 / waysEnemy[grade] * Math.PI / 180),
					speed * Math.sin(theta + i * 360 / waysEnemy[grade] * Math.PI / 180),
					radiusEnemy[grade],
					colorEnemy,
					100));
			}
		}
	}

	@Override
	public int getSpanEnemy()
	{
		return spanEnemy[grade];
	}

	private static final double[] radiusEnemy = {
		0.004, 0.006, 0.006, 0.008,
	};

	private static final int[] spanEnemy = {
		8, 6, 6, 4,
	};

	private static final int[] waysEnemy = {
		4, 6, 8, 10,
	};

	private static final int[] anglePerShotEnemy = {
		8, 5, 3, 2,
	};

	private static double[] speedEnemy = {
		0.003, 0.004, 0.005, 0.006,
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
		return "カルサイト" + label[grade] + "スキャッター";
	}

	private static final String[] label = {
		"L", "", "H", "V"
	};

}
