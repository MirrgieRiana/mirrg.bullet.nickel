package mirrg.bullet.nickel.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

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
		x = 1.0 * game.panel.responceApplyStandard.moduleInputStatus.getMouseX() / game.panel.getWidth();
		y = 1.0 * game.panel.responceApplyStandard.moduleInputStatus.getMouseY() / game.panel.getHeight();

		// shot
		if (i % 2 == 0) {
			if (game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON1) > 0) {
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
