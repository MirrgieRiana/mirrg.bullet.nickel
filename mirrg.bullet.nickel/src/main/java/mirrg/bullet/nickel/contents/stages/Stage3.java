package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyBoss1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.WeaponLaserRuby;
import mirrg.bullet.nickel.contents.weapons.WeaponScatterCalcite;
import mirrg.bullet.nickel.contents.weapons.WeaponScatterLaserRuby;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.stage.IStage;
import mirrg.bullet.nickel.weapon.WeaponDelay;

public class Stage3 implements IStage
{

	@Override
	public void init(PhaseBattle phase)
	{
		age = 0;
	}

	private int age;
	private boolean finished = false;
	private int A;

	@Override
	public void move(PhaseBattle phase)
	{
		A = 20;

		lrlr(phase);

		lrDouji(phase);

		ruby(phase);

		issei(phase);

		lrDouji(phase);

		emerald(phase);

		boss(phase);

		if (age >= A) {
			finished = true;
		}

		age++;
	}

	private void lrlr(PhaseBattle phase)
	{
		for (int j = 0; j < 2; j++) {

			for (int i = 0; i < 10; i++) {
				if (age == A) {
					phase.addEnemy(new EnemyFairy1(0, 0.15, 0.005, 0, 25, Items.nickel.color)
						.addWeapon(new WeaponDelay(50, new WeaponBulletsNickel(0)))
						.addStack(() -> new StackItem(Items.nickel, 1), 0.1));
				}
				A += 10;
			}

			for (int i = 0; i < 10; i++) {
				if (age == A) {
					phase.addEnemy(new EnemyFairy1(1, 0.15, -0.005, 0, 25, Items.nickel.color)
						.addWeapon(new WeaponDelay(50, new WeaponBulletsNickel(0)))
						.addStack(() -> new StackItem(Items.nickel, 1), 0.1));
				}
				A += 10;
			}

			A += 100;
		}
	}

	private void lrDouji(PhaseBattle phase)
	{
		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0, 0.25, 0.005, 0, 100, Items.nickel.color)
					.addWeapon(new WeaponDelay(50, new WeaponBulletsNickel(0)))
					.addStack(() -> new StackItem(Items.nickel, 1), 0.1));
				phase.addEnemy(new EnemyFairy1(1, 0.35, -0.005, 0, 100, Items.nickel.color)
					.addWeapon(new WeaponDelay(50, new WeaponBulletsNickel(0)))
					.addStack(() -> new StackItem(Items.nickel, 1), 0.1));
			}
			A += 10;
		}
		A += 100;

		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0, 0.45, 0.005, 0, 100, Items.nickel.color)
					.addWeapon(new WeaponDelay(50, new WeaponBulletsNickel(0)))
					.addStack(() -> new StackItem(Items.nickel, 1), 0.1));
				phase.addEnemy(new EnemyFairy1(1, 0.55, -0.005, 0, 100, Items.nickel.color)
					.addWeapon(new WeaponDelay(50, new WeaponBulletsNickel(0)))
					.addStack(() -> new StackItem(Items.nickel, 1), 0.1));
			}
			A += 10;
		}
		A += 100;
	}

	private void issei(PhaseBattle phase)
	{
		for (int i = 0; i < 5; i++) {
			if (age == A) {
				for (int j = 0; j < 10; j++) {
					phase.addEnemy(new EnemyFairy1(0.1 + 0.08 * j, 0, 0, 0.005, 25, Items.calcite.color)
						.addWeapon(new WeaponBulletsCalcite(0))
						.addStack(() -> new StackItem(Items.calcite, 1), 0.1));
				}
			}
			A += 30;
		}
		A += 200;
	}

	private void ruby(PhaseBattle phase)
	{
		if (age == A) {
			phase.addEnemy(new EnemyFairy2(0.25, 0, 0.04, 2000, 0.004, 100)
				.addWeapon(new WeaponScatterLaserRuby(0))
				.addStack(() -> new StackItem(Items.ruby, 1), 0.1, 5));
		}
		A += 200;

		if (age == A) {
			phase.addEnemy(new EnemyFairy2(0.75, 0, 0.04, 2000, 0.004, 100)
				.addWeapon(new WeaponScatterLaserRuby(0))
				.addStack(() -> new StackItem(Items.ruby, 1), 0.1, 5));
		}
		A += 200;

		if (age == A) {
			phase.addEnemy(new EnemyFairy2(0.25, 0, 0.04, 2000, 0.004, 100)
				.addWeapon(new WeaponScatterLaserRuby(0))
				.addStack(() -> new StackItem(Items.ruby, 1), 0.1, 5));
			phase.addEnemy(new EnemyFairy2(0.75, 0, 0.04, 2000, 0.004, 100)
				.addWeapon(new WeaponScatterLaserRuby(0))
				.addStack(() -> new StackItem(Items.ruby, 1), 0.1, 5));
		}
		A += 200;
	}

	private void emerald(PhaseBattle phase)
	{
		if (age == A) {
			phase.addEnemy(new EnemyFairy2(0.2, 0, 0.04, 1500, 0.004, 100)
				.addWeapon(new WeaponScatterLaserRuby(0))
				.addStack(() -> new StackItem(Items.ruby, 1), 0.1, 5));
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.04, 1500, 0.004, 100)
				.addWeapon(new WeaponScatterLaserRuby(0))
				.addStack(() -> new StackItem(Items.ruby, 1), 0.1, 5));
			phase.addEnemy(new EnemyFairy2(0.8, 0, 0.04, 1500, 0.004, 100)
				.addWeapon(new WeaponScatterLaserRuby(0))
				.addStack(() -> new StackItem(Items.ruby, 1), 0.1, 5));
		}
		A += 450;
	}

	private void boss(PhaseBattle phase)
	{
		if (age == A) {
			double x = 0.5, y = 0.15, r = 0.1;

			phase.addEnemy(new EnemyBoss1(x, y, r, 8000, 0)
				.setNext(new EnemyBoss1(x, y, r, 16000, 30)
					.setNext(new EnemyBoss1(x, y, r, 8000, 0)
						.setNext(new EnemyBoss1(x, y, r, 20000, 30)
							.addWeapon(new WeaponScatterLaserRuby(1))
							.addWeapon(new WeaponLaserRuby(0))
							.addWeapon(new WeaponDelay(4, new WeaponLaserRuby(0)))
							.addWeapon(new WeaponDelay(8, new WeaponLaserRuby(0)))
							.addStack(() -> new StackItem(Items.ruby, 1), 0.75, 20))
						.addWeapon(new WeaponScatterLaserRuby(0))
						.addWeapon(new WeaponDelay(10, new WeaponScatterLaserRuby(0))))
					.addWeapon(new WeaponScatterCalcite(1))
					.addWeapon(new WeaponBulletsNickel(1))
					.addStack(() -> new StackItem(Items.calcite, 1), 0.75, 15)
					.addStack(() -> new StackItem(Items.nickel, 1), 0.75, 5))
				.addWeapon(new WeaponScatterCalcite(0)));
		}
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}

}
