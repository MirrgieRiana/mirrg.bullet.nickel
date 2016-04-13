package mirrg.bullet.nickel.weapon.card;

import mirrg.bullet.nickel.weapon.instance.IBattery;

public interface ICardBattery
{

	public double getDamagePerSecond();

	public double getRangeAngle();

	public int getTierAdditional();

	public IBattery createBattery();

}
