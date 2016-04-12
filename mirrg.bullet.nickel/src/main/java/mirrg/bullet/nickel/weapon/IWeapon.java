package mirrg.bullet.nickel.weapon;

import java.awt.Color;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public interface IWeapon
{

	public void move(ILiving living, PhaseBattle phase, boolean isFireable, boolean isPlayer);

	public String getName();

	public default int getDelay()
	{
		return 0;
	}

	public default int getTier()
	{
		double dps = Math.max(getDamagePerSecond(true) * 0.24, getDamagePerSecond(false)) + 1;
		double tier = Math.log(dps / 100) / Math.log(2) * 2 + 4;
		return (int) Math.floor(Math.max(tier, 0));
	}

	public double getDamagePerSecond(boolean isScatter);

	public Color getColor();

}
