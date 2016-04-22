package mirrg.bullet.nickel.contents;

import java.util.Hashtable;
import java.util.stream.Stream;

import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponAbstract;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBullets;
import mirrg.bullet.nickel.item.Category;
import mirrg.bullet.nickel.item.IItem;
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

	private static Hashtable<Category, IItem[]> tableKeyItem = new Hashtable<>();

	static {
		tableKeyItem.put(Category.fire, new IItem[] {
			Items.copper, // Tier2~
			Items.iron, // Tier5~
			Items.bronze, // Tier8~
		});
		tableKeyItem.put(Category.aqua, new IItem[] {
			Items.calcite, // Tier2~
		});
		tableKeyItem.put(Category.air, new IItem[] {
			Items.grass, // Tier2~
			Items.grass, // Tier5~
			Items.grass, // Tier8~
		});
		tableKeyItem.put(Category.earth, new IItem[] {
			Items.dirt, // Tier2~
			Items.stone, // Tier5~
			Items.sand, // Tier8~
			Items.brick, // Tier11~
		});
	}

	private static Hashtable<String, Integer> tableCostAddition = new Hashtable<>();

	static {
		tableCostAddition.put("P", 1);
		tableCostAddition.put("L", 5);
		tableCostAddition.put("M", 10);
		tableCostAddition.put("H", 20);
		tableCostAddition.put("V", 40);
		tableCostAddition.put("U", 60);
		tableCostAddition.put("S", 100);
		tableCostAddition.put("X", 200);
	}

	private static int[] costs = {
		1, 5, 19, 19, 15, 1,
	};

	private static Recipe createRecipeWeapon(int costBase, SupplierCardWeaponAbstract supplierCardWeapon, String nameGrade)
	{
		Category category = supplierCardWeapon.getItem().getCategory();

		int tier = supplierCardWeapon.get(nameGrade, true).getTier();
		IItem[] items = tableKeyItem.get(category);

		Recipe recipe = new Recipe();

		recipe.addIn(new StackItem(supplierCardWeapon.getItem(), tableCostAddition.get(nameGrade) + costBase), false);
		{
			int index = (tier + 4) / 3 - 2;
			if (items.length > index) {
				if (index >= 0) {
					recipe.addIn(new StackItem(items[index], costs[(tier + 1) % 3]), false);
				}
			}
		}
		{
			int index = (tier + 1) / 3 - 2;
			if (items.length > index) {
				if (index >= 0) {
					recipe.addIn(new StackItem(items[index], costs[(tier + 1) % 3 + 3]), false);
				}
			}
		}

		recipe.addOut(() -> new StackWeapon(supplierCardWeapon.get(nameGrade, true)));
		return recipe;
	}

	public final static Recipe[] recipesMake = CardWeapons.getSuppliers().stream()
		.flatMap(supplier -> supplier.getCardWeapons(true).entrySet().stream()
			.map(entry -> createRecipeWeapon(supplier.getCost(), supplier, entry.getKey())))
		.sorted((a, b) -> {
			double c = ((StackWeapon) a.out.get(0).get()).item.getTier()
				- ((StackWeapon) b.out.get(0).get()).item
					.getTier();
			if (c != 0) return (int) Math.signum(c);
			c = ((StackWeapon) a.out.get(0).get()).item.getDamagePerSecond(true) - ((StackWeapon) b.out.get(0).get()).item.getDamagePerSecond(true);
			return (int) Math.signum(c);
		}).toArray(Recipe[]::new);

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
		new Recipe()
			.addIn(new StackItem(Items.oil, 1), true)
			.addOut(() -> new StackItem(Items.fuel, 5)),
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
