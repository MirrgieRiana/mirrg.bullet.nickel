package mirrg.bullet.nickel.weapon;

import java.awt.Color;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.item.IItem;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class WeaponGraded implements IWeapon
{

	protected IItem item;
	protected Grade grade;
	protected String nameWeapon;

	public WeaponGraded(IItem item, Grade grade, String nameWeapon)
	{
		this.item = item;
		this.grade = grade;
		this.nameWeapon = nameWeapon;
	}

	@Override
	public String getName()
	{
		return item.getName() + grade.label + nameWeapon;
	}

	@Override
	public void move(ILiving living, PhaseBattle phase, boolean isFireable, boolean isPlayer)
	{
		grade.move(living, phase, isFireable, isPlayer);
	}

	@Override
	public double getDamagePerSecond(boolean isScatter)
	{
		return grade.getDamagePerSecond(isScatter);
	}

	@Override
	public int getTier()
	{
		return IWeapon.super.getTier() + grade.getTierAdditional();
	}

	@Override
	public Color getColor()
	{
		return item.getColor();
	}

}
