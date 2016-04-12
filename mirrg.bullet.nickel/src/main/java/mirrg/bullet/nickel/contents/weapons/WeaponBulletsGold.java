package mirrg.bullet.nickel.contents.weapons;

import static mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.Grade;

public class WeaponBulletsGold extends WeaponBulletsBase
{

	public WeaponBulletsGold(int grade)
	{
		super(grade, Items.gold);

		BatteryAbstract base = new BatteryBullets()
			.set(SIZE, null).set(DAMAGE, null)
			.set(V_OFFSET, 0).set(H_OFFSET, 0)
			.set(ANGLE_OFFSET, 0).set(SPEED, null)
			.set(NOIZ_SPEED_RATE, null).set(NOIZ_SPEED_OFFSET, 0).set(NOIZ_ANGLE, null)
			.set(WAYS, null).set(ANGLE_PER_WAY, null);

		BatteryAbstract baseEnemy = new BatteryBullets(base)
			.set(SIZE, 0.01).set(DAMAGE, 100).set(ANGLE_PER_WAY, 0);
		BatteryAbstract basePlayer = new BatteryBullets(base)
			.set(SIZE, 0.012).set(SPEED, 0.03).set(ANGLE_PER_WAY, 0);

		grades = new Grade[] {
			new Grade("L", 60, 5)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.01).set(NOIZ_SPEED_RATE, 0.01).set(NOIZ_ANGLE, 1).set(WAYS, 3))
				.addPlayer(new BatteryBullets(basePlayer).set(DAMAGE, 8).set(NOIZ_SPEED_RATE, 0.01).set(NOIZ_ANGLE, 1).set(WAYS, 3)),
			new Grade("", 55, 5)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.015).set(NOIZ_SPEED_RATE, 0.015).set(NOIZ_ANGLE, 1.5).set(WAYS, 5))
				.addPlayer(new BatteryBullets(basePlayer).set(DAMAGE, 15).set(NOIZ_SPEED_RATE, 0.015).set(NOIZ_ANGLE, 1.5).set(WAYS, 5)),
			new Grade("H", 50, 4)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.02).set(NOIZ_SPEED_RATE, 0.02).set(NOIZ_ANGLE, 2).set(WAYS, 7))
				.addPlayer(new BatteryBullets(basePlayer).set(DAMAGE, 30).set(NOIZ_SPEED_RATE, 0.02).set(NOIZ_ANGLE, 2).set(WAYS, 7)),
			new Grade("V", 45, 4)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.025).set(NOIZ_SPEED_RATE, 0.03).set(NOIZ_ANGLE, 3).set(WAYS, 9))
				.addPlayer(new BatteryBullets(basePlayer).set(DAMAGE, 60).set(NOIZ_SPEED_RATE, 0.03).set(NOIZ_ANGLE, 3).set(WAYS, 9)),
		};

	}

}
