package mirrg.bullet.nickel;

import mirrg.bullet.nickel.phases.PhaseBattle;

public interface IStage
{

	public void init(PhaseBattle phase);

	public void move(PhaseBattle phase);

	public boolean isFinished();

}
