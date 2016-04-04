package mirrg.bullet.nickel.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.Game;
import mirrg.bullet.nickel.entity.ILiving;

public abstract class EnemyAbstract implements ILiving
{

	public double x;
	public double y;
	public double xx;
	public double yy;
	public double r;

	public int age;
	public int hp;
	public int hpMax;

	public EnemyAbstract(double x, double y, double xx, double yy, double r, int hp)
	{
		this.x = x;
		this.y = y;
		this.xx = xx;
		this.yy = yy;
		this.r = r;
		this.hp = hp;
		this.hpMax = hp;
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

		age++;

		return hp <= 0;
	}

	@Override
	public void render(Game game, Graphics2D graphics)
	{
		graphics.setColor(Color.blue);
		graphics.fill(getShape(0));
	}

	public void renderHPBar(Game game, Graphics2D graphics)
	{
		double angle = 360 * hp / hpMax;
		{
			graphics.setColor(Color.red);
			Stroke stroke = graphics.getStroke();
			graphics.setStroke(new BasicStroke(0.003f));

			graphics.draw(new Arc2D.Double(x - r, y - r, r * 2, r * 2, 90 + angle, 360 - angle, Arc2D.OPEN));

			graphics.setStroke(stroke);
		}
		{
			graphics.setColor(Color.green);
			Stroke stroke = graphics.getStroke();
			graphics.setStroke(new BasicStroke(0.003f));

			graphics.draw(new Arc2D.Double(x - r, y - r, r * 2, r * 2, 90, angle, Arc2D.OPEN));

			graphics.setStroke(stroke);
		}
	}

	@Override
	public void renderOverlay(Game game, Graphics2D graphics)
	{
		renderMarker(game, graphics);
	}

	public void renderMarker(Game game, Graphics2D graphics)
	{
		double x2 = x * game.panel.getWidth();
		double w2 = r * game.panel.getWidth();
		double y2 = game.panel.getHeight() - 10;
		double h2 = r * 100;

		graphics.setColor(new Color(255, 0, 0, 128));
		graphics.fill(new Rectangle2D.Double(x2 - w2, y2 - 3 - h2 / 2, w2 * 2, h2));
	}

	@Override
	public Shape getShape(double additionalRadius)
	{
		return new Ellipse2D.Double(
			x - getRadius() - additionalRadius,
			y - getRadius() - additionalRadius,
			(getRadius() + additionalRadius) * 2,
			(getRadius() + additionalRadius) * 2);
	}

	@Override
	public void damage()
	{
		hp--;
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
