package mirrg.bullet.nickel.contents.stages;

import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.stage.IStage;

public abstract class StageAbstract implements IStage
{

	protected PhaseBattle phase;
	protected int age;
	protected int A;
	private boolean finished = false;
	private int norm;
	private int shootdown;

	@Override
	public void init(PhaseBattle phase)
	{
		this.phase = phase;
		age = 0;
	}

	@Override
	public void move()
	{
		A = 20;

		registerField();

		if (age >= A) {
			finished = true;
		}

		age++;
	}

	protected abstract void registerField();

	protected void addNorm()
	{
		norm++;
	}

	protected void addShootdown()
	{
		shootdown++;
	}

	@Override
	public boolean isClearing()
	{
		return phase.enemies.size() == 0 && finished && (shootdown >= norm);
	}

	@Override
	public boolean isTimeouting()
	{
		return phase.enemies.size() == 0 && finished && (shootdown < norm);
	}

}
