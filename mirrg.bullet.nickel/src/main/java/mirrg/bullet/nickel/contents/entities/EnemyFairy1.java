package mirrg.bullet.nickel.contents.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import mirrg.bullet.nickel.phases.PhaseBattle;
import mirrg.util.hydrogen.HColor;

/**
 * 直進しつつ弾を撃ち始める雑魚。
 */
public class EnemyFairy1 extends EnemyBase
{

	private Color color;

	public EnemyFairy1(double x, double y, double xx, double yy, int hp, Color color)
	{
		super(x, y, xx, yy, 0.025, hp, 1);
		this.color = color;
	}

	private int ir = 0;

	@Override
	public void renderBody(PhaseBattle phase, Graphics2D graphics)
	{

		{
			graphics.setColor(HColor.createColor(
				color.getRed() * (1.0 * hp / hpMax) + 255 * (1 - 1.0 * hp / hpMax),
				color.getGreen() * (1.0 * hp / hpMax) + 0 * (1 - 1.0 * hp / hpMax),
				color.getBlue() * (1.0 * hp / hpMax) + 0 * (1 - 1.0 * hp / hpMax)));
			Stroke stroke = graphics.getStroke();
			graphics.setStroke(new BasicStroke(0.001f));

			graphics.draw(createRegularPolygon(3, x, y, r * (1 + 0.2 * Math.sin(ir * 19 * Math.PI / 180)), ir * 11 * Math.PI / 180));
			graphics.draw(createRegularPolygon(3, x, y, r * (0.7 + 0.2 * Math.sin((ir * 19 + 180) * Math.PI / 180)), ir * -6 * Math.PI / 180));

			graphics.setStroke(stroke);
		}

		ir++;
	}

}
