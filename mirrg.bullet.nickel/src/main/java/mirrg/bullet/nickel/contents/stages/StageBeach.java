package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBurst;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponSpark;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

public class StageBeach extends StageAbstract
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
				phase.addEnemy(new EnemyFairy1(0, 0.1, 0.005, 0, 100, Items.sand.getColor())
					.addWeapon(new WeaponDelay(20, SupplierCardWeaponBurst.sand.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.sand, 1), 0.2));
				phase.addEnemy(new EnemyFairy1(1, 0.2, -0.005, 0, 100, Items.sand.getColor())
					.addWeapon(new WeaponDelay(20, SupplierCardWeaponBurst.sand.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.sand, 1), 0.2));
			}
			A += 30;
		}
		A -= 150;

		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0.1, 1, 0, -0.005, 100, Items.sand.getColor())
					.addWeapon(new WeaponDelay(20, SupplierCardWeaponBurst.sand.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.sand, 1), 0.2));
				phase.addEnemy(new EnemyFairy1(0.9, 1, 0, -0.005, 100, Items.sand.getColor())
					.addWeapon(new WeaponDelay(20, SupplierCardWeaponBurst.sand.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.sand, 1), 0.2));
			}
			A += 30;
		}
		A += 200;
	}

	private void hard()
	{
		if (age == A) {
			addNorm();
			double dps = 100;
			double seconds = 15;
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, (int) (dps * seconds), 0.003, (int) (30 * seconds))
				.setBoss(true)
				.addWeapon(new WeaponDelay(50, SupplierCardWeaponBurst.sand.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(55, SupplierCardWeaponBurst.sand.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(50, SupplierCardWeaponSpark.sand.get("M", false).createWeapon()))
				.addStack(() -> new StackItem(Items.sand, 1), 0.75, 5)
				.hookOnDie(this::addShootdown));
		}
		A += 20;
	}

}
