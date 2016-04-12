package mirrg.bullet.nickel.weapon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.function.Function;

import mirrg.bullet.nickel.contents.weapons.bullets.BatteryBullets;
import mirrg.bullet.nickel.item.IItem;

public abstract class TypeWeapon
{

	protected Function<Grade, IWeapon> creator;
	protected IItem item;
	protected ArrayList<String> namesGrade = new ArrayList<>();
	protected Hashtable<String, Grade> grades = new Hashtable<>();

	public TypeWeapon(Function<Grade, IWeapon> creator, IItem item)
	{
		this.creator = creator;
		this.item = item;
	}

	public TypeWeapon register(Grade grade)
	{
		namesGrade.add(grade.label);
		grades.put(grade.label, grade);
		return this;
	}

	public abstract IWeapon create(String nameGrade, boolean isPlayer);

	//

	protected Color paler(Color color)
	{
		return new Color(
			color.getRed(),
			color.getGreen(),
			color.getBlue(),
			color.getAlpha() / 2);
	}

	protected IWeapon c(Grade grade)
	{
		return creator.apply(grade);
	}

	protected BatteryAbstract bat(BatteryAbstract... base)
	{
		return new BatteryBullets(base);
	}

	protected Grade g(String label)
	{
		return new Grade(label);
	}

}
