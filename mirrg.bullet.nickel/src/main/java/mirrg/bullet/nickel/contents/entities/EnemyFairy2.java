package mirrg.bullet.nickel.contents.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import mirrg.bullet.nickel.phases.PhaseBattle;

/**
 * 画面上から降りてきてしばらく弾幕を撃ったのち退場するほどほど堅い雑魚。
 */
public class EnemyFairy2 extends EnemyBase
{

	private int age = 0;
	private double yStart;
	private double speed;
	private int wait;

	public EnemyFairy2(double x, double y, double r, int hp, double speed, int wait)
	{
		super(x, y, 0, speed, r, hp, 10);
		firing = false;
		isInvincible = true;

		yStart = y;
		this.speed = speed;
		this.wait = wait;
	}

	public EnemyFairy2 setBoss(boolean isBoss)
	{
		this.isBoss = isBoss;
		return this;
	}

	@Override
	public boolean move(PhaseBattle phase)
	{

		if (age == 0) {
			if (y - yStart >= 0.2) {
				age = 1;

				yy = 0;
				firing = true;
				isInvincible = false;
			}
		} else {

			if (age == wait) yy = -speed;

			age++;
		}

		return super.move(phase);
	}

	private int ir = 0;

	@Override
	public void renderBody(PhaseBattle phase, Graphics2D graphics)
	{

		{
			graphics.setColor(new Color(255, 0, 0));
			Stroke stroke = graphics.getStroke();
			graphics.setStroke(new BasicStroke(0.001f));

			graphics.draw(createRegularPolygon(4, x, y, r * (0.5 + 0.5 * Math.sin(ir * 3 * Math.PI / 180)), ir * 3 * Math.PI / 180));
			graphics.draw(createRegularPolygon(4, x, y, r * (0.5 + 0.5 * Math.sin((ir * 3 + 120) * Math.PI / 180)), ir * -2 * Math.PI / 180));
			graphics.draw(createRegularPolygon(4, x, y, r * (0.5 + 0.5 * Math.sin((ir * 3 + 240) * Math.PI / 180)), ir * 5 * Math.PI / 180));

			graphics.setStroke(stroke);
		}

		ir++;
	}

}
