package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.CardBatteryBullets.*;

import java.util.ArrayList;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public abstract class SupplierCardWeaponEmitter extends SupplierCardWeaponAbstract
{

	public SupplierCardWeaponEmitter(IItem item)
	{
		super(item, "エミッタ");
	}

	protected CardBatteryAbstract getBulletBase(boolean isPlayer)
	{
		CardBatteryAbstract battery = new CardBatteryBullets()
			.set(COLOR, item.getColor()).set(SPAN, 16).set(SIZE, 0.004).set(SPEED, 0.006)
			.set(BULLETS, 3).set(DELAY_PER_BULLET, 3)
			.set(WAYS, 12).set(ANGLE_PER_WAY, 360.0 / 12)
			.set(ANGLE_PER_TIME, 7.2);
		if (isPlayer) {
			battery
				.map(COLOR, a -> paler(a))
				.set(SPAN, 10)
				.set(DAMAGE, 4)
				.map(SIZE, a -> a * 1.25)
				.map(SPEED, a -> a * 5);
		}
		return battery;
	}

	public static SupplierCardWeaponEmitter stone = new SupplierCardWeaponEmitter(Items.stone) {
		private CardBatteryAbstract a(CardBatteryAbstract b, double span, double speed, double damage, double ways, double anglePerWay)
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
			CardBatteryAbstract base = getBulletBase(isPlayer);

			cards.add(w("L").add(b(base).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(BULLETS, 3).set(WAYS, 12).set(ANGLE_PER_WAY, 360.0 / 12).map(ANGLE_PER_TIME,
				a -> a * 1.0)));
			cards.add(w("M").add(b(base).map(SPAN, a -> a * 0.8).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 2.0).set(BULLETS, 4).set(WAYS, 15).set(ANGLE_PER_WAY, 360.0 / 15).map(ANGLE_PER_TIME,
				a -> a * 0.8)));
			cards.add(w("H").add(b(base).map(SPAN, a -> a * 0.6).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 5.0).set(BULLETS, 5).set(WAYS, 20).set(ANGLE_PER_WAY, 360.0 / 20).map(ANGLE_PER_TIME,
				a -> a * 0.6)));
			cards.add(w("V").add(b(base).map(SPAN, a -> a * 0.5).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 10.0).set(BULLETS, 7).set(WAYS, 30).set(ANGLE_PER_WAY, 360.0 / 30).map(ANGLE_PER_TIME,
				a -> a * 0.5)));

			return cards;
		}
	};

}
