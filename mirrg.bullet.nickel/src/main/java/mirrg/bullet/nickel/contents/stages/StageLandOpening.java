package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBurst;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

public class StageLandOpening extends StageAbstract
{

	@Override
	protected void registerField()
	{
		for (int i = 0; i < 8; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0, 0.15, 0.002, 0, 50, Items.dirt.getColor())
					.addWeapon(new WeaponDelay(100, SupplierCardWeaponBurst.dirt.get("L", false).createWeapon()))
					.addStack(() -> new StackItem(Items.dirt, 1), 0.33));
			}
			A += 60;
		}
	}

}
