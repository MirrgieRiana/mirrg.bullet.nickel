package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import java.util.ArrayList;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public abstract class SupplierCardWeaponBullets extends SupplierCardWeaponAbstract
{

	public SupplierCardWeaponBullets(IItem item)
	{
		super(item, "バレット");
	}

	protected CardBatteryAbstract getBulletBase(boolean isPlayer)
	{
		CardBatteryAbstract battery = new CardBatteryBullets()
			.set(COLOR, item.getColor()).set(SPAN, 90).set(SEARCH, true)
			.set(SPEED, 0.004).set(ANGLE_PER_WAY, 5).set(SPEED_RATE_PER_BULLET, 0.05);
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

	///////////////////////////

	public static SupplierCardWeaponBullets calcite = new SupplierCardWeaponBullets(Items.calcite) {
		private CardBatteryAbstract a(double span, double speed, double damage, double ways, double anglePerWay, CardBatteryAbstract... b)
		{
			return b(b)
				.map(SPAN, a -> a * span)
				.map(SPEED, a -> a * speed)
				.map(DAMAGE, a -> a * damage)
				.set(WAYS, ways)
				.set(ANGLE_PER_WAY, anglePerWay);
		}

		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract b = getBulletBase(isPlayer).map(SPAN, a -> a * 0.8).map(SIZE, a -> a / 2).map(DAMAGE, a -> a / 2);

			cards.add(w("P").add(a(1.5, 0.8, 0.7, 2, 20, b)));
			cards.add(w("L").add(a(1.0, 1.0, 1.0, 3, 15, b)));
			cards.add(w("M").add(a(0.8, 1.2, 2.0, 5, 12, b)));
			cards.add(w("H").add(a(0.6, 1.5, 5.0, 7, 10, b)));
			cards.add(w("V").add(a(0.5, 2.0, 10.0, 9, 8, b)));

			return cards;
		}
	};

	public static SupplierCardWeaponBullets nickel = new SupplierCardWeaponBullets(Items.nickel) {
		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract b = getBulletBase(isPlayer).map(SPEED, a -> a * 2.0).set(ANGLE_PER_WAY, 1).map(DAMAGE, a -> a / 2);

			cards.add(w("P").add(b(b).map(SPAN, a -> a * 1.50).map(SPEED, a -> a * 0.8).map(DAMAGE, a -> a * 0.8).set(WAYS, 1)));
			cards.add(w("L").add(b(b).map(SPAN, a -> a * 1.00).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(WAYS, 2)));
			cards.add(w("M").add(b(b).map(SPAN, a -> a * 0.80).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 1.5).set(WAYS, 4)));
			cards.add(w("H").add(b(b).map(SPAN, a -> a * 0.60).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 2.4).set(WAYS, 6)));
			cards.add(w("V").add(b(b).map(SPAN, a -> a * 0.50).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 4.0).set(WAYS, 8)));
			cards.add(w("U").add(b(b).map(SPAN, a -> a * 0.40).map(SPEED, a -> a * 2.1).map(DAMAGE, a -> a * 7.0).set(WAYS, 10)));
			cards.add(w("S").add(b(b).map(SPAN, a -> a * 0.35).map(SPEED, a -> a * 2.2).map(DAMAGE, a -> a * 10.0).set(WAYS, 12)));
			cards.add(w("X").add(b(b).map(SPAN, a -> a * 0.30).map(SPEED, a -> a * 2.3).map(DAMAGE, a -> a * 25.0).set(WAYS, 14)));

			return cards;
		}
	};

	public static SupplierCardWeaponBullets stone = new SupplierCardWeaponBullets(Items.stone) {
		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract b = getBulletBase(isPlayer).map(DAMAGE, a -> a * 0.24);

			cards.add(w("L").add(b(b).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(WAYS, 2)));
			cards.add(w("M").add(b(b).map(SPAN, a -> a * 0.8).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 2.0).set(WAYS, 3)));
			cards.add(w("H").add(b(b).map(SPAN, a -> a * 0.6).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 5.0).set(WAYS, 4)));
			cards.add(w("V").add(b(b).map(SPAN, a -> a * 0.5).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 10.0).set(WAYS, 5)));

			return cards;
		}
	};

}
