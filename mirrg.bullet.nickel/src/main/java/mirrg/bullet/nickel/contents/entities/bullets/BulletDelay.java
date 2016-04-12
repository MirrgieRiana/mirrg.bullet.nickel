package mirrg.bullet.nickel.contents.entities.bullets;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.function.DoubleConsumer;

import mirrg.bullet.nickel.entity.IBullet;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class BulletDelay implements IBullet
{

	public double wait;
	public DoubleConsumer action;

	public BulletDelay(double wait, DoubleConsumer action)
	{
		this.wait = wait;
		this.action = action;
	}

	@Override
	public boolean move(PhaseBattle phase)
	{
		wait--;
		if (wait < 0) return true;

		return hp <= 0;
	}

	@Override
	public void onDie(PhaseBattle phase)
	{
		if (wait < 0) action.accept(-wait);
	}

	@Override
	public void render(PhaseBattle phase, Graphics2D graphics)
	{

	}

	@Override
	public Shape getShape(double additionalRadius)
	{
		return new Rectangle2D.Double(0, 0, 0, 0);
	}

	private int hp = 1;

	@Override
	public void damage(int value)
	{
		hp -= value;
	}

	@Override
	public int getAttack()
	{
		return 0;
	}

}
