package mirrg.bullet.nickel.weapon.instance;

import java.util.ArrayList;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class WeaponBatteries implements IWeapon
{

	private ArrayList<IBattery> batteries = new ArrayList<>();

	public WeaponBatteries add(IBattery battery)
	{
		batteries.add(battery);
		return this;
	}

	@Override
	public void move(ILiving living, PhaseBattle phase, boolean isFireable, boolean isPlayer)
	{
		for (IBattery battery : batteries) {
			battery.move(living, phase, isFireable, isPlayer);
		}
	}

}
