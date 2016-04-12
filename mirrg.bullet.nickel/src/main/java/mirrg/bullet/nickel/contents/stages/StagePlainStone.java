package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.bullets.TypeWeaponBullets;
import mirrg.bullet.nickel.contents.weapons.bullets.TypeWeaponEmitter;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.weapon.WeaponDelay;

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
					.addWeapon(new WeaponDelay(100, TypeWeaponBullets.stone.create("L", false)))
					.addStack(() -> new StackItem(Items.stone, 1), 0.2));
			}
			A += 30;
		}
		A -= 150;

		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(1, 0.25, -0.003, 0, 50, Items.stone.getColor())
					.addWeapon(new WeaponDelay(100, TypeWeaponBullets.stone.create("L", false)))
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
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, 600, 0.003, 300)
				.setBoss(true)
				.addWeapon(new WeaponDelay(100, TypeWeaponBullets.stone.create("M", false)))
				.addWeapon(new WeaponDelay(100, TypeWeaponEmitter.dirt.create("M", false)))
				.addStack(() -> new StackItem(Items.stone, 1), 0.75, 5)
				.addStack(() -> new StackItem(Items.dirt, 1), 0.75, 5)
				.hookOnDie(this::addShootdown));
		}
		A += 20;
	}

}
