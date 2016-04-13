package mirrg.bullet.nickel.contents.weapons;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.instance.Grade;

public class WeaponBulletsIron extends WeaponBulletsBase
{

	public WeaponBulletsIron(int grade)
	{
		super(grade, Items.iron);

		CardBatteryAbstract base = new CardBatteryBullets()
			.set(SIZE, null).set(DAMAGE, null)
			.set(V_OFFSET, null).set(H_OFFSET, null)
			.set(ANGLE_OFFSET, 0).set(SPEED, null)
			.set(NOIZ_SPEED_RATE, 0).set(NOIZ_SPEED_OFFSET, 0).set(NOIZ_ANGLE, 0)
			.set(WAYS, 1).set(ANGLE_PER_WAY, 0);

		CardBatteryAbstract baseEnemy = new CardBatteryBullets(base)
			.set(COLOR, item.color).set(SIZE, 0.008).set(DAMAGE, 100).set(V_OFFSET, 0).set(H_OFFSET, 0).set(SPEED, 0.0075);
		CardBatteryAbstract basePlayer = new CardBatteryBullets(base)
			.set(COLOR, paler(item.color)).set(SIZE, 0.01).set(SPEED, 0.05);

		CardBatteryAbstract basePlayer1 = new CardBatteryBullets(basePlayer).set(DAMAGE, 5);
		CardBatteryAbstract basePlayer2 = new CardBatteryBullets(basePlayer).set(DAMAGE, 10);
		CardBatteryAbstract basePlayer3 = new CardBatteryBullets(basePlayer).set(DAMAGE, 15);
		CardBatteryAbstract basePlayer4 = new CardBatteryBullets(basePlayer).set(DAMAGE, 20);

		CardBatteryAbstract basePlayerA1 = new CardBatteryBullets().set(V_OFFSET, 0).set(H_OFFSET, -0.01);
		CardBatteryAbstract basePlayerA2 = new CardBatteryBullets().set(V_OFFSET, 0).set(H_OFFSET, 0.01);
		CardBatteryAbstract basePlayerB1 = new CardBatteryBullets().set(V_OFFSET, -0.01).set(H_OFFSET, -0.03);
		CardBatteryAbstract basePlayerB2 = new CardBatteryBullets().set(V_OFFSET, -0.01).set(H_OFFSET, 0.03);
		CardBatteryAbstract basePlayerC1 = new CardBatteryBullets().set(V_OFFSET, -0.03).set(H_OFFSET, -0.04);
		CardBatteryAbstract basePlayerC2 = new CardBatteryBullets().set(V_OFFSET, -0.03).set(H_OFFSET, 0.04);
		CardBatteryAbstract basePlayerD1 = new CardBatteryBullets().set(V_OFFSET, -0.05).set(H_OFFSET, -0.05);
		CardBatteryAbstract basePlayerD2 = new CardBatteryBullets().set(V_OFFSET, -0.05).set(H_OFFSET, 0.05);

		grades = new Grade[] {
			new Grade("L", 50, 3)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.004))
				.addPlayer(new CardBatteryBullets(basePlayer1, basePlayerA1))
				.addPlayer(new CardBatteryBullets(basePlayer1, basePlayerA2)),
			new Grade("", 40, 3)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.005))
				.addPlayer(new CardBatteryBullets(basePlayer2, basePlayerA1))
				.addPlayer(new CardBatteryBullets(basePlayer2, basePlayerA2))
				.addPlayer(new CardBatteryBullets(basePlayer2, basePlayerB1))
				.addPlayer(new CardBatteryBullets(basePlayer2, basePlayerB2)),
			new Grade("H", 30, 2)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.006))
				.addPlayer(new CardBatteryBullets(basePlayer3, basePlayerA1))
				.addPlayer(new CardBatteryBullets(basePlayer3, basePlayerA2))
				.addPlayer(new CardBatteryBullets(basePlayer3, basePlayerB1))
				.addPlayer(new CardBatteryBullets(basePlayer3, basePlayerB2))
				.addPlayer(new CardBatteryBullets(basePlayer2, basePlayerC1))
				.addPlayer(new CardBatteryBullets(basePlayer2, basePlayerC2)),
			new Grade("V", 20, 2)
				.addEnemy(new CardBatteryBullets(baseEnemy).set(SPEED, 0.007))
				.addPlayer(new CardBatteryBullets(basePlayer4, basePlayerA1))
				.addPlayer(new CardBatteryBullets(basePlayer4, basePlayerA2))
				.addPlayer(new CardBatteryBullets(basePlayer4, basePlayerB1))
				.addPlayer(new CardBatteryBullets(basePlayer4, basePlayerB2))
				.addPlayer(new CardBatteryBullets(basePlayer4, basePlayerC1))
				.addPlayer(new CardBatteryBullets(basePlayer4, basePlayerC2))
				.addPlayer(new CardBatteryBullets(basePlayer4, basePlayerD1))
				.addPlayer(new CardBatteryBullets(basePlayer4, basePlayerD2)),
		};

	}

}
