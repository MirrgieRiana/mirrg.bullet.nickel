package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.weapons.WeaponSpikesLeaf;
import mirrg.bullet.nickel.contents.weapons.WeaponSpikesWood;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.stage.IStage;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

public class Stage5 implements IStage
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

		lr(phase);

		if (age >= A) {
			finished = true;
		}

		age++;
	}

	private void lr(PhaseBattle phase)
	{
		for (int i = 0; i < 4; i++) {
			if (age == A) {
				for (int j = 0; j < 5; j++) {
					phase.addEnemy(new EnemyFairy1(0.3 + 0.12 * j, 0, 0, 0.003, 50, Items.wood.color)
						.addWeapon(new WeaponDelay(10, new WeaponSpikesWood(0)))
						.addStack(() -> new StackItem(Items.wood, 1), 0.1));
				}
			}
			A += 50;
		}

		for (int i = 0; i < 2; i++) {
			if (age == A) {
				for (int j = 0; j < 10; j++) {
					phase.addEnemy(new EnemyFairy1(0.1 + 0.09 * j, 0, 0, 0.003, 10, Items.leaf.color)
						.addWeapon(new WeaponDelay(10, new WeaponSpikesLeaf(0)))
						.addStack(() -> new StackItem(Items.leaf, 1), 0.1));
				}
			}
			A += 50;
		}
		A += 100;
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}

}
