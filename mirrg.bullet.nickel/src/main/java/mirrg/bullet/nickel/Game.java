package mirrg.bullet.nickel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import mirrg.bullet.nickel.entities.EnemyBoss1;
import mirrg.bullet.nickel.entities.Player;
import mirrg.bullet.nickel.entity.IBullet;
import mirrg.bullet.nickel.entity.IEntity;
import mirrg.bullet.nickel.entity.ILiving;

public class Game
{

	public PanelNickel panel;

	public ArrayList<ILiving> players = new ArrayList<>();
	public ArrayList<ILiving> enemies = new ArrayList<>();
	public ArrayList<IBullet> bulletsPlayer = new ArrayList<>();
	public ArrayList<IBullet> bulletsEnemy = new ArrayList<>();

	public ILiving player;
	private int score;
	private int scoreMax;

	public Game(PanelNickel panel)
	{
		this.panel = panel;
	}

	public void init()
	{
		players.clear();
		enemies.clear();
		bulletsPlayer.clear();
		bulletsEnemy.clear();

		player = new Player(0.5, 0.75, 0.005);
		addPlayer(player);
		addEnemy(new EnemyBoss1(0.5, 0.15, 0.1));
	}

	public void move()
	{

		moveLivings(players);
		moveLivings(enemies);
		moveBullets(bulletsPlayer, enemies);
		moveBullets(bulletsEnemy, players);

		listenersInvokeLator.forEach(Runnable::run);
		listenersInvokeLator.clear();

		score++;
	}

	private void moveLivings(ArrayList<ILiving> livings)
	{
		for (int i = 0; i < livings.size(); i++) {
			if (livings.get(i).move(this)) {
				livings.get(i).onDie(this);
				livings.remove(i);
				i--;
				continue;
			}
		}
	}

	private void moveBullets(ArrayList<IBullet> bullets, ArrayList<ILiving> targets)
	{
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).move(this)) {
				bullets.get(i).onDie(this);
				bullets.remove(i);
				i--;
				continue;
			}
			for (int j = 0; j < targets.size(); j++) {
				if (isColliding(targets.get(j), bullets.get(i))) {
					targets.get(j).damage();
					bullets.get(i).damage();
					continue;
				}
			}
		}
	}

	public static boolean isColliding(ILiving living, IEntity entity)
	{
		return entity.getShape(living.getRadius()).contains(living.getX(), living.getY());
	}

	public ArrayList<Runnable> listenersInvokeLator = new ArrayList<>();

	public void invokeLater(Runnable listener)
	{
		listenersInvokeLator.add(listener);
	}

	public void addPlayer(ILiving player)
	{
		players.add(player);
	}

	public void addEnemy(ILiving enemy)
	{
		enemies.add(enemy);
	}

	public void addBulletPlayer(IBullet bullet)
	{
		bulletsPlayer.add(bullet);
	}

	public void addBulletEnemy(IBullet bullet)
	{
		bulletsEnemy.add(bullet);
	}

	public void paint(Graphics2D graphics)
	{
		drawGame(graphics);
		drawScore(graphics);
		drawFPS(graphics);
	}

	private void drawGame(Graphics2D graphics)
	{

		// clear
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, panel.getWidth(), panel.getHeight());

		{
			int width = panel.getWidth();
			int height = panel.getHeight();
			AffineTransform transform = graphics.getTransform();
			graphics.scale(width, height);
			{

				// render player bullets
				for (IEntity bulletPlayer : bulletsPlayer) {
					bulletPlayer.render(this, graphics);
				}

				// render enemies
				for (IEntity enemy : enemies) {
					enemy.render(this, graphics);
				}

				// render enemy bullets
				for (IEntity bulletEnemy : bulletsEnemy) {
					bulletEnemy.render(this, graphics);
				}

				// render player
				player.render(this, graphics);

			}
			graphics.setTransform(transform);
		}

		{

			// render player bullets
			for (IEntity bulletPlayer : bulletsPlayer) {
				bulletPlayer.renderOverlay(this, graphics);
			}

			// render enemies
			for (IEntity enemy : enemies) {
				enemy.renderOverlay(this, graphics);
			}

			// render enemy bullets
			for (IEntity bulletEnemy : bulletsEnemy) {
				bulletEnemy.renderOverlay(this, graphics);
			}

			// render player
			player.renderOverlay(this, graphics);

		}
	}

	private void drawScore(Graphics2D graphics)
	{
		graphics.setColor(Color.white);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		graphics.drawString("Score: " + score,
			0, graphics.getFont().getSize());
		graphics.drawString("HiScore: " + scoreMax,
			0, graphics.getFont().getSize() * 2);
	}

	private void drawFPS(Graphics2D graphics)
	{
		graphics.setColor(Color.white);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		graphics.drawString(String.format(
			"FPS: %.2f",
			panel.responceApplyStandard.moduleFPSAdjuster.getFPS()),
			0, panel.getHeight());
		graphics.drawString(String.format(
			"CPU: %.2f%%",
			panel.responceApplyStandard.moduleFPSAdjuster.getLoadFactor() * 100),
			0, panel.getHeight() - graphics.getFont().getSize());
	}

	public void die()
	{
		player = new Player(0.5, 0.75, 0.005);
		addPlayer(player);

		if (score > scoreMax) scoreMax = score;
		score = 0;
	}

}
