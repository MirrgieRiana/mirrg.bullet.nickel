package mirrg.bullet.nickel.contents.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.bullet.nickel.contents.entities.bullets.BulletBomb;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.item.IStack;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.weapon.instance.IWeapon;

public class EnemyBase implements ILiving
{

	public double x;
	public double y;
	public double xx;
	public double yy;
	public double r;
	public int dropCount;

	public int hp;
	public int hpMax;

	public boolean firing = true;
	public boolean isBoss = false;
	public boolean isInvincible = false;

	public ArrayList<IWeapon> weapons = new ArrayList<>();
	public ArrayList<Supplier<IStack>> supplierStacks = new ArrayList<>();

	public EnemyBase(double x, double y, double xx, double yy, double r, int hp, int dropCount)
	{
		this.x = x;
		this.y = y;
		this.xx = xx;
		this.yy = yy;
		this.r = r;
		this.hp = hp;
		this.hpMax = hp;
		this.dropCount = dropCount;
	}

	public EnemyBase addWeapon(IWeapon weapons)
	{
		this.weapons.add(weapons);
		return this;
	}

	public EnemyBase addStack(Supplier<IStack> supplierStack)
	{
		return addStack(supplierStack, 1, 1);
	}

	public EnemyBase addStack(Supplier<IStack> supplierStack, double chance)
	{
		return addStack(supplierStack, chance, 1);
	}

	public EnemyBase addStack(Supplier<IStack> supplierStack, int count)
	{
		return addStack(supplierStack, 1, count);
	}

	public EnemyBase addStack(Supplier<IStack> supplierStack, double chance, int count)
	{
		for (int i = 0; i < count; i++) {
			if (chance > Math.random()) this.supplierStacks.add(supplierStack);
		}
		return this;
	}

	@Override
	public boolean move(PhaseBattle phase)
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

		if (firing) {
			for (IWeapon weapon : weapons) {
				weapon.move(this, phase, true, false);
			}
		} else {
			for (IWeapon weapon : weapons) {
				weapon.move(this, phase, false, false);
			}
		}

		return hp <= 0;
	}

	@Override
	public void onDie(PhaseBattle phase)
	{
		if (hp <= 0) {
			if (isBoss) phase.addBulletPlayer(new BulletBomb(x, y, 1, 1));

			int dropped = 0;

			for (Supplier<IStack> supplierStack : supplierStacks) {
				double theta = 2 * Math.random() * Math.PI;
				double length = r * (0.5 + Math.random() * 1.5);

				phase.addEntityItem(new EntityItemStack(x, y, length * Math.cos(theta), length * Math.sin(theta), supplierStack.get()));

				dropped++;
			}

			while (dropped < dropCount) {
				double theta = 2 * Math.random() * Math.PI;
				double length = r * (0.5 + Math.random() * 1.5);

				phase.addEntityItem(new EntityItemScore(x, y, length * Math.cos(theta), length * Math.sin(theta), 10));

				dropped++;
			}

			listenersHookOnDie.forEach(Runnable::run);
		}
	}

	@Override
	public void render(PhaseBattle phase, Graphics2D graphics)
	{
		renderBody(phase, graphics);
		if (isBoss) renderHPBar(phase, graphics);
	}

	public void renderBody(PhaseBattle phase, Graphics2D graphics)
	{
		graphics.setColor(Color.blue);
		graphics.fill(getShape(0));
	}

	public void renderHPBar(PhaseBattle phase, Graphics2D graphics)
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
	public void renderOverlay(PhaseBattle phase, Graphics2D graphics)
	{
		renderMarker(phase, graphics);
	}

	public void renderMarker(PhaseBattle phase, Graphics2D graphics)
	{
		double x2 = x * phase.game.sizeGame.width;
		double w2 = r * phase.game.sizeGame.width;
		double y2 = phase.game.sizeGame.height - 10;
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
	public void damage(int value)
	{
		if (!isInvincible) hp -= value;
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

	protected static Shape createRegularPolygon(int points, double x, double y, double r, double angle)
	{
		Path2D.Double path = new Path2D.Double();

		path.moveTo(x + r * Math.cos(angle), y + r * Math.sin(angle));
		for (int i = 1; i < points; i++) {
			double angle2 = (i * 360.0 / points) * Math.PI / 180;
			path.lineTo(x + r * Math.cos(angle + angle2), y + r * Math.sin(angle + angle2));
		}
		path.lineTo(x + r * Math.cos(angle), y + r * Math.sin(angle));
		path.closePath();

		return path;
	}

	private ArrayList<Runnable> listenersHookOnDie = new ArrayList<>();

	public EnemyBase hookOnDie(Runnable action)
	{
		listenersHookOnDie.add(action);
		return this;
	}

}
