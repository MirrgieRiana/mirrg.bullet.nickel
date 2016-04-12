package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.IWeapon;
import mirrg.bullet.nickel.weapon.TypeWeapon;
import mirrg.bullet.nickel.weapon.WeaponGraded;

public abstract class TypeWeaponSpikes extends TypeWeapon
{

	public TypeWeaponSpikes(IItem item)
	{
		super(grade -> new WeaponGraded(item, grade, "スパイク"), item);
	}

	protected BatteryAbstract getBulletBase(boolean isPlayer)
	{
		BatteryAbstract battery = new BatteryBullets()
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

	public static TypeWeapon grass = new TypeWeaponSpikes(Items.grass) {
		@Override
		public IWeapon create(String n, boolean isPlayer)
		{
			BatteryAbstract base = getBulletBase(isPlayer);

			if (n.equals("L")) return c(g(n).add(bat(base).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(BULLETS, 3)));
			if (n.equals("M")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.8).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 2.0).set(BULLETS, 4)));
			if (n.equals("H")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.6).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 5.0).set(BULLETS, 5)));
			if (n.equals("V")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.5).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 10.0).set(BULLETS, 7)));

			throw new IllegalArgumentException("Undefined Grade Name: " + n);
		}
	};

}
