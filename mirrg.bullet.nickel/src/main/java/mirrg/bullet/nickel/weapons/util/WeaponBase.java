package mirrg.bullet.nickel.weapons.util;

import java.awt.Color;

import mirrg.bullet.nickel.IWeapon;

public abstract class WeaponBase implements IWeapon
{

	protected int grade;
	protected Color colorEnemy;
	protected Color colorPlayer;

	public WeaponBase(int grade, Color color)
	{
		this.grade = grade;
		this.colorEnemy = color;
		this.colorPlayer = new Color(
			color.getRed(),
			color.getGreen(),
			color.getBlue(),
			color.getAlpha() / 2);
	}

}
