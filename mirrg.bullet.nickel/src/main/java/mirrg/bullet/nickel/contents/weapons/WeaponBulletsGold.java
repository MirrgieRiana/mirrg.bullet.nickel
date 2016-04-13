package mirrg.bullet.nickel.contents.weapons;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.instance.Grade;

public class WeaponBulletsGold extends WeaponBulletsBase
{

	public WeaponBulletsGold(int grade)
	{
		super(grade, Items.gold);

		CardBatteryAbstract base = new CardBatteryBullets()
			.set(SIZE, null).set(DAMAGE, null)
			.set(V_OFFSET, 0).set(H_OFFSET, 0)
			.set(ANGLE_OFFSET, 0).set(SPEED, null)
			.set(NOIZ_SPEED_RATE, null).set(NOIZ_SPEED_OFFSET, 0).set(NOIZ_ANGLE, null)
			.set(WAYS, null).set(ANGLE_PER_WAY, null);

		CardBatteryAbstract baseEnemy = new CardBatteryBullets(base)
			.set(SIZE, 0.01).set(DAMAGE, 100).set(ANGLE_PER_WAY, 0);
		CardBatteryAbstract basePlayer = new CardBatteryBullets(base)
			.set(SIZE, 0.012).set(SPEED, 0.03).set(ANGLE_PER_WAY, 0);

		grades = new Grade[] {
			new Grade("L", 60, 5)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.01).set(NOIZ_SPEED_RATE, 0.01).set(NOIZ_ANGLE, 1).set(WAYS, 3))
				.addPlayer(new CardBatteryBullets(basePlayer).set(DAMAGE, 8).set(NOIZ_SPEED_RATE, 0.01).set(NOIZ_ANGLE, 1).set(WAYS, 3)),
			new Grade("", 55, 5)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.015).set(NOIZ_SPEED_RATE, 0.015).set(NOIZ_ANGLE, 1.5).set(WAYS, 5))
				.addPlayer(new CardBatteryBullets(basePlayer).set(DAMAGE, 15).set(NOIZ_SPEED_RATE, 0.015).set(NOIZ_ANGLE, 1.5).set(WAYS, 5)),
			new Grade("H", 50, 4)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.02).set(NOIZ_SPEED_RATE, 0.02).set(NOIZ_ANGLE, 2).set(WAYS, 7))
				.addPlayer(new CardBatteryBullets(basePlayer).set(DAMAGE, 30).set(NOIZ_SPEED_RATE, 0.02).set(NOIZ_ANGLE, 2).set(WAYS, 7)),
			new Grade("V", 45, 4)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.025).set(NOIZ_SPEED_RATE, 0.03).set(NOIZ_ANGLE, 3).set(WAYS, 9))
				.addPlayer(new CardBatteryBullets(basePlayer).set(DAMAGE, 60).set(NOIZ_SPEED_RATE, 0.03).set(NOIZ_ANGLE, 3).set(WAYS, 9)),
		};

	}

}
