package mirrg.bullet.nickel.contents.entities.bullets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

import mirrg.bullet.nickel.entity.IBullet;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class BulletBomb implements IBullet, ILiving
{

	public double x;
	public double y;
	public double r;
	public double rMax;
	public int damageAll;

	public BulletBomb(double x, double y, double rMax, int damageAll)
	{
		this.x = x;
		this.y = y;
		this.r = rMax / 10;
		this.rMax = rMax;
		this.damageAll = damageAll;
	}

	@Override
	public boolean move(PhaseBattle phase)
	{
		r += 0.05;

		for (IBullet bullet : phase.bulletsEnemy) {
			if (PhaseBattle.isColliding(this, bullet)) {
				bullet.damage(1);
			}
		}

		return r > rMax;
	}

	@Override
	public void render(PhaseBattle phase, Graphics2D graphics)
	{
		{
			graphics.setColor(Color.white);
			Stroke stroke = graphics.getStroke();
			graphics.setStroke(new BasicStroke(0.007f));

			graphics.draw(getShape(0));

			graphics.setStroke(stroke);
		}
	}

	@Override
	public Shape getShape(double additionalRadius)
	{
		return new Ellipse2D.Double(
			x - r - additionalRadius,
			y - r - additionalRadius,
			(r + additionalRadius) * 2,
			(r + additionalRadius) * 2);
	}

	@Override
	public void damage(int value)
	{

	}

	@Override
	public double getX()
	{
		return x;
	}

	@Override
	public double getY()
	{
		return y;
	}

	@Override
	public double getRadius()
	{
		return r;
	}

	@Override
	public int getToughness()
	{
		return 1;
	}

	@Override
	public int getAttack()
	{
		return (int) (damageAll / (rMax / 0.05));
	}

}
