package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.IBattery;
import mirrg.bullet.nickel.weapon.IWeapon;
import mirrg.bullet.nickel.weapon.TypeWeapon;
import mirrg.bullet.nickel.weapon.WeaponGraded;

public abstract class TypeWeaponBullets extends TypeWeapon
{

	public TypeWeaponBullets(IItem item)
	{
		super(grade -> new WeaponGraded(item, grade, "バレット"), item);
	}

	protected BatteryAbstract getBulletBase(boolean isPlayer)
	{
		BatteryAbstract battery = new BatteryBullets()
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

	public static TypeWeapon calcite = new TypeWeaponBullets(Items.calcite) {
		private IBattery a(BatteryAbstract b, double span, double speed, double damage, double ways, double anglePerWay)
		{
			return bat(b)
				.map(SPAN, a -> a * span)
				.map(SPEED, a -> a * speed)
				.map(DAMAGE, a -> a * damage)
				.set(WAYS, ways)
				.set(ANGLE_PER_WAY, anglePerWay);
		}

		@Override
		public IWeapon create(String n, boolean isPlayer)
		{
			BatteryAbstract b = getBulletBase(isPlayer).map(SPAN, a -> a * 0.8).map(SIZE, a -> a / 2).map(DAMAGE, a -> a / 2);

			if (n.equals("P")) return c(g(n).add(a(b, 1.5, 0.8, 0.7, 2, 20)));
			if (n.equals("L")) return c(g(n).add(a(b, 1.0, 1.0, 1.0, 3, 15)));
			if (n.equals("M")) return c(g(n).add(a(b, 0.8, 1.2, 2.0, 5, 12)));
			if (n.equals("H")) return c(g(n).add(a(b, 0.6, 1.5, 5.0, 7, 10)));
			if (n.equals("V")) return c(g(n).add(a(b, 0.5, 2.0, 10.0, 9, 8)));

			throw new IllegalArgumentException("Undefined Grade Name: " + n);
		}
	};

	public static TypeWeapon nickel = new TypeWeaponBullets(Items.nickel) {
		@Override
		public IWeapon create(String n, boolean isPlayer)
		{
			BatteryAbstract b = getBulletBase(isPlayer).map(SPEED, a -> a * 2.0).set(ANGLE_PER_WAY, 1).map(DAMAGE, a -> a / 2);

			if (n.equals("P")) return c(g(n).add(bat(b).map(SPAN, a -> a * 1.50).map(SPEED, a -> a * 0.8).map(DAMAGE, a -> a * 0.8).set(WAYS, 1)));
			if (n.equals("L")) return c(g(n).add(bat(b).map(SPAN, a -> a * 1.00).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(WAYS, 2)));
			if (n.equals("M")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.80).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 1.5).set(WAYS, 4)));
			if (n.equals("H")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.60).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 2.4).set(WAYS, 6)));
			if (n.equals("V")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.50).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 4.0).set(WAYS, 8)));
			if (n.equals("U")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.40).map(SPEED, a -> a * 2.1).map(DAMAGE, a -> a * 7.0).set(WAYS, 10)));
			if (n.equals("S")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.35).map(SPEED, a -> a * 2.2).map(DAMAGE, a -> a * 10.0).set(WAYS, 12)));
			if (n.equals("X")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.30).map(SPEED, a -> a * 2.3).map(DAMAGE, a -> a * 25.0).set(WAYS, 14)));

			throw new IllegalArgumentException("Undefined Grade Name: " + n);
		}
	};

	public static TypeWeapon stone = new TypeWeaponBullets(Items.stone) {
		@Override
		public IWeapon create(String n, boolean isPlayer)
		{
			BatteryAbstract b = getBulletBase(isPlayer).map(DAMAGE, a -> a * 0.24);

			if (n.equals("L")) return c(g(n).add(bat(b).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(WAYS, 2)));
			if (n.equals("M")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.8).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 2.0).set(WAYS, 3)));
			if (n.equals("H")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.6).map(SPEED, a -> a * 1.5).map(DAMAGE, a -> a * 5.0).set(WAYS, 4)));
			if (n.equals("V")) return c(g(n).add(bat(b).map(SPAN, a -> a * 0.5).map(SPEED, a -> a * 2.0).map(DAMAGE, a -> a * 10.0).set(WAYS, 5)));

			throw new IllegalArgumentException("Undefined Grade Name: " + n);
		}
	};

}
