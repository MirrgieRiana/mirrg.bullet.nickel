package mirrg.bullet.nickel.contents.weapons.bullets;

import static mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets.*;

import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.IWeapon;
import mirrg.bullet.nickel.weapon.TypeWeapon;
import mirrg.bullet.nickel.weapon.WeaponGraded;

public abstract class TypeWeaponChunk extends TypeWeapon
{

	public TypeWeaponChunk(IItem item)
	{
		super(grade -> new WeaponGraded(item, grade, "チャンク"), item);
	}

	protected BatteryAbstract getBulletBase(boolean isPlayer)
	{
		BatteryAbstract battery = new BatteryBullets()
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

	public static TypeWeapon copper = new TypeWeaponChunk(Items.copper) {
		@Override
		public IWeapon create(String n, boolean isPlayer)
		{
			BatteryAbstract base = getBulletBase(isPlayer);

			if (n.equals("L")) return c(g(n).add(bat(base).map(SPAN, a -> a * 1.0).map(SPEED, a -> a * 1.0).map(DAMAGE, a -> a * 1.0).set(WAYS, 4)));
			if (n.equals("M")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.95).map(SPEED, a -> a * 1.1).map(DAMAGE, a -> a * 8.0).set(WAYS, 5)));
			if (n.equals("H")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.9).map(SPEED, a -> a * 1.2).map(DAMAGE, a -> a * 60.0).set(WAYS, 6)));
			if (n.equals("V")) return c(g(n).add(bat(base).map(SPAN, a -> a * 0.85).map(SPEED, a -> a * 1.4).map(DAMAGE, a -> a * 400.0).set(WAYS, 7)));

			throw new IllegalArgumentException("Undefined Grade Name: " + n);
		}
	};

}
