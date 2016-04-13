package mirrg.bullet.nickel.weapon.instance;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public interface IBattery
{

	public void move(ILiving living, PhaseBattle phase, boolean isFireable, boolean isPlayer);

}
