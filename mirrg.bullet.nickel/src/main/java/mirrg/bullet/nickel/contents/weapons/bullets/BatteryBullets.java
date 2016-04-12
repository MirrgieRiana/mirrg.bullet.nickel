package mirrg.bullet.nickel.contents.weapons.bullets;

import java.awt.Color;
import java.util.function.DoubleConsumer;

import mirrg.bullet.nickel.contents.entities.bullets.BulletDelay;
import mirrg.bullet.nickel.contents.entities.bullets.BulletStraight;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.bullet.nickel.weapon.BatteryAbstract;
import mirrg.bullet.nickel.weapon.KeyBoolean;
import mirrg.bullet.nickel.weapon.KeyColor;
import mirrg.bullet.nickel.weapon.KeyDouble;

public class BatteryBullets extends BatteryAbstract
{

	public static final KeyColor COLOR = new KeyColor("COLOR", Color.white);
	public static final KeyDouble SPAN = new KeyDouble("SPAN", 30);
	public static final KeyDouble SIZE = new KeyDouble("SIZE", 0.008);
	public static final KeyDouble DAMAGE = new KeyDouble("DAMAGE", 100);
	public static final KeyBoolean SEARCH = new KeyBoolean("SEARCH", false);
	public static final KeyDouble V_OFFSET = new KeyDouble("V_OFFSET", 0);
	public static final KeyDouble H_OFFSET = new KeyDouble("H_OFFSET", 0);
	public static final KeyDouble ANGLE_OFFSET = new KeyDouble("ANGLE_OFFSET", 0);
	public static final KeyDouble SPEED = new KeyDouble("SPEED", 0.004);

	public static final KeyDouble ANGLE_PER_TIME = new KeyDouble("ANGLE_PER_TIME", 0);

	public static final KeyDouble NOIZ_SPEED_RATE = new KeyDouble("NOIZ_SPEED_RATE", 0);
	public static final KeyDouble NOIZ_SPEED_OFFSET = new KeyDouble("NOIZ_SPEED_OFFSET", 0);
	public static final KeyDouble NOIZ_ANGLE = new KeyDouble("NOIZ_ANGLE", 0);

	public static final KeyDouble WAYS = new KeyDouble("WAYS", 1);
	public static final KeyDouble ANGLE_PER_WAY = new KeyDouble("ANGLE_PER_WAY", 0);
	public static final KeyDouble SPEED_RATE_PER_WAY = new KeyDouble("SPEED_RATE_PER_WAY", 0);
	public static final KeyDouble DELAY_PER_WAY = new KeyDouble("DELAY_PER_WAY", 0);

	public static final KeyDouble BULLETS = new KeyDouble("BULLETS", 1);
	public static final KeyDouble ANGLE_PER_BULLETS = new KeyDouble("ANGLE_PER_BULLETS", 0);
	public static final KeyDouble SPEED_RATE_PER_BULLET = new KeyDouble("SPEED_RATE_PER_BULLET", 0);
	public static final KeyDouble DELAY_PER_BULLET = new KeyDouble("DELAY_PER_BULLET", 0);

	private double gauge = 0;
	private int times = 0;

	public BatteryBullets(BatteryAbstract... parents)
	{
		super(parents);
	}

	@Override
	public void move(ILiving living, PhaseBattle phase, boolean isFireable, boolean isPlayer)
	{
		if (isFireable) {
			while (gauge < 1) {
				double extra = 1 - gauge;
				fire(living, phase, isPlayer, extra);
				times++;
				gauge += get(SPAN);
			}
			gauge -= 1;
		} else {
			gauge -= 1;
			if (gauge < 0) gauge = 0;
		}
	}

	public void fire(ILiving living, PhaseBattle phase, boolean isPlayer, double extra)
	{
		double ways = get(WAYS);
		if (get(WAYS) - ways > Math.random()) ways++;

		for (int w = 0; w < ways; w++) {
			double bullets = get(BULLETS);
			if (get(BULLETS) - bullets > Math.random()) bullets++;

			for (int b = 0; b < bullets; b++) {
				double x = living.getX();
				double y = living.getY();
				double theta = getAngle(x, y, phase, isPlayer);

				x += get(V_OFFSET) * Math.cos(theta) - get(H_OFFSET) * Math.sin(theta);
				y += get(V_OFFSET) * Math.sin(theta) + get(H_OFFSET) * Math.cos(theta);

				theta += get(ANGLE_PER_WAY) * (w - (ways - 1) * 0.5) * Math.PI / 180;
				theta += get(NOIZ_ANGLE) * (Math.random() * 2 - 1) * Math.PI / 180;
				theta += get(ANGLE_OFFSET) * Math.PI / 180;
				theta += times * get(ANGLE_PER_TIME) * Math.PI / 180;

				double speed = get(SPEED);
				speed *= (1 + (Math.random() * 2 - 1) * get(NOIZ_SPEED_RATE));
				speed *= 1 + b * get(SPEED_RATE_PER_BULLET);
				speed += get(NOIZ_SPEED_OFFSET) * (Math.random() * 2 - 1);

				double xx = speed * Math.cos(theta);
				double yy = speed * Math.sin(theta);

				x += extra * xx;
				y += extra * yy;

				int damage = (int) Math.floor(get(DAMAGE));
				if (get(DAMAGE) - damage > Math.random()) damage++;

				double delay = w * get(DELAY_PER_WAY) + b * get(DELAY_PER_BULLET);
				BulletStraight bullet = new BulletStraight(x, y, xx, yy, get(SIZE), get(COLOR), damage);
				DoubleConsumer action = extraTick -> {

					bullet.x += bullet.xx * extraTick;
					bullet.y += bullet.yy * extraTick;

					phase.invokeLater(() -> {
						if (isPlayer) {
							phase.addBulletPlayer(bullet);
						} else {
							phase.addBulletEnemy(bullet);
						}
					});
				};

				if (delay != 0) {
					phase.invokeLater(() -> {
						phase.addBulletEnemy(new BulletDelay(delay, action));
					});
				} else {
					action.accept(0);
				}

			}
		}
	}

	@Override
	public double getDamagePerSecond()
	{
		return 30.0 * get(WAYS) * get(BULLETS) * get(DAMAGE) / get(SPAN);
	}

	@Override
	public double getRangeAngle()
	{
		return get(NOIZ_ANGLE) * 2 + get(ANGLE_PER_WAY) * (get(WAYS) - 1);
	}

	private double getAngle(double x, double y, PhaseBattle phase, boolean isPlayer)
	{
		if (get(SEARCH)) {
			ILiving nearest;
			if (isPlayer) {
				nearest = PhaseBattle.getNearest(phase.enemies, x, y);
			} else {
				nearest = PhaseBattle.getNearest(phase.players, x, y);
			}
			if (nearest != null) return Math.atan2(nearest.getY() - y, nearest.getX() - x);
		}

		if (isPlayer) {
			return 270 * Math.PI / 180;
		} else {
			return 90 * Math.PI / 180;
		}
	}

	@Override
	public int getTierAdditional()
	{
		return get(SEARCH) ? 1 : 0;
	}

}
