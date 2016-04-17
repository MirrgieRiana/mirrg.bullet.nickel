package mirrg.bullet.nickel.core;

import java.util.HashSet;
import java.util.Optional;

import mirrg.bullet.nickel.contents.CardStages;
import mirrg.bullet.nickel.contents.weapons.bullets.SupplierCardWeaponBullets;
import mirrg.bullet.nickel.item.IStack;
import mirrg.bullet.nickel.item.Inventory;
import mirrg.bullet.nickel.item.StackWeapon;
import mirrg.bullet.nickel.stage.ICardStage;

public class DataNickel
{

	public void putDefault()
	{
		inventory = new Inventory();

		{
			StackWeapon weapon = new StackWeapon(SupplierCardWeaponBullets.nickel.get("P", true));

			inventory.addStack(weapon);
			nameOreWeaponLeft = weapon.getNameOre();
		}

		{
			StackWeapon weapon = new StackWeapon(SupplierCardWeaponBullets.calcite.get("P", true));

			inventory.addStack(weapon);
			nameOreWeaponRight = weapon.getNameOre();
		}

		availableStages.clear();
		availableStages.add(CardStages.landOpening.name);
	}

	private Inventory inventory;

	public Inventory getInventory()
	{
		return inventory;
	}

	private HashSet<String> availableStages = new HashSet<>();

	public void markAvailable(ICardStage cardStage)
	{
		availableStages.add(cardStage.getName());
	}

	public boolean isAvailable(ICardStage cardStage)
	{
		return availableStages.contains(cardStage.getName());
	}

	private Optional<StackWeapon> getWeapon(String nameOre)
	{
		IStack stack = inventory.search(nameOre).orElse(null);
		if (stack instanceof StackWeapon) {
			return Optional.ofNullable((StackWeapon) stack);
		} else {
			return Optional.empty();
		}
	}

	private String nameOreWeaponLeft;

	public Optional<StackWeapon> getWeaponLeft()
	{
		return getWeapon(nameOreWeaponLeft);
	}

	public void setWeaponLeft(Optional<StackWeapon> stack)
	{
		nameOreWeaponLeft = stack.map(IStack::getNameOre).orElse("");
	}

	private String nameOreWeaponRight;

	public Optional<StackWeapon> getWeaponRight()
	{
		return getWeapon(nameOreWeaponRight);
	}

	public void setWeaponRight(Optional<StackWeapon> stack)
	{
		nameOreWeaponRight = stack.map(IStack::getNameOre).orElse("");
	}

}
