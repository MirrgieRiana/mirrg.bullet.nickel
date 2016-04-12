package mirrg.bullet.nickel.contents.weapons;

import static mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.Grade;

public class WeaponBulletsTin extends WeaponBulletsBase
{

	public WeaponBulletsTin(int grade)
	{
		super(grade, Items.tin);

		BatteryAbstract base = new BatteryBullets()
			.set(SIZE, null).set(DAMAGE, null)
			.set(V_OFFSET, 0).set(H_OFFSET, 0)
			.set(ANGLE_OFFSET, 0).set(SPEED, null)
			.set(NOIZ_SPEED_RATE, 0).set(NOIZ_SPEED_OFFSET, 0).set(NOIZ_ANGLE, 0)
			.set(WAYS, 1).set(ANGLE_PER_WAY, 0);

		BatteryAbstract baseEnemy = new BatteryBullets(base)
			.set(SIZE, 0.004).set(DAMAGE, 50);
		BatteryAbstract basePlayer = new BatteryBullets(base)
			.set(SIZE, 0.005).set(SPEED, 0.05);

		grades = new Grade[] {
			new Grade("L", 5, 1)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.01))
				.addPlayer(new BatteryBullets(basePlayer).set(DAMAGE, 2)),
			new Grade("", 4, 1)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.015))
				.addPlayer(new BatteryBullets(basePlayer).set(DAMAGE, 6)),
			new Grade("H", 3, 1)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.02))
				.addPlayer(new BatteryBullets(basePlayer).set(DAMAGE, 20)),
			new Grade("V", 2, 1)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.025))
				.addPlayer(new BatteryBullets(basePlayer).set(DAMAGE, 60)),
		};

	}

}
