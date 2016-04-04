package mirrg.bullet.nickel.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import mirrg.bullet.nickel.Game;
import mirrg.bullet.nickel.entity.IBullet;

public class BulletStraight implements IBullet
{

	public double x;
	public double y;
	public double xx;
	public double yy;
	public double r;
	public Color color;

	public BulletStraight(double x, double y, double xx, double yy, double r, Color color)
	{
		this.x = x;
		this.y = y;
		this.xx = xx;
		this.yy = yy;
		this.r = r;
		this.color = color;
	}

	@Override
	public boolean move(Game game)
	{
		x += xx;
		y += yy;

		if (x < 0) {
			return true;
		} else if (x > 1) {
			return true;
		} else if (y < 0) {
			return true;
		} else if (y > 1) {
			return true;
		}

		return hp <= 0;
	}

	@Override
	public void render(Game game, Graphics2D graphics)
	{
		graphics.setColor(color);
		graphics.fill(getShape(0));
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

	private int hp = 1;

	@Override
	public void damage()
	{
		hp--;
	}

}
