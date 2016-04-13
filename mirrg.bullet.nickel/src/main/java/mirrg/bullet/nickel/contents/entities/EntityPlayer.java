package mirrg.bullet.nickel.contents.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import mirrg.bullet.nickel.contents.entities.bullets.BulletBomb;
import mirrg.bullet.nickel.entity.IPlayer;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.weapon.card.CardWeapon;
import mirrg.bullet.nickel.weapon.instance.IWeapon;

public class EntityPlayer implements IPlayer
{

	public double x;
	public double y;
	public double r;
	public CardWeapon cardWeaponLeft;
	public CardWeapon cardWeaponRight;
	public IWeapon weaponLeft;
	public IWeapon weaponRight;
	public int invincible = 50;

	public EntityPlayer(double x, double y, double r, CardWeapon cardWeaponLeft, CardWeapon cardWeaponRight)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		this.cardWeaponLeft = cardWeaponLeft;
		this.cardWeaponRight = cardWeaponRight;

		weaponLeft = cardWeaponLeft.createWeapon();
		weaponRight = cardWeaponRight.createWeapon();
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
			weaponLeft.move(this, phase, true, true);
			weaponRight.move(this, phase, false, true);
		} else {
			weaponLeft.move(this, phase, false, true);
			if (phase.game.panel.responceApplyStandard.moduleInputStatus.getMouseButtons().getState(MouseEvent.BUTTON3) > 0) {
				weaponRight.move(this, phase, true, true);
			} else {
				weaponRight.move(this, phase, false, true);
			}
		}

		if (invincible > 0) invincible--;

		return hp <= 0;
	}

	@Override
	public void onDie(PhaseBattle phase)
	{
		phase.addBulletPlayer(new BulletBomb(x, y, 0.5, (int) (cardWeaponLeft.getDamagePerSecond(true) / 10)));
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
		return weaponLeft;
	}

	@Override
	public IWeapon getWeaponSub()
	{
		return weaponRight;
	}

}
