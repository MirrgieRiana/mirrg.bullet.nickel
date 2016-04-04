package mirrg.bullet.nickel.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

import mirrg.bullet.nickel.Game;
import mirrg.bullet.nickel.entity.IBullet;
import mirrg.bullet.nickel.entity.ILiving;

public class BulletBomb implements IBullet, ILiving
{

	public double x;
	public double y;
	public double r;
	public double rMax;

	public BulletBomb(double x, double y, double rMax)
	{
		this.x = x;
		this.y = y;
		this.r = rMax / 10;
		this.rMax = rMax;
	}

	@Override
	public boolean move(Game game)
	{
		r += 0.05;

		for (IBullet bullet : game.bulletsEnemy) {
			if (Game.isColliding(this, bullet)) {
				bullet.damage();
			}
		}

		return r > rMax;
	}

	@Override
	public void render(Game game, Graphics2D graphics)
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
	public void damage()
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

}
