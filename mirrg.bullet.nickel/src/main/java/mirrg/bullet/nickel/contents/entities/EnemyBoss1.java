package mirrg.bullet.nickel.contents.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;

public class EnemyBoss1 extends EnemyBase
{

	private int age = 0;
	private ILiving next = null;

	public EnemyBoss1(double x, double y, double r, int hp, int dropCount)
	{
		super(x, y, 0, 0, r, hp, dropCount);
		isBoss = true;
		firing = false;
	}

	@Override
	public boolean move(PhaseBattle phase)
	{

		if (age >= 50) firing = true;

		age++;

		return super.move(phase);
	}

	public EnemyBoss1 setNext(ILiving next)
	{
		this.next = next;
		return this;
	}

	@Override
	public void onDie(PhaseBattle phase)
	{
		super.onDie(phase);
		if (next != null) phase.addEnemy(next);
	}

	private int ir = 0;

	@Override
	public void renderBody(PhaseBattle phase, Graphics2D graphics)
	{

		{
			graphics.setColor(new Color(255, 192, 0));
			Stroke stroke = graphics.getStroke();
			graphics.setStroke(new BasicStroke(0.001f));

			graphics.draw(createRegularPolygon(3, x, y, r * (0.6 + 0.3 * Math.sin(ir * 6 * Math.PI / 180)), ir * 7 * Math.PI / 180));
			graphics.draw(createRegularPolygon(3, x, y, r * (0.6 + 0.3 * Math.sin(ir * 8 * Math.PI / 180)), ir * -10 * Math.PI / 180));
			graphics.draw(createRegularPolygon(6, x, y, r * (0.9 + 0.1 * Math.sin(ir * 1 * Math.PI / 180)), ir * 3 * Math.PI / 180));
			graphics.draw(createRegularPolygon(6, x, y, r * (0.9 + 0.1 * Math.sin((ir * 1 + 180) * Math.PI / 180)), ir * -3 * Math.PI / 180));

			graphics.setStroke(stroke);
		}

		ir++;
	}

	/*
	@Override
	public boolean move(PhaseBattle phase)
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
	
	return super.move(phase);
	}
	private double pow2(double value)
	{
		return value * value;
	}
	*/

}
