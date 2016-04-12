package mirrg.bullet.nickel.weapon;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public interface IBattery
{

	public void move(ILiving living, PhaseBattle phase, boolean isFireable, boolean isPlayer);

	public double getDamagePerSecond();

	public double getRangeAngle();

	public int getTierAdditional();

}
