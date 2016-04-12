package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.IWeapon;
import mirrg.bullet.nickel.weapon.TypeWeapon;
import mirrg.bullet.nickel.weapon.WeaponGraded;

public abstract class TypeWeaponBurst extends TypeWeapon
{

	public TypeWeaponBurst(IItem item)
	{
		super(grade -> new WeaponGraded(item, grade, "バースト"), item);
	}

	protected BatteryAbstract getBulletBase(boolean isPlayer)
	{
		BatteryAbstract battery = new BatteryBullets()
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

	public static TypeWeapon dirt = new TypeWeaponBurst(Items.dirt) {
		@Override
		public IWeapon create(String n, boolean isPlayer)
		{
			BatteryAbstract b = getBulletBase(isPlayer).map(SIZE, a -> a / 2).map(DAMAGE, a -> a / 3).map(WAYS, a -> a * 2);

			if (n.equals("L")) return c(g(n).add(bat(b).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).map(WAYS, a -> a * 1.0).map(NOIZ_SPEED_RATE, a -> a * 1.0).map(NOIZ_ANGLE, a -> a * 1.0)));
			if (n.equals("M")) return c(g(n).add(bat(b).map(SPEED, a -> a * 1.1).map(DAMAGE, a -> a * 2.2).map(WAYS, a -> a * 1.5).map(NOIZ_SPEED_RATE, a -> a * 1.5).map(NOIZ_ANGLE, a -> a * 1.5)));
			if (n.equals("H")) return c(g(n).add(bat(b).map(SPEED, a -> a * 1.3).map(DAMAGE, a -> a * 5.0).map(WAYS, a -> a * 2.0).map(NOIZ_SPEED_RATE, a -> a * 2.0).map(NOIZ_ANGLE, a -> a * 2.0)));
			if (n.equals("V")) return c(g(n).add(bat(b).map(SPEED, a -> a * 1.6).map(DAMAGE, a -> a * 12.0).map(WAYS, a -> a * 4.0).map(NOIZ_SPEED_RATE, a -> a * 3.0).map(NOIZ_ANGLE, a -> a * 3.0)));

			throw new IllegalArgumentException("Undefined Grade Name: " + n);
		}
	};

}
