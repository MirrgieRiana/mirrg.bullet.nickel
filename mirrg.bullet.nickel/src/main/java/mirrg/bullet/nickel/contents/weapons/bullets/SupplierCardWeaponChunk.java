package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import java.util.ArrayList;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public abstract class SupplierCardWeaponChunk extends SupplierCardWeaponAbstract
{

	public SupplierCardWeaponChunk(IItem item)
	{
		super(item, "チャンク", "chunk");
	}

	protected CardBatteryAbstract getBulletBase(boolean isPlayer)
	{
		CardBatteryAbstract battery = new CardBatteryBullets()
			.set(COLOR, item.getColor()).set(SPAN, 120).set(DAMAGE, 50).set(SEARCH, true)
			.set(SPEED, 0.003).set(NOIZ_SPEED_RATE, 0.03).set(NOIZ_ANGLE, 3)
			.set(WAYS, 4).set(SPEED_RATE_PER_BULLET, 0.05);
		if (isPlayer) {
			battery
				.map(COLOR, a -> paler(a))
				.set(SPAN, 6)
				.map(SIZE, a -> a * 1.25)
				.map(DAMAGE, a -> a * 0.1)
				.set(SEARCH, false)
				.map(SPEED, a -> a * 4);
		}
		return battery;
	}

	public static SupplierCardWeaponChunk copper = new SupplierCardWeaponChunk(Items.copper) {
		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract base = getBulletBase(isPlayer);

			cards.add(w("L").add(b(base).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(WAYS, 4)));
			cards.add(w("M").add(b(base).map(SPAN, a -> a * 0.95).map(SPEED, a -> a * 1.1).map(DAMAGE, a -> a * 8.0).set(WAYS, 5)));
			cards.add(w("H").add(b(base).map(SPAN, a -> a * 0.9).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 60.0).set(WAYS, 6)));
			cards.add(w("V").add(b(base).map(SPAN, a -> a * 0.85).map(SPEED, a -> a * 1.4).map(DAMAGE, a -> a * 400.0).set(WAYS, 7)));

			return cards;
		}
	};

}
