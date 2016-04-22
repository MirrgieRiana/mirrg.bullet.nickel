package mirrg.bullet.nickel.contents;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

	public static final Class<?>[] classes = {
		SupplierCardWeaponBullets.class,
		SupplierCardWeaponEmitter.class,
		SupplierCardWeaponSpark.class,
		SupplierCardWeaponBurst.class,
		SupplierCardWeaponChunk.class,
		SupplierCardWeaponSpikes.class,
	};

	public static ArrayList<SupplierCardWeaponAbstract> getSuppliers()
	{
		ArrayList<SupplierCardWeaponAbstract> array = new ArrayList<>();

		for (Class<?> clazz : classes) {
			for (Field field : clazz.getFields()) {
				try {
					array.add((SupplierCardWeaponAbstract) field.get(null));
				} catch (NullPointerException | IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException("Illegal field to load SupplierCardWeapon: " + clazz.getName() + "." + field.getName());
				}
			}
		}

		return array;
	}

	public static Optional<CardWeapon> get(String nameOre)
	{
		ArrayList<SupplierCardWeaponAbstract> supplierCardWeapons = getSuppliers();

		for (SupplierCardWeaponAbstract supplier : supplierCardWeapons) {
			for (Entry<String, CardWeapon> entry : supplier.getCardWeapons(true).entrySet()) {
				if (entry.getValue().getNameOre().equals(nameOre)) return Optional.of(entry.getValue());
			}
		}

		return Optional.empty();
	}

}
