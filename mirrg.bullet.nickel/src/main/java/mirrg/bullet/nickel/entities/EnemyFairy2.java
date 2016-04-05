package mirrg.bullet.nickel.entities;

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

		yStart = y;
		this.speed = speed;
		this.wait = wait;
	}

	@Override
	public boolean move(PhaseBattle phase)
	{

		if (age == 0) {
			if (y - yStart >= 0.2) {
				age = 1;

				yy = 0;
				firing = true;
			}
		} else {

			if (age == wait) yy = -speed;

			age++;
		}

		return super.move(phase);
	}

}
