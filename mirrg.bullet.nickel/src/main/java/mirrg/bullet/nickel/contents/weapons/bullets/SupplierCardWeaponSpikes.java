package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import java.util.ArrayList;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public abstract class SupplierCardWeaponSpikes extends SupplierCardWeaponAbstract
{

	public SupplierCardWeaponSpikes(IItem item)
	{
		super(item, "スパイク");
	}

	protected CardBatteryAbstract getBulletBase(boolean isPlayer)
	{
		CardBatteryAbstract battery = new CardBatteryBullets()
			.set(COLOR, item.getColor()).set(SPAN, 90).set(SIZE, 0.004).set(DAMAGE, 50)
			.set(SEARCH, true).set(SPEED, 0.008).set(DELAY_PER_BULLET, 2);
		if (isPlayer) {
			battery
				.map(COLOR, a -> paler(a))
				.set(SPAN, 3)
				.map(SIZE, a -> a * 1.25)
				.map(DAMAGE, a -> a * 0.05)
				.map(SPEED, a -> a * 5)
				.map(DELAY_PER_BULLET, a -> a * 0.2);
		}
		return battery;
	}

	public static SupplierCardWeaponSpikes grass = new SupplierCardWeaponSpikes(Items.grass) {
		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract base = getBulletBase(isPlayer);

			cards.add(w("L").add(b(base).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(BULLETS, 3)));
			cards.add(w("M").add(b(base).map(SPAN, a -> a * 0.8).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 2.0).set(BULLETS, 4)));
			cards.add(w("H").add(b(base).map(SPAN, a -> a * 0.6).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 5.0).set(BULLETS, 5)));
			cards.add(w("V").add(b(base).map(SPAN, a -> a * 0.5).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 10.0).set(BULLETS, 7)));

			return cards;
		}
	};

}
