package mirrg.bullet.nickel.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import mirrg.bullet.nickel.IWeapon;
import mirrg.bullet.nickel.InstanceWeapon;
import mirrg.bullet.nickel.entities.bullets.BulletBomb;
import mirrg.bullet.nickel.entity.IPlayer;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class Player implements IPlayer
{

	public double x;
	public double y;
	public double r;
	public InstanceWeapon weaponLeft;
	public InstanceWeapon weaponRight;
	public int invincible = 50;

	public Player(double x, double y, double r, IWeapon weaponLeft, IWeapon weaponRight)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		this.weaponLeft = new InstanceWeapon(weaponLeft, true);
		this.weaponRight = new InstanceWeapon(weaponRight, true);
	}

	@Override
	public boolean move(PhaseBattle phase)
	{

		// move
		double xTo = 1.0 * phase.game.panel.responceApplyStandard.moduleInputStatus.getMouseX() / phase.game.sizeGame.width;
		double yTo = 1.0 * phase.game.panel.responceApplyStandard.moduleInputStatus.getMouseY() / phase.game.sizeGame.height;

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

		// auto harvest
		if (y < 0.15) {
			phase.doAutoHarvest();
		}

		// shot
		if (phase.game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON1) > 0) {
			weaponLeft.shoot(this, phase);
		} else if (phase.game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON3) > 0) {
			weaponRight.shoot(this, phase);
		}
		weaponLeft.move(this, phase);
		weaponRight.move(this, phase);

		if (invincible > 0) invincible--;

		return hp <= 0;
	}

	@Override
	public void onDie(PhaseBattle phase)
	{
		phase.addBulletPlayer(new BulletBomb(x, y, 0.5, 50));
		phase.invokeLater(phase::die);
	}

	private int ir;

	@Override
	public void render(PhaseBattle phase, Graphics2D graphics)
	{
		if (invincible <= 0 || (ir) % 2 == 0) {
			graphics.setColor(Color.white);
			graphics.fill(getShape(0));
		}

		ir++;
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

	private int hp = 100;

	@Override
	public void damage(int value)
	{
		if (invincible <= 0) hp -= value;
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
	public IWeapon getWeaponMain()
	{
		return weaponLeft.weapon;
	}

	@Override
	public IWeapon getWeaponSub()
	{
		return weaponRight.weapon;
	}

}
