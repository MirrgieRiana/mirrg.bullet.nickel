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

	private static Recipe addCasingBullets(Recipe recipe, Supplier<StackWeapon> out)
	{
		StackWeapon stackWeapon = out.get();

		switch (stackWeapon.item.getTier()) {
			case 0:
				recipe.addIn(new StackItem(Items.stone, 1));
				break;
			case 1:
				recipe.addIn(new StackItem(Items.stone, 5));
				break;
			case 2:
				recipe.addIn(new StackItem(Items.copper, 1));
				recipe.addIn(new StackItem(Items.stone, 4));
				break;
			case 3:
				recipe.addIn(new StackItem(Items.copper, 3));
				recipe.addIn(new StackItem(Items.stone, 2));
				break;
			case 4:
				recipe.addIn(new StackItem(Items.copper, 5));
				recipe.addIn(new StackItem(Items.stone, 1));
				break;
			case 5:
				recipe.addIn(new StackItem(Items.iron, 1));
				recipe.addIn(new StackItem(Items.copper, 4));
				break;
			case 6:
				recipe.addIn(new StackItem(Items.iron, 3));
				recipe.addIn(new StackItem(Items.copper, 2));
				break;
			case 7:
				recipe.addIn(new StackItem(Items.iron, 5));
				recipe.addIn(new StackItem(Items.copper, 1));
				break;
			case 8:
				recipe.addIn(new StackItem(Items.bronze, 1));
				recipe.addIn(new StackItem(Items.iron, 4));
				break;
			case 9:
				recipe.addIn(new StackItem(Items.bronze, 3));
				recipe.addIn(new StackItem(Items.iron, 2));
				break;
			case 10:
				recipe.addIn(new StackItem(Items.bronze, 5));
				recipe.addIn(new StackItem(Items.iron, 1));
				break;
			case 11:
				recipe.addIn(new StackItem(Items.nickel, 1));
				recipe.addIn(new StackItem(Items.bronze, 4));
				break;
			case 12:
				recipe.addIn(new StackItem(Items.nickel, 3));
				recipe.addIn(new StackItem(Items.bronze, 2));
				break;
			case 13:
				recipe.addIn(new StackItem(Items.nickel, 5));
				recipe.addIn(new StackItem(Items.bronze, 1));
				break;
			default:
				recipe.addIn(new StackItem(Items.nickel, 10));
				break;
		}

		recipe.addOut(out);
		return recipe;
	}

	public final static Recipe[] recipesMake = Stream.of(new Recipe[] {

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.calcite, 1)),
			() -> new StackWeapon(SupplierCardWeaponBullets.calcite.get("P", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.nickel, 1)),
			() -> new StackWeapon(SupplierCardWeaponBullets.nickel.get("P", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.stone, 5)),
			() -> new StackWeapon(SupplierCardWeaponBullets.stone.get("L", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.calcite, 5)),
			() -> new StackWeapon(SupplierCardWeaponBullets.calcite.get("L", true))),
		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.nickel, 5)),
			() -> new StackWeapon(SupplierCardWeaponBullets.nickel.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.dirt, 7)),
			() -> new StackWeapon(SupplierCardWeaponBurst.dirt.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.copper, 8)),
			() -> new StackWeapon(SupplierCardWeaponChunk.copper.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.grass, 10)),
			() -> new StackWeapon(SupplierCardWeaponSpikes.grass.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.dirt, 15)),
			() -> new StackWeapon(SupplierCardWeaponSpark.dirt.get("L", true))),

		addCasingBullets(new Recipe()
			.addIn(new StackItem(Items.dirt, 16)),
			() -> new StackWeapon(SupplierCardWeaponEmitter.stone.get("L", true))),

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
			.addIn(new StackItem(Items.copper, 3))
			.addIn(new StackItem(Items.tin, 1))
			.addOut(() -> new StackItem(Items.bronze, 4)),
		new Recipe()
			.addIn(new StackItem(Items.stone, 20))
			.addIn(new StackItem(Items.clay, 5))
			.addOut(() -> new StackItem(Items.furnace, 1)),
	};

	public final static Recipe[] recipesRefine = {
		new Recipe()
			.addIn(new StackItem(Items.wood, 1))
			.addIn(new StackItem(Items.furnace, 1))
			.addOut(() -> new StackItem(Items.fuel, 2))
			.addOut(() -> new StackItem(Items.furnace, 1)),
		new Recipe()
			.addIn(new StackItem(Items.sand, 1))
			.addIn(new StackItem(Items.fuel, 1))
			.addIn(new StackItem(Items.furnace, 1))
			.addOut(() -> new StackItem(Items.glass, 1))
			.addOut(() -> new StackItem(Items.furnace, 1)),
		new Recipe()
			.addIn(new StackItem(Items.clay, 1))
			.addIn(new StackItem(Items.fuel, 1))
			.addIn(new StackItem(Items.furnace, 1))
			.addOut(() -> new StackItem(Items.brick, 1))
			.addOut(() -> new StackItem(Items.furnace, 1)),
	};

	public final static Recipe[] recipesDebug = Stream.of(Items.values())
		.map(item -> new Recipe().addOut(() -> new StackItem(item, 5)))
		.toArray(Recipe[]::new);

}
