package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import java.util.ArrayList;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public abstract class SupplierCardWeaponSpark extends SupplierCardWeaponAbstract
{

	public SupplierCardWeaponSpark(IItem item)
	{
		super(item, "スパーク", "spark");
	}

	protected CardBatteryAbstract getBulletBase(boolean isPlayer)
	{
		CardBatteryAbstract battery = new CardBatteryBullets()
			.set(COLOR, item.getColor()).set(SPAN, 3).set(SIZE, 0.004).set(SPEED, 0.006)
			.set(NOIZ_SPEED_RATE, 0.05).set(NOIZ_ANGLE, 180).set(WAYS, 2);
		if (isPlayer) {
			battery
				.set(SPAN, 2)
				.map(COLOR, a -> paler(a))
				.set(DAMAGE, 10)
				.map(SIZE, a -> a * 1.25)
				.map(SPEED, a -> a * 5);
		}
		return battery;
	}

	public static SupplierCardWeaponSpark dirt = new SupplierCardWeaponSpark(Items.dirt) {
		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract base = getBulletBase(isPlayer);

			cards.add(w("L").add(b(base).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(WAYS, 2)));
			cards.add(w("M").add(b(base).map(SPAN, a -> a * 0.8).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 2.0).set(WAYS, 5)));
			cards.add(w("H").add(b(base).map(SPAN, a -> a * 0.6).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 5.0).set(WAYS, 11)));
			cards.add(w("V").add(b(base).map(SPAN, a -> a * 0.5).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 10.0).set(WAYS, 15)));

			return cards;
		}
	};

	public static SupplierCardWeaponSpark grass = new SupplierCardWeaponSpark(Items.grass) {
		private CardBatteryAbstract createCardBattery(
			boolean isPlayer,
			double span,
			double speed,
			double damage,
			double bullets,
			double anglePerTime)
		{
			return getBulletBase(isPlayer)
				.map(SPAN, a -> a * 1 * span)
				.map(SPEED, a -> a * (isPlayer ? 1 : 3) * speed)
				.map(DAMAGE, a -> a * 0.2 * damage)
				.set(WAYS, 1)
				.set(BULLETS, bullets)
				.set(DELAY_PER_BULLET, anglePerTime)
				.set(NOIZ_SPEED_RATE, 0)
				.set(NOIZ_ANGLE, 180);
		}

		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();

			cards.add(w("L").add(createCardBattery(isPlayer, 1.0, 1.0, 1.0, 15, 0.75)));
			cards.add(w("M").add(createCardBattery(isPlayer, 0.7, 1.2, 4.0, 20, 0.65)));
			cards.add(w("H").add(createCardBattery(isPlayer, 0.4, 1.5, 15.0, 25, 0.55)));
			cards.add(w("V").add(createCardBattery(isPlayer, 0.2, 1.8, 50.0, 30, 0.45)));

			return cards;
		}
	};

}
