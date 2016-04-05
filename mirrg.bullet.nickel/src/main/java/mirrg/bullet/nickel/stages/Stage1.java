package mirrg.bullet.nickel.stages;

import mirrg.bullet.nickel.IStage;
import mirrg.bullet.nickel.entities.EnemyFairy1;
import mirrg.bullet.nickel.item.Items;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.weapons.WeaponBulletsNickel;
import mirrg.bullet.nickel.weapons.util.WeaponDelay;

public class Stage1 implements IStage
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
		for (int i = 0; i < 10; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0, 0.15, 0.002, 0, 100)
					.addWeapon(new WeaponDelay(100, new WeaponBulletsNickel(0)))
					.addStack(() ->new StackItem(Items.nickel, 1), 0.1));
			}
			A += 40;
		}
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}

}
