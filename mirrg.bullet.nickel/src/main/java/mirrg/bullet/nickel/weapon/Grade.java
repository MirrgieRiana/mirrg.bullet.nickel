package mirrg.bullet.nickel.weapon;

import java.util.ArrayList;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class Grade
{

	public String label;
	public ArrayList<IBattery> batteries = new ArrayList<>();

	public Grade(String label)
	{
		this.label = label;
	}

	public Grade add(IBattery battery)
	{
		batteries.add(battery);
		return this;
	}

	public void move(ILiving living, PhaseBattle phase, boolean isFireable, boolean isPlayer)
	{
		for (IBattery battery : batteries) {
			battery.move(living, phase, isFireable, isPlayer);
		}
	}

	public double getDamagePerSecond(boolean isScatter)
	{
		double t = 0;
		for (IBattery battery : batteries) {
			if (isScatter) {
				t += battery.getDamagePerSecond();
			} else {
				t += battery.getDamagePerSecond() * 7 / Math.max(battery.getRangeAngle(), 7);
			}
		}
		return t;
	}

	public int getTierAdditional()
	{
		int t = 0;
		for (IBattery battery : batteries) {
			t = Math.max(t, battery.getTierAdditional());
		}
		return t;
	}

}
