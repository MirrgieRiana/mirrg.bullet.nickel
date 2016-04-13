package mirrg.bullet.nickel.contents.weapons;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.instance.Grade;

public class WeaponBulletsTin extends WeaponBulletsBase
{

	public WeaponBulletsTin(int grade)
	{
		super(grade, Items.tin);

		CardBatteryAbstract base = new CardBatteryBullets()
			.set(SIZE, null).set(DAMAGE, null)
			.set(V_OFFSET, 0).set(H_OFFSET, 0)
			.set(ANGLE_OFFSET, 0).set(SPEED, null)
			.set(NOIZ_SPEED_RATE, 0).set(NOIZ_SPEED_OFFSET, 0).set(NOIZ_ANGLE, 0)
			.set(WAYS, 1).set(ANGLE_PER_WAY, 0);

		CardBatteryAbstract baseEnemy = new CardBatteryBullets(base)
			.set(SIZE, 0.004).set(DAMAGE, 50);
		CardBatteryAbstract basePlayer = new CardBatteryBullets(base)
			.set(SIZE, 0.005).set(SPEED, 0.05);

		grades = new Grade[] {
			new Grade("L", 5, 1)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.01))
				.addPlayer(new CardBatteryBullets(basePlayer).set(DAMAGE, 2)),
			new Grade("", 4, 1)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.015))
				.addPlayer(new CardBatteryBullets(basePlayer).set(DAMAGE, 6)),
			new Grade("H", 3, 1)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.02))
				.addPlayer(new CardBatteryBullets(basePlayer).set(DAMAGE, 20)),
			new Grade("V", 2, 1)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.025))
				.addPlayer(new CardBatteryBullets(basePlayer).set(DAMAGE, 60)),
		};

	}

}
