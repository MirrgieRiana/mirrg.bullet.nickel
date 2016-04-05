package mirrg.bullet.nickel.stages;

import mirrg.bullet.nickel.IStage;
import mirrg.bullet.nickel.entities.EnemyFairy1;
import mirrg.bullet.nickel.entities.EnemyFairy2;
import mirrg.bullet.nickel.item.Items;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.weapons.WeaponBulletsCalcite;
import mirrg.bullet.nickel.weapons.WeaponBulletsNickel;
import mirrg.bullet.nickel.weapons.util.WeaponDelay;

public class Stage2 implements IStage
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
		for (int i = 0; i < 15; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(0, 0.15, 0.003, 0, 100)
					.addWeapon(new WeaponDelay(100, new WeaponBulletsNickel(0)))
					.addStack(() -> new StackItem(Items.nickel, 1), 0.1));
			}
			A += 20;
		}
		A -= 150;

		for (int i = 0; i < 15; i++) {
			if (age == A) {
				phase.addEnemy(new EnemyFairy1(1, 0.25, -0.003, 0, 100)
					.addWeapon(new WeaponDelay(100, new WeaponBulletsNickel(0)))
					.addStack(() -> new StackItem(Items.nickel, 1), 0.1));
			}
			A += 20;
		}
		A += 150;
	}

	private void hard(PhaseBattle phase)
	{
		if (age == A) {
			phase.addEnemy(new EnemyFairy2(0.5, 0, 0.05, 1000, 0.003, 400)
				.addWeapon(new WeaponDelay(100, new WeaponBulletsCalcite(1)))
				.addStack(() -> new StackItem(Items.calcite, 1), 0.75, 5));
		}
		A += 20;
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}

}
