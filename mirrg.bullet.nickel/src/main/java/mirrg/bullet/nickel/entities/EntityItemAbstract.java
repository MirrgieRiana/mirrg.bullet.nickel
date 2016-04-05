package mirrg.bullet.nickel.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import mirrg.bullet.nickel.entity.IEntityItem;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public abstract class EntityItemAbstract implements IEntityItem
{

	public double x;
	public double y;
	public double yy = 0;
	public double xOffset;
	public double yOffset;
	public double r = 0.005;
	public double rGuide = 0.1;

	public int hp = 1;

	public EntityItemAbstract(double x, double y, double xOffset, double yOffset)
	{
		this.x = x;
		this.y = y;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	@Override
	public boolean move(PhaseBattle phase)
	{
		x += xOffset * 0.2;
		y += yOffset * 0.2;
		xOffset *= 0.8;
		yOffset *= 0.8;

		yy += 0.0001;
		if (yy > 0.004) yy = 0.004;

		boolean fallable = true;

		// guide
		for (ILiving player : phase.players) {
			if (player.getShape(r).contains(x, y)) {
				onCought(phase);
				damage(1);
			} else if (player.getShape(rGuide).contains(x, y)) {
				double distance = new Point2D.Double(x, y).distance(player.getX(), player.getY());

				if (distance < 0.03) {
					x = player.getX();
					y = player.getY();
				} else {
					double theta = Math.atan2(player.getY() - y, player.getX() - x);
					double speed = 0.03;

					x += speed * Math.cos(theta);
					y += speed * Math.sin(theta);
				}

				fallable = false;
			}
		}

		if (fallable) y += yy;

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

	public abstract void onCought(PhaseBattle phase);

	@Override
	public void render(PhaseBattle phase, Graphics2D graphics)
	{
		graphics.setColor(getColor());
		graphics.fill(getShape(0));

		{
			graphics.setColor(Color.white);
			Stroke stroke = graphics.getStroke();
			graphics.setStroke(new BasicStroke(0.003f));

			graphics.draw(getShape(0));

			graphics.setStroke(stroke);
		}
	}

	public abstract Color getColor();

	@Override
	public Shape getShape(double additionalRadius)
	{
		return new Rectangle2D.Double(
			x - r - additionalRadius,
			y - r - additionalRadius,
			(r + additionalRadius) * 2,
			(r + additionalRadius) * 2);
	}

	@Override
	public void damage(int value)
	{
		hp -= value;
	}

	@Override
	public void doAutoHarvest()
	{
		rGuide = 2;
	}

}
