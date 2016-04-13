package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponChunk;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

public class StageQuarryCopper extends StageAbstract
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
				phase.addEnemy(new EnemyFairy1(0, 0.15, 0.003, 0, 200, Items.copper.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponChunk.copper.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.copper, 1), 0.1));
			}
			A += 20;
		}
		A -= 200;

		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(1, 0.2, -0.003, 0, 200, Items.copper.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponChunk.copper.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.copper, 1), 0.1));
			}
			A += 20;
		}
		A -= 200;

		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0, 0.25, 0.003, 0, 200, Items.copper.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponChunk.copper.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.copper, 1), 0.1));
			}
			A += 20;
		}
		A -= 200;

		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(1, 0.3, -0.003, 0, 200, Items.copper.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponChunk.copper.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.copper, 1), 0.1));
			}
			A += 20;
		}
		A += 200;
	}

	private void hard()
	{
		if (age == A) {
			addNorm();
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, 1000, 0.003, 600)
				.setBoss(true)
				.addWeapon(new WeaponDelay(100, SupplierCardWeaponChunk.copper.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(110, SupplierCardWeaponChunk.copper.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(120, SupplierCardWeaponChunk.copper.get("M", false).createWeapon()))
				.addStack(() -> new StackItem(Items.copper, 1), 0.5, 5)
				.hookOnDie(this::addShootdown));
		}
		A += 20;
	}

}
