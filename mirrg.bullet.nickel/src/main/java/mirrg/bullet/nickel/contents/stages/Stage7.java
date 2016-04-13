package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.contents.entities.EnemyFairy1;
import mirrg.bullet.nickel.contents.entities.EnemyFairy2;
import mirrg.bullet.nickel.contents.weapons.WeaponBulletsTin;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.stage.IStage;
import mirrg.bullet.nickel.weapon.instance.WeaponDelay;

public class Stage7 implements IStage
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

		hard(phase);

		if (age >= A) {
			finished = true;
		}

		age++;
	}

	private void lr(PhaseBattle phase)
	{
		for (int i = 0; i < 50; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0, 0.15, 0.01, 0, 100, Items.tin.color)
					.addWeapon(new WeaponDelay(20, new WeaponBulletsTin(0)))
					.addStack(() -> new StackItem(Items.tin, 1), 0.1));
			}
			A += 5;
		}
		A += 200;
	}

	private void hard(PhaseBattle phase)
	{
		if (age == A) {
			phase.addEnemy(new EnemyFairy2(0.4, 0, 0.05, 1000, 0.003, 600)
				.addWeapon(new WeaponDelay(100, new WeaponBulletsTin(1)))
				.addWeapon(new WeaponDelay(100, new WeaponDelay(10, new WeaponBulletsTin(1))))
				.addStack(() -> new StackItem(Items.tin, 1), 0.75, 5));
			phase.addEnemy(new EnemyFairy2(0.6, 0, 0.05, 1000, 0.003, 600)
				.addWeapon(new WeaponDelay(100, new WeaponBulletsTin(1)))
				.addWeapon(new WeaponDelay(100, new WeaponDelay(10, new WeaponBulletsTin(1))))
				.addStack(() -> new StackItem(Items.tin, 1), 0.75, 5));
		}
		A += 20;
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}

}
