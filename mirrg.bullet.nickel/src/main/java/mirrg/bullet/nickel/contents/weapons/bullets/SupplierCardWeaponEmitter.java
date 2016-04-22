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
		super(item, "エミッタ", "emitter", 30);
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
		private CardBatteryAbstract createCardBattery(
			CardBatteryAbstract base,
			double span,
			double speed,
			double damage,
			double bullets,
			double ways,
			double anglePerTime)
		{
			return b(base)
				.map(SPAN, a -> a * span)
				.map(SPEED, a -> a * speed)
				.map(DAMAGE, a -> a * damage)
				.set(BULLETS, bullets)
				.set(WAYS, ways)
				.set(ANGLE_PER_WAY, 360.0 / ways)
				.map(ANGLE_PER_TIME, a -> a * anglePerTime);
		}

		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract base = getBulletBase(isPlayer);

			cards.add(w("L").add(createCardBattery(base, 1.0, 1.0, 1.0, 3, 10, 1.0)));
			cards.add(w("M").add(createCardBattery(base, 0.8, 1.2, 2.0, 4, 15, 0.8)));
			cards.add(w("H").add(createCardBattery(base, 0.6, 1.5, 5.0, 5, 20, 0.6)));
			cards.add(w("V").add(createCardBattery(base, 0.5, 2.0, 10.0, 7, 30, 0.5)));

			return cards;
		}
	};

	public static SupplierCardWeaponEmitter copper = new SupplierCardWeaponEmitter(Items.copper) {
		private CardBatteryAbstract createCardBattery(
			CardBatteryAbstract base,
			double span,
			double speed,
			double damage,
			double ways,
			double anglePerTime)
		{
			return b(base)
				.set(SIZE, 0.02)
				.map(SPAN, a -> a * 0.8 * span)
				.map(SPEED, a -> a * 0.75 * speed)
				.map(DAMAGE, a -> a * 3 * damage)
				.set(BULLETS, 1)
				.set(WAYS, ways)
				.set(ANGLE_PER_WAY, 360.0 / ways)
				.map(ANGLE_PER_TIME, a -> a * 1.023 * anglePerTime)
				.set(NOIZ_ANGLE, 5)
				.set(NOIZ_SPEED_RATE, 0.05);
		}

		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract base = getBulletBase(isPlayer);

			cards.add(w("L").add(createCardBattery(base, 1.0, 1.0, 1.0, 10, 1.0)));
			cards.add(w("M").add(createCardBattery(base, 0.8, 1.2, 2.0, 15, 0.8)));
			cards.add(w("H").add(createCardBattery(base, 0.6, 1.5, 5.0, 20, 0.6)));
			cards.add(w("V").add(createCardBattery(base, 0.5, 2.0, 10.0, 25, 0.5)));

			return cards;
		}
	};

	public static SupplierCardWeaponEmitter calcite = new SupplierCardWeaponEmitter(Items.calcite) {
		private CardBatteryAbstract createCardBattery(
			CardBatteryAbstract base,
			double span,
			double speed,
			double damage,
			double ways,
			double anglePerTime)
		{
			return b(base)
				.map(SPAN, a -> a * 0.2 * span)
				.map(SPEED, a -> a * 1.0 * speed)
				.map(DAMAGE, a -> a * 0.6 * damage)
				.set(BULLETS, 1)
				.set(WAYS, ways)
				.set(ANGLE_PER_WAY, 360.0 / ways)
				.map(ANGLE_PER_TIME, a -> a * 1.023 * anglePerTime);
		}

		@Override
		public ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer)
		{
			ArrayList<CardWeapon> cards = new ArrayList<CardWeapon>();
			CardBatteryAbstract base = getBulletBase(isPlayer);

			cards.add(w("L").add(createCardBattery(base, 1.0, 1.0, 1.0, 10, 1.0)));
			cards.add(w("M").add(createCardBattery(base, 0.8, 1.2, 2.0, 15, 0.8)));
			cards.add(w("H").add(createCardBattery(base, 0.6, 1.5, 5.0, 20, 0.6)));
			cards.add(w("V").add(createCardBattery(base, 0.5, 2.0, 10.0, 25, 0.5)));

			return cards;
		}
	};

}
