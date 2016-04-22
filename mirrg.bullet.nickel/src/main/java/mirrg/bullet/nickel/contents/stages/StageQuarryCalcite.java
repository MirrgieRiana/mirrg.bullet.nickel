package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBullets;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponEmitter;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

public class StageQuarryCalcite extends StageAbstract
{

	@Override
	public void registerField()
	{
		lr();
		hard();
	}

	private void lr()
	{
		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0, 0.2, 0.003, 0, 100, Items.calcite.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponBullets.calcite.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.calcite, 1), 0.1));
				phase.addEnemy(new EnemyFairy1(1, 0.8, -0.003, 0, 100, Items.calcite.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponBullets.calcite.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.calcite, 1), 0.1));
				phase.addEnemy(new EnemyFairy1(0.8, 0, 0, 0.003, 100, Items.calcite.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponBullets.calcite.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.calcite, 1), 0.1));
				phase.addEnemy(new EnemyFairy1(0.2, 1, 0, -0.003, 100, Items.calcite.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponBullets.calcite.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.calcite, 1), 0.1));
			}
			A += 20;
		}
		A += 200;
	}

	private void hard()
	{
		if (age == A) {
			addNorm();
			double dps = 75;
			double seconds = 10;
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, (int) (dps * seconds), 0.003, (int) (30 * seconds))
				.setBoss(true)
				.addWeapon(new WeaponDelay(100, SupplierCardWeaponEmitter.calcite.get("M", false).createWeapon())) // TODO
				.addWeapon(new WeaponDelay(100, SupplierCardWeaponBullets.calcite.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(105, SupplierCardWeaponBullets.calcite.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(110, SupplierCardWeaponBullets.calcite.get("M", false).createWeapon()))
				.addStack(() -> new StackItem(Items.calcite, 1), 0.5, 5)
				.hookOnDie(this::addShootdown));
		}
		A += 20;
	}

}
