package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.IWeapon;
import mirrg.bullet.nickel.weapon.TypeWeapon;
import mirrg.bullet.nickel.weapon.WeaponGraded;

public abstract class TypeWeaponEmitter extends TypeWeapon
{

	public TypeWeaponEmitter(IItem item)
	{
		super(grade -> new WeaponGraded(item, grade, "エミッタ"), item);
	}

	protected BatteryAbstract getBulletBase(boolean isPlayer)
	{
		BatteryAbstract battery = new BatteryBullets()
			.set(COLOR, item.getColor()).set(SPAN, 3).set(SIZE, 0.004).set(SPEED, 0.006)
			.set(NOIZ_SPEED_RATE, 0.05).set(NOIZ_ANGLE, 180).set(WAYS, 2);
		if (isPlayer) {
			battery
				.set(SPAN, 1)
				.set(DAMAGE, 25)
				.map(COLOR, a -> paler(a))
				.map(DAMAGE, a -> a / 2)
				.map(SIZE, a -> a * 1.25)
				.map(SPEED, a -> a * 5);
		}
		return battery;
	}

	public static TypeWeapon dirt = new TypeWeaponEmitter(Items.dirt) {
		@Override
		public IWeapon create(String n, boolean isPlayer)
		{
			BatteryAbstract base = getBulletBase(isPlayer);

			if (n.equals("L")) return c(g(n).add(bat(base).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(WAYS, 2)));
			if (n.equals("M")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.8).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 2.0).set(WAYS, 5)));
			if (n.equals("H")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.6).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 5.0).set(WAYS, 11)));
			if (n.equals("V")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.5).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 10.0).set(WAYS, 15)));

			throw new IllegalArgumentException("Undefined Grade Name: " + n);
		}
	};

}
