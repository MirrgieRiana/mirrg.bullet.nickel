package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import java.util.ArrayList;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public abstract class SupplierCardWeaponBurst extends SupplierCardWeaponAbstract
{

	public SupplierCardWeaponBurst(IItem item)
	{
		super(item, "バースト", "burst", 3);
	}

	protected CardBatteryAbstract getBulletBase(boolean isPlayer)
	{
		CardBatteryAbstract battery = new CardBatteryBullets()
			.set(COLOR, item.getColor()).set(SPAN, 90).set(SIZE, 0.004).set(DAMAGE, 25)
			.set(SEARCH, true).set(SPEED, 0.004).set(NOIZ_SPEED_RATE, 0.1).set(NOIZ_ANGLE, 10)
			.set(WAYS, 4).set(SPEED_RATE_PER_BULLET, 0.05);
		if (isPlayer) {
			battery
				.map(COLOR, a -> paler(a))
				.set(SPAN, 3)
				.map(SIZE, a -> a * 1.25)
				.map(DAMAGE, a -> a * 0.1)
				.set(SEARCH, false)
				.map(SPEED, a -> a * 5);
		}
		return battery;
	}

	public static SupplierCardWeaponBurst dirt = new SupplierCardWeaponBurst(Items.dirt) {
		private CardBatteryAbstract createCardBattery(
			boolean isPlayer,
			double speed,
			double damage,
			double ways,
			double noizSpeedRate,
			double noizAngle)
		{
			return getBulletBase(isPlayer)
				.map(SPEED, a -> a * 0.5 * speed)
				.map(DAMAGE, a -> a / 3 * damage)
				.map(WAYS, a -> a * 2 * ways)
				.map(NOIZ_SPEED_RATE, a -> a * noizSpeedRate)
				.map(NOIZ_ANGLE, a -> a * noizAngle);
		}

		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();

			cards.add(w("L").add(createCardBattery(isPlayer, 1.0, 1.0, 1.0, 1.0, 1.0)));
			cards.add(w("M").add(createCardBattery(isPlayer, 1.1, 2.2, 1.5, 1.5, 1.5)));
			cards.add(w("H").add(createCardBattery(isPlayer, 1.3, 5.0, 2.0, 2.0, 2.0)));
			cards.add(w("V").add(createCardBattery(isPlayer, 1.6, 12.0, 4.0, 3.0, 3.0)));

			return cards;
		}
	};

	public static SupplierCardWeaponBurst sand = new SupplierCardWeaponBurst(Items.sand) {
		private CardBatteryAbstract createCardBattery(
			boolean isPlayer,
			double speed,
			double damage,
			double ways,
			double noizSpeedRate,
			double noizAngle)
		{
			return getBulletBase(isPlayer)
				.map(SIZE, a -> a * 0.75)
				.map(SPEED, a -> a * 2 * speed)
				.map(DAMAGE, a -> a / 4 * damage)
				.map(WAYS, a -> a * 3 * ways)
				.map(NOIZ_SPEED_RATE, a -> a * noizSpeedRate)
				.map(NOIZ_ANGLE, a -> a * noizAngle);
		}

		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();

			cards.add(w("L").add(createCardBattery(isPlayer, 1.0, 1.0, 1.0, 1.0, 1.0)));
			cards.add(w("M").add(createCardBattery(isPlayer, 1.1, 2.2, 1.5, 1.5, 1.5)));
			cards.add(w("H").add(createCardBattery(isPlayer, 1.3, 5.0, 2.0, 2.0, 2.0)));
			cards.add(w("V").add(createCardBattery(isPlayer, 1.6, 12.0, 4.0, 3.0, 3.0)));

			return cards;
		}
	};

}
