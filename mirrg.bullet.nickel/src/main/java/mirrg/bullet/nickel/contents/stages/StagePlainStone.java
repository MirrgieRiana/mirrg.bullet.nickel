package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBullets;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponEmitter;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

public class StagePlainStone extends StageAbstract
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
				phase.addEnemy(new EnemyFairy1(0, 0.15, 0.003, 0, 50, Items.stone.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponBullets.stone.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.stone, 1), 0.2));
			}
			A += 30;
		}
		A -= 150;

		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(1, 0.25, -0.003, 0, 50, Items.stone.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponBullets.stone.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.stone, 1), 0.2));
			}
			A += 30;
		}
		A += 150;
	}

	private void hard()
	{
		if (age == A) {
			addNorm();
			double dps = 40;
			double seconds = 10;
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, (int) (dps * seconds), 0.003, (int) (30 * seconds))
				.setBoss(true)
				.addWeapon(new WeaponDelay(100, SupplierCardWeaponBullets.stone.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(100, SupplierCardWeaponEmitter.stone.get("L", false).createWeapon()))
				.addStack(() -> new StackItem(Items.stone, 1), 0.75, 5)
				.hookOnDie(this::addShootdown));
		}
		A += 20;
	}

}
