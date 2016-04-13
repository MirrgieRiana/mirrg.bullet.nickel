package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponSpikes;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

public class StagePlainGrass extends StageAbstract
{

	@Override
	public void registerField()
	{
		lr();
		hard();
	}

	private void lr()
	{
		for (int i = 0; i < 5; i++) {
			if (age == A) {
				for (int j = 0; j < 5; j++) {
					phase.addEnemy(new EnemyFairy1(0.3 + 0.1 * j, 0, 0, 0.005, 50, Items.grass.getColor())
						.addWeapon(new WeaponDelay(20, SupplierCardWeaponSpikes.grass.get("L", false).createWeapon()))
						.addStack(() -> new StackItem(Items.grass, 1), 0.2));
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
			double dps = 50;
			double seconds = 10;
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, (int) (dps * seconds), 0.003, (int) (30 * seconds))
				.setBoss(true)
				.addWeapon(new WeaponDelay(100, SupplierCardWeaponSpikes.grass.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(103, SupplierCardWeaponSpikes.grass.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(106, SupplierCardWeaponSpikes.grass.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(109, SupplierCardWeaponSpikes.grass.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(112, SupplierCardWeaponSpikes.grass.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(115, SupplierCardWeaponSpikes.grass.get("M", false).createWeapon()))
				.addWeapon(new WeaponDelay(118, SupplierCardWeaponSpikes.grass.get("M", false).createWeapon()))
				.addStack(() -> new StackItem(Items.grass, 1), 0.75, 10)
				.hookOnDie(this::addShootdown));
		}
		A += 20;
	}

}
