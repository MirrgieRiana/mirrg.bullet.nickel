package mirrg.bullet.nickel.entities;

import java.awt.Color;

import mirrg.bullet.nickel.Game;

public class EnemyFairy extends EnemyAbstract
{

	public EnemyFairy(double x, double y, double xx, double yy)
	{
		super(x, y, xx, yy, 0.025, 10);
	}

	@Override
	public boolean move(Game game)
	{

		// 自機狙い3Way
		if (age % 20 == 0) {
			for (int i = 0; i < 3; i++) {

				BulletStraight bullet = new BulletStraight(x, y, 0, 0, 0.010, Color.orange);
				double theta = Math.atan2(game.player.getY() - bullet.y, game.player.getX() - bullet.x)
					+ (i - 1) * 3 * Math.PI / 180;
				double speed = 0.015;
				bullet.xx = speed * Math.cos(theta);
				bullet.yy = speed * Math.sin(theta);

				game.addBulletEnemy(bullet);
			}
		}

		return super.move(game);
	}

}
