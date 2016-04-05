package mirrg.bullet.nickel;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public interface IWeapon
{

	public void fireEnemy(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase);

	public int getSpanEnemy();

	public void firePlayer(InstanceWeapon instanceWeapon, ILiving living, PhaseBattle phase);

	public int getSpanPlayer();

	public String getName();

	public default int getDelay()
	{
		return 0;
	}

}
