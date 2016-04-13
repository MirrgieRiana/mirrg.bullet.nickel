package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBurst;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponSpark;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

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
						.addWeapon(new WeaponDelay(100, SupplierCardWeaponBurst.dirt.get("L", false).createWeapon()))
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
			double dps = 25;
			double seconds = 10;
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, (int) (dps * seconds), 0.003, (int) (30 * seconds))
				.setBoss(true)
				.addWeapon(new WeaponDelay(100, SupplierCardWeaponBurst.dirt.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(110, SupplierCardWeaponBurst.dirt.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(120, SupplierCardWeaponBurst.dirt.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(130, SupplierCardWeaponBurst.dirt.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(140, SupplierCardWeaponBurst.dirt.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(100, SupplierCardWeaponSpark.dirt.get("L", false).createWeapon()))
				.addStack(() -> new StackItem(Items.dirt, 1), 0.75, 10)
				.hookOnDie(this::addShootdown));
		}
		A += 20;
	}

}
