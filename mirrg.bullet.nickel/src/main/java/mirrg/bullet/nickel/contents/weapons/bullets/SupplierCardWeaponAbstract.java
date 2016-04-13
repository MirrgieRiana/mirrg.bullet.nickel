package mirrg.bullet.nickel.contents.weapons.bullets;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;

import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.phases.PhaseDebug;
import mirrg.bullet.nickel.weapon.card.CardBatteryAbstract;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public abstract class SupplierCardWeaponAbstract
{

	protected IItem item;
	private String nameWeapon;
	private Hashtable<String, CardWeapon> cardWeaponsEnemy = new Hashtable<>();
	private Hashtable<String, CardWeapon> cardWeaponsPlayer = new Hashtable<>();

	public SupplierCardWeaponAbstract(IItem item, String nameWeapon)
	{
		this.item = item;
		this.nameWeapon = nameWeapon;

		init();
	}

	private int counter = -1;

	private void init()
	{
		if (counter != PhaseDebug.counter) {
			counter = PhaseDebug.counter;

			for (CardWeapon cardWeapon : createCardWeapons(false)) {
				cardWeaponsEnemy.put(cardWeapon.getNameGrade(), cardWeapon);
			}
			for (CardWeapon cardWeapon : createCardWeapons(true)) {
				cardWeaponsPlayer.put(cardWeapon.getNameGrade(), cardWeapon);
			}

		}
	}

	public CardWeapon get(String nameGrade, Boolean isPlayer)
	{
		init();

		CardWeapon cardWeapon = (isPlayer ? cardWeaponsPlayer : cardWeaponsEnemy).get(nameGrade);
		if (cardWeapon != null) return cardWeapon;
		throw new IllegalArgumentException("Undefined Grade Name: " + nameGrade);
	}

	//

	protected Color paler(Color color)
	{
		return new Color(
			color.getRed(),
			color.getGreen(),
			color.getBlue(),
			color.getAlpha() / 2);
	}

	protected abstract CardBatteryAbstract getBulletBase(boolean isPlayer);

	//

	protected CardWeapon w(String nameGrade)
	{
		return new CardWeapon(item, nameGrade, nameWeapon);
	}

	protected CardBatteryBullets b(CardBatteryAbstract... cardBatteries)
	{
		return new CardBatteryBullets(cardBatteries);
	}

	protected abstract ArrayList<CardWeapon> createCardWeapons(Boolean isPlayer);

}
