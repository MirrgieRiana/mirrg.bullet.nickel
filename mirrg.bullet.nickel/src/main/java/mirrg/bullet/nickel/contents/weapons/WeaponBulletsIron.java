package mirrg.bullet.nickel.contents.weapons;

import static mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.Grade;

public class WeaponBulletsIron extends WeaponBulletsBase
{

	public WeaponBulletsIron(int grade)
	{
		super(grade, Items.iron);

		BatteryAbstract base = new BatteryBullets()
			.set(SIZE, null).set(DAMAGE, null)
			.set(V_OFFSET, null).set(H_OFFSET, null)
			.set(ANGLE_OFFSET, 0).set(SPEED, null)
			.set(NOIZ_SPEED_RATE, 0).set(NOIZ_SPEED_OFFSET, 0).set(NOIZ_ANGLE, 0)
			.set(WAYS, 1).set(ANGLE_PER_WAY, 0);

		BatteryAbstract baseEnemy = new BatteryBullets(base)
			.set(COLOR, item.color).set(SIZE, 0.008).set(DAMAGE, 100).set(V_OFFSET, 0).set(H_OFFSET, 0).set(SPEED, 0.0075);
		BatteryAbstract basePlayer = new BatteryBullets(base)
			.set(COLOR, paler(item.color)).set(SIZE, 0.01).set(SPEED, 0.05);

		BatteryAbstract basePlayer1 = new BatteryBullets(basePlayer).set(DAMAGE, 5);
		BatteryAbstract basePlayer2 = new BatteryBullets(basePlayer).set(DAMAGE, 10);
		BatteryAbstract basePlayer3 = new BatteryBullets(basePlayer).set(DAMAGE, 15);
		BatteryAbstract basePlayer4 = new BatteryBullets(basePlayer).set(DAMAGE, 20);

		BatteryAbstract basePlayerA1 = new BatteryBullets().set(V_OFFSET, 0).set(H_OFFSET, -0.01);
		BatteryAbstract basePlayerA2 = new BatteryBullets().set(V_OFFSET, 0).set(H_OFFSET, 0.01);
		BatteryAbstract basePlayerB1 = new BatteryBullets().set(V_OFFSET, -0.01).set(H_OFFSET, -0.03);
		BatteryAbstract basePlayerB2 = new BatteryBullets().set(V_OFFSET, -0.01).set(H_OFFSET, 0.03);
		BatteryAbstract basePlayerC1 = new BatteryBullets().set(V_OFFSET, -0.03).set(H_OFFSET, -0.04);
		BatteryAbstract basePlayerC2 = new BatteryBullets().set(V_OFFSET, -0.03).set(H_OFFSET, 0.04);
		BatteryAbstract basePlayerD1 = new BatteryBullets().set(V_OFFSET, -0.05).set(H_OFFSET, -0.05);
		BatteryAbstract basePlayerD2 = new BatteryBullets().set(V_OFFSET, -0.05).set(H_OFFSET, 0.05);

		grades = new Grade[] {
			new Grade("L", 50, 3)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.004))
				.addPlayer(new BatteryBullets(basePlayer1, basePlayerA1))
				.addPlayer(new BatteryBullets(basePlayer1, basePlayerA2)),
			new Grade("", 40, 3)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.005))
				.addPlayer(new BatteryBullets(basePlayer2, basePlayerA1))
				.addPlayer(new BatteryBullets(basePlayer2, basePlayerA2))
				.addPlayer(new BatteryBullets(basePlayer2, basePlayerB1))
				.addPlayer(new BatteryBullets(basePlayer2, basePlayerB2)),
			new Grade("H", 30, 2)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.006))
				.addPlayer(new BatteryBullets(basePlayer3, basePlayerA1))
				.addPlayer(new BatteryBullets(basePlayer3, basePlayerA2))
				.addPlayer(new BatteryBullets(basePlayer3, basePlayerB1))
				.addPlayer(new BatteryBullets(basePlayer3, basePlayerB2))
				.addPlayer(new BatteryBullets(basePlayer2, basePlayerC1))
				.addPlayer(new BatteryBullets(basePlayer2, basePlayerC2)),
			new Grade("V", 20, 2)
				.addEnemy(new BatteryBullets(baseEnemy).set(SPEED, 0.007))
				.addPlayer(new BatteryBullets(basePlayer4, basePlayerA1))
				.addPlayer(new BatteryBullets(basePlayer4, basePlayerA2))
				.addPlayer(new BatteryBullets(basePlayer4, basePlayerB1))
				.addPlayer(new BatteryBullets(basePlayer4, basePlayerB2))
				.addPlayer(new BatteryBullets(basePlayer4, basePlayerC1))
				.addPlayer(new BatteryBullets(basePlayer4, basePlayerC2))
				.addPlayer(new BatteryBullets(basePlayer4, basePlayerD1))
				.addPlayer(new BatteryBullets(basePlayer4, basePlayerD2)),
		};

	}

}
