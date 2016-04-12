package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.bullets.TypeWeaponBullets;
import mirrg.bullet.nickel.contents.weapons.bullets.TypeWeaponBurst;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.WeaponDelay;

public class StagePlainDirt extends StageAbstract
{

	@Override
	protected void registerField()
	{
		lr();
		hard();
	}

	private void lr()
	{
		for (int i = 0; i < 10; i++) {
			if (age == A) {
				for (int j = 0; j < 5; j++) {
					phase.addEnemy(new EnemyFairy1(0 + 0.005 * (5 - j), 0.10 + 0.05 * j, 0.003, 0, 50, Items.dirt.getColor())
						.addWeapon(new WeaponDelay(100, TypeWeaponBullets.stone.create("L", false)))
						.addStack(() -> new StackItem(Items.dirt, 1), 0.2));
				}
			}
			A += 30;
		}
		A += 200;
	}

	private void hard()
	{
		if (age == A) {
			addNorm();
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, 600, 0.003, 300)
				.setBoss(true)
				.addWeapon(new WeaponDelay(100, TypeWeaponBurst.dirt.create("M", false)))
				.addWeapon(new WeaponDelay(110, TypeWeaponBurst.dirt.create("M", false)))
				.addWeapon(new WeaponDelay(120, TypeWeaponBurst.dirt.create("M", false)))
				.addWeapon(new WeaponDelay(130, TypeWeaponBurst.dirt.create("M", false)))
				.addWeapon(new WeaponDelay(140, TypeWeaponBurst.dirt.create("M", false)))
				.addStack(() -> new StackItem(Items.dirt, 1), 0.75, 10)
				.hookOnDie(this::addShootdown));
		}
		A += 20;
	}

}
