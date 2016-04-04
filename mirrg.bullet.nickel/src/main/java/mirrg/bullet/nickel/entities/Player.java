package mirrg.bullet.nickel.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import mirrg.bullet.nickel.Game;
import mirrg.bullet.nickel.entity.ILiving;

public class Player implements ILiving
{

	public double x;
	public double y;
	public double r;

	public Player(double x, double y, double r)
	{
		this.x = x;
		this.y = y;
		this.r = r;
	}

	private int i;

	@Override
	public boolean move(Game game)
	{

		// move
		double xTo = 1.0 * game.panel.responceApplyStandard.moduleInputStatus.getMouseX() / game.sizeGame.width;
		double yTo = 1.0 * game.panel.responceApplyStandard.moduleInputStatus.getMouseY() / game.sizeGame.height;

		{
			double distance = new Point2D.Double(x, y).distance(xTo, yTo);

			if (distance < 0.03) {
				x = xTo;
				y = yTo;
			} else {
				double theta = Math.atan2(yTo - y, xTo - x);
				double speed = 0.03;

				x += speed * Math.cos(theta);
				y += speed * Math.sin(theta);
			}

			if (x < 0) x = 0;
			if (x > 1) x = 1;
			if (y < 0) y = 0;
			if (y > 1) y = 1;

		}

		// shot
		if (game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON1) > 0) {
			if (i % 2 == 0) {
				double theta = 1.5 * Math.PI;
				double speed = 0.04;

				game.addBulletPlayer(new BulletStraight(
					x - 0.01,
					y,
					speed * Math.cos(theta),
					speed * Math.sin(theta),
					0.01,
					Color.darkGray));
				game.addBulletPlayer(new BulletStraight(
					x + 0.01,
					y,
					speed * Math.cos(theta),
					speed * Math.sin(theta),
					0.01,
					Color.darkGray));
				game.addBulletPlayer(new BulletStraight(
					x - 0.01,
					y,
					speed * Math.cos(theta - 5 * Math.PI / 180),
					speed * Math.sin(theta - 5 * Math.PI / 180),
					0.01,
					Color.darkGray));
				game.addBulletPlayer(new BulletStraight(
					x + 0.01,
					y,
					speed * Math.cos(theta + 5 * Math.PI / 180),
					speed * Math.sin(theta + 5 * Math.PI / 180),
					0.01,
					Color.darkGray));
			}
		} else if (game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON3) > 0) {
			if (i % 5 == 0) {
				for (int i = 0; i < 11; i++) {
					double theta = 1.5 * Math.PI + 8 * (i - 5) * Math.PI / 180;
					double speed = 0.04;
					game.addBulletPlayer(new BulletStraight(
						x,
						y,
						speed * Math.cos(theta),
						speed * Math.sin(theta),
						0.005,
						Color.darkGray));
				}
			}
		}

		i++;

		return hp <= 0;
	}

	@Override
	public void onDie(Game game)
	{
		game.addBulletPlayer(new BulletBomb(x, y, 0.5));
		game.invokeLater(game::die);
	}

	@Override
	public void render(Game game, Graphics2D graphics)
	{
		graphics.setColor(Color.white);
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
