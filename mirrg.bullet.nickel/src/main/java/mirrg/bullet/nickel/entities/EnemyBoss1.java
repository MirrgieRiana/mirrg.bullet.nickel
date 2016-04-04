package mirrg.bullet.nickel.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import mirrg.bullet.nickel.Game;
import mirrg.bullet.nickel.item.Item;
import mirrg.bullet.nickel.item.Stack;

public class EnemyBoss1 extends EnemyAbstract
{

	public EnemyBoss1(double x, double y, double r)
	{
		super(x, y, 0, 0, r, 1000);
	}

	@Override
	public boolean move(Game game)
	{

		// 雑魚召喚
		if (age % 50 == 0) {
			EnemyFairy enemy = new EnemyFairy(x, y, 0, 0);
			double theta = Math.atan2(game.player.getY() - enemy.y, game.player.getX() - enemy.x)
				+ (Math.random() * 2 - 1) * 60 * Math.PI / 180;
			double speed = 0.004;
			enemy.xx = speed * Math.cos(theta);
			enemy.yy = speed * Math.sin(theta);
			game.addEnemy(enemy);
		}

		// 全方位ワインダー
		if (age % 4 == 0) {
			for (int i3 = 0; i3 < 2; i3++) {
				for (int i2 = 0; i2 < 8; i2++) {
					double theta = age * Math.PI / 180 * (i3 * 2 - 1);
					double speed = 0.004;

					game.addBulletEnemy(new BulletStraight(
						x,
						y,
						speed * Math.cos(theta + i2 * Math.PI / 4),
						speed * Math.sin(theta + i2 * Math.PI / 4),
						0.004,
						Color.lightGray));
				}
			}
		}

		// 自機狙い
		if (age % 20 == 0) {
			for (int i = 0; i < 5; i++) {

				BulletStraight bullet = new BulletStraight(Math.random(), 0.25 * Math.random(), 0, 0, 0.015, Color.yellow);
				double theta = Math.atan2(game.player.getY() - bullet.y, game.player.getX() - bullet.x);
				double speed = Math.sqrt(pow2(game.player.getY() - bullet.y) + pow2(game.player.getX() - bullet.x)) / 100;
				bullet.xx = speed * Math.cos(theta);
				bullet.yy = speed * Math.sin(theta);

				game.addBulletEnemy(bullet);
			}
		}

		// ランダム板
		if (age % 5 == 0) {
			double theta = Math.random() * 2 * Math.PI;
			double speed = Math.random() * 0.005 + 0.002;

			BulletStraight bullet = new BulletStraight(
				Math.random(),
				0.5 * Math.random(),
				speed * Math.cos(theta),
				speed * Math.sin(theta),
				0.002,
				Color.lightGray);
			game.addBulletEnemy(bullet);

			int count = 4 + (int) (5 * Math.random());
			for (int i = 0; i < count; i++) {
				game.addBulletEnemy(new BulletStraight(
					bullet.x + i * 0.01 * Math.cos(theta + Math.PI / 2),
					bullet.y + i * 0.01 * Math.sin(theta + Math.PI / 2),
					speed * Math.cos(theta),
					speed * Math.sin(theta),
					0.002,
					Color.lightGray));
			}
		}

		// 自機狙い槍
		if (age % 40 == 0) {
			BulletStraight bullet = new BulletStraight(Math.random(), 0.25 * Math.random(), 0, 0, 0.003, Color.red);
			double theta = Math.atan2(
				game.player.getY() - bullet.y,
				game.player.getX() - bullet.x) + (Math.random() - 0.5) * 2 * 3 * Math.PI / 180;
			double speed = 0.02;
			bullet.xx = speed * Math.cos(theta);
			bullet.yy = speed * Math.sin(theta);

			game.addBulletEnemy(bullet);

			for (int i = 1; i < 30; i++) {
				game.addBulletEnemy(new BulletStraight(
					bullet.x + i * 0.01 * Math.cos(theta),
					bullet.y + i * 0.01 * Math.sin(theta),
					bullet.xx,
					bullet.yy,
					0.003,
					Color.red));
			}
		}

		return super.move(game);
	}

	@Override
	public void onDie(Game game)
	{
		game.addBulletPlayer(new BulletBomb(x, y, 1));

		for (int i = 0; i < 30; i++) {
			double theta = 2 * Math.random() * Math.PI;
			double length = 0.03 + 0.07 * Math.random();

			if (0.2 > Math.random()) {
				game.addEntityItem(new EntityItemStack(x, y, length * Math.cos(theta), length * Math.sin(theta),
					new Stack(Item.EnumItem.values()[(int) (Item.EnumItem.values().length * Math.random())].item, 1)));
			} else {
				game.addEntityItem(new EntityItemScore(x, y, length * Math.cos(theta), length * Math.sin(theta), 10));
			}
		}
	}

	private double pow2(double value)
	{
		return value * value;
	}

	@Override
	public void render(Game game, Graphics2D graphics)
	{
		graphics.setColor(Color.blue);
		graphics.fill(getShape(-r / 2));

		renderHPBar(game, graphics);
	}

}
