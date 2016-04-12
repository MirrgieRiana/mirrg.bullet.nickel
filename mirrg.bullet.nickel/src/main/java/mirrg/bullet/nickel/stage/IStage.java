package mirrg.bullet.nickel.stage;

import mirrg.bullet.nickel.phases.PhaseBattle;

public interface IStage
{

	public void init(PhaseBattle phase);

	public void move();

	public boolean isClearing();

	public boolean isTimeouting();

}
