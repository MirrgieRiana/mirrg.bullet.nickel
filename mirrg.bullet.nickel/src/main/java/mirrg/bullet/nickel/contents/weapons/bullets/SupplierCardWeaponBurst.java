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
		super(item, "バースト", "burst");
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
		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract b = getBulletBase(isPlayer).map(SIZE, a -> a / 2).map(DAMAGE, a -> a / 3).map(WAYS, a -> a * 2);

			cards.add(w("L").add(b(b).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).map(WAYS, a -> a * 1.0).map(NOIZ_SPEED_RATE, a -> a * 1.0).map(NOIZ_ANGLE, a -> a * 1.0)));
			cards.add(w("M").add(b(b).map(SPEED, a -> a * 1.1).map(DAMAGE, a -> a * 2.2).map(WAYS, a -> a * 1.5).map(NOIZ_SPEED_RATE, a -> a * 1.5).map(NOIZ_ANGLE, a -> a * 1.5)));
			cards.add(w("H").add(b(b).map(SPEED, a -> a * 1.3).map(DAMAGE, a -> a * 5.0).map(WAYS, a -> a * 2.0).map(NOIZ_SPEED_RATE, a -> a * 2.0).map(NOIZ_ANGLE, a -> a * 2.0)));
			cards.add(w("V").add(b(b).map(SPEED, a -> a * 1.6).map(DAMAGE, a -> a * 12.0).map(WAYS, a -> a * 4.0).map(NOIZ_SPEED_RATE, a -> a * 3.0).map(NOIZ_ANGLE, a -> a * 3.0)));

			return cards;
		}
	};

}
