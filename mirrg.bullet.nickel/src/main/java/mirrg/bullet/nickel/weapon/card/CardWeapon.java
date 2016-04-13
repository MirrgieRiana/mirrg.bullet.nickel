package mirrg.bullet.nickel.weapon.card;

import java.awt.Color;
import java.util.ArrayList;

import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.instance.IWeapon;
import mirrg.bullet.nickel.weapon.instance.WeaponBatteries;

public class CardWeapon
{

	private ArrayList<ICardBattery> cardBatteries = new ArrayList<>();
	private IItem item;
	private String nameGrade;
	private String nameWeaponType;

	public CardWeapon(IItem item, String nameGrade, String nameWeaponType)
	{
		this.item = item;
		this.nameGrade = nameGrade;
		this.nameWeaponType = nameWeaponType;
	}

	public CardWeapon add(ICardBattery battery)
	{
		cardBatteries.add(battery);
		return this;
	}

	public String getName()
	{
		return item.getName() + nameGrade + nameWeaponType;
	}

	public String getNameGrade()
	{
		return nameGrade;
	}

	public Color getColor()
	{
		return item.getColor();
	}

	public int getTier()
	{
		double dps = Math.max(getDamagePerSecond(true) * 0.24, getDamagePerSecond(false)) + 1;
		double tier = Math.log(dps / 100) / Math.log(2) * 2 + 4;
		return (int) Math.floor(Math.max(tier, 0)) + getTierAdditional();
	}

	public double getDamagePerSecond(boolean isScatter)
	{
		double t = 0;
		for (ICardBattery cardBattery : cardBatteries) {
			if (isScatter) {
				t += cardBattery.getDamagePerSecond();
			} else {
				t += cardBattery.getDamagePerSecond() * 7 / Math.max(cardBattery.getRangeAngle(), 7);
			}
		}
		return t;
	}

	public int getTierAdditional()
	{
		int t = 0;
		for (ICardBattery cardBattery : cardBatteries) {
			t = Math.max(t, cardBattery.getTierAdditional());
		}
		return t;
	}

	public IWeapon createWeapon()
	{
		WeaponBatteries weapon = new WeaponBatteries();
		for (ICardBattery cardBattery : cardBatteries) {
			weapon.add(cardBattery.createBattery());
		}
		return weapon;
	}

}
