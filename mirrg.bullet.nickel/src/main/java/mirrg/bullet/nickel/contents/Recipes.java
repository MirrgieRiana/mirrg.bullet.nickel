package mirrg.bullet.nickel.contents;

import java.util.function.Supplier;
import java.util.stream.Stream;

import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBullets;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBurst;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponChunk;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponEmitter;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponSpark;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponSpikes;
import mirrg.bullet.nickel.item.Recipe;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.item.StackWeapon;

public class Recipes
{

	public final static Recipe[] recipesShop = {
		new Recipe()
			.addIn(new StackItem(Items.money, 100), false)
			.addOut(() -> new StackWeapon(SupplierCardWeaponBullets.nickel.get("P", true))),
		new Recipe()
			.addIn(new StackItem(Items.money, 100), false)
			.addOut(() -> new StackWeapon(SupplierCardWeaponBullets.calcite.get("P", true))),
		new Recipe()
			.addIn(new StackItem(Items.money, 100), false)
			.addOut(() -> new StackItem(Items.fuel, 1)),
	};

	private static Recipe addCasingBullets(Recipe recipe, Supplier<StackWeapon> out)
	{
		StackWeapon stackWeapon = out.get();

		switch (stackWeapon.item.getTier()) {
			case 0:
				recipe.addIn(new StackItem(Items.stone, 1), false);
				break;
			case 1:
				recipe.addIn(new StackItem(Items.stone, 3), false);
				break;
			case 2:
				recipe.addIn(new StackItem(Items.stone, 5), false);
				break;
			case 3:
				recipe.addIn(new StackItem(Items.copper, 1), false); // key point
				recipe.addIn(new StackItem(Items.stone, 10), false);
				break;
			case 4:
				recipe.addIn(new StackItem(Items.copper, 3), false);
				recipe.addIn(new StackItem(Items.stone, 20), false);
				break;
			case 5:
				recipe.addIn(new StackItem(Items.copper, 5), false);
				recipe.addIn(new StackItem(Items.stone, 15), false);
				break;
			case 6:
				recipe.addIn(new StackItem(Items.iron, 1), false); // key point
				recipe.addIn(new StackItem(Items.copper, 10), false);
				recipe.addIn(new StackItem(Items.stone, 10), false);
				break;
			case 7:
				recipe.addIn(new StackItem(Items.iron, 3), false);
				recipe.addIn(new StackItem(Items.copper, 20), false);
				recipe.addIn(new StackItem(Items.stone, 5), false);
				break;
			case 8:
				recipe.addIn(new StackItem(Items.iron, 5), false);
				recipe.addIn(new StackItem(Items.copper, 15), false);
				break;
			case 9:
				recipe.addIn(new StackItem(Items.bronze, 1), false); // key point
				recipe.addIn(new StackItem(Items.iron, 10), false);
				recipe.addIn(new StackItem(Items.copper, 10), false);
				break;
			case 10:
				recipe.addIn(new StackItem(Items.bronze, 3), false);
				recipe.addIn(new StackItem(Items.iron, 20), false);
				recipe.addIn(new StackItem(Items.copper, 5), false);
				break;
			case 11:
				recipe.addIn(new StackItem(Items.bronze, 5), false);
				recipe.addIn(new StackItem(Items.iron, 15), false);
				break;
			case 12:
				recipe.addIn(new StackItem(Items.nickel, 1), false); // key point
				recipe.addIn(new StackItem(Items.bronze, 10), false);
				recipe.addIn(new StackItem(Items.iron, 10), false);
				break;
			case 13:
				recipe.addIn(new StackItem(Items.nickel, 3), false);
				recipe.addIn(new StackItem(Items.bronze, 20), false);
				recipe.addIn(new StackItem(Items.iron, 5), false);
				break;
			default:
				recipe.addIn(new StackItem(Items.nickel, 5), false);
				recipe.addIn(new StackItem(Items.bronze, 15), false);
				break;
		}

		recipe.addOut(out);
		return recipe;
	}

	public final static Recipe[] recipesMake = Stream.of(new Recipe[] {

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.calcite, 1), true),
			() -> new StackWeapon(SupplierCardWeaponBullets.calcite.get("P", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.nickel, 1), true),
			() -> new StackWeapon(SupplierCardWeaponBullets.nickel.get("P", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.stone, 5), true),
			() -> new StackWeapon(SupplierCardWeaponBullets.stone.get("L", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.calcite, 5), true),
			() -> new StackWeapon(SupplierCardWeaponBullets.calcite.get("L", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.nickel, 5), true),
			() -> new StackWeapon(SupplierCardWeaponBullets.nickel.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.dirt, 8), true),
			() -> new StackWeapon(SupplierCardWeaponBurst.dirt.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.copper, 12), true),
			() -> new StackWeapon(SupplierCardWeaponChunk.copper.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.grass, 20), true),
			() -> new StackWeapon(SupplierCardWeaponSpikes.grass.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.dirt, 30), true),
			() -> new StackWeapon(SupplierCardWeaponSpark.dirt.get("L", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.grass, 30), true),
			() -> new StackWeapon(SupplierCardWeaponSpark.grass.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.stone, 35), true),
			() -> new StackWeapon(SupplierCardWeaponEmitter.stone.get("L", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.copper, 35), true),
			() -> new StackWeapon(SupplierCardWeaponEmitter.copper.get("L", true))),

	})
		.sorted((a, b) -> {
			double c = ((StackWeapon) a.out.get(0).get()).item.getTier()
				- ((StackWeapon) b.out.get(0).get()).item.getTier();
			if (c != 0) return (int) Math.signum(c);
			c = ((StackWeapon) a.out.get(0).get()).item.getDamagePerSecond(true)
				- ((StackWeapon) b.out.get(0).get()).item.getDamagePerSecond(true);
			return (int) Math.signum(c);
		})
		.toArray(Recipe[]::new);

	public final static Recipe[] recipesUpgrade = {

	};

	public final static Recipe[] recipesCombine = {
		new Recipe()
			.addIn(new StackItem(Items.copper, 3), true)
			.addIn(new StackItem(Items.tin, 1), true)
			.addOut(() -> new StackItem(Items.bronze, 4)),
		new Recipe()
			.addIn(new StackItem(Items.stone, 20), true)
			.addIn(new StackItem(Items.clay, 5), true)
			.addOut(() -> new StackItem(Items.furnace, 1)),
	};

	public final static Recipe[] recipesRefine = {
		new Recipe()
			.addIn(new StackItem(Items.wood, 1), true)
			.addIn(new StackItem(Items.furnace, 1), false)
			.addOut(() -> new StackItem(Items.fuel, 2))
			.addOut(() -> new StackItem(Items.furnace, 1)),
		new Recipe()
			.addIn(new StackItem(Items.sand, 1), true)
			.addIn(new StackItem(Items.fuel, 1), false)
			.addIn(new StackItem(Items.furnace, 1), false)
			.addOut(() -> new StackItem(Items.glass, 1))
			.addOut(() -> new StackItem(Items.furnace, 1)),
		new Recipe()
			.addIn(new StackItem(Items.clay, 1), true)
			.addIn(new StackItem(Items.fuel, 1), false)
			.addIn(new StackItem(Items.furnace, 1), false)
			.addOut(() -> new StackItem(Items.brick, 1))
			.addOut(() -> new StackItem(Items.furnace, 1)),
	};

	public final static Recipe[] recipesDebug = Stream.of(Items.values())
		.map(item -> new Recipe().addOut(() -> new StackItem(item, 5)))
		.toArray(Recipe[]::new);

}
