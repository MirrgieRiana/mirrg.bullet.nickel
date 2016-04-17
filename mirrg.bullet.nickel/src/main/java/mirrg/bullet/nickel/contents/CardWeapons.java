package mirrg.bullet.nickel.contents;

import java.util.Map.Entry;
import java.util.Optional;

import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponAbstract;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBullets;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBurst;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponChunk;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponEmitter;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponSpark;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponSpikes;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public class CardWeapons
{

	public static final SupplierCardWeaponAbstract[] suppliers = {
		SupplierCardWeaponBullets.calcite,
		SupplierCardWeaponBullets.nickel,
		SupplierCardWeaponBullets.stone,
		SupplierCardWeaponEmitter.copper,
		SupplierCardWeaponEmitter.stone,
		SupplierCardWeaponSpark.dirt,
		SupplierCardWeaponSpark.grass,
		SupplierCardWeaponBurst.dirt,
		SupplierCardWeaponChunk.copper,
		SupplierCardWeaponSpikes.grass,
	};

	public static Optional<CardWeapon> get(String nameOre)
	{
		for (SupplierCardWeaponAbstract supplier : suppliers) {
			for (Entry<String, CardWeapon> entry : supplier.getCardWeapons(true).entrySet()) {
				if (entry.getValue().getNameOre().equals(nameOre)) return Optional.of(entry.getValue());
			}
		}

		return Optional.empty();
	}

}
