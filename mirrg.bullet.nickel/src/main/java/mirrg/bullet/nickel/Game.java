package mirrg.bullet.nickel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import mirrg.bullet.nickel.entities.EnemyBoss1;
import mirrg.bullet.nickel.entities.Player;
import mirrg.bullet.nickel.entity.IBullet;
import mirrg.bullet.nickel.entity.IEntity;
import mirrg.bullet.nickel.entity.IEntityItem;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.entity.IParticle;
import mirrg.bullet.nickel.item.Stack;
import mirrg.util.hydrogen.HString;

public class Game
{

	public PanelNickel panel;

	public ArrayList<ILiving> players = new ArrayList<>();
	public ArrayList<ILiving> enemies = new ArrayList<>();
	public ArrayList<IBullet> bulletsPlayer = new ArrayList<>();
	public ArrayList<IBullet> bulletsEnemy = new ArrayList<>();
	public ArrayList<IEntityItem> entityItems = new ArrayList<>();
	public ArrayList<IParticle> particles = new ArrayList<>();
	public ArrayList<Stack> inventory = new ArrayList<>();

	public ILiving player;
	public int score;
	public int scoreMax;
	public int life;
	public int clearing;

	public Dimension sizePanel;
	public Dimension sizeGame;
	public Dimension sizeInventory;

	public Game(PanelNickel panel)
	{
		this.panel = panel;
	}

	public void init(int width, int height)
	{
		resized(width, height);
		scoreMax = 100;
		reset();
	}

	public void resized(int width, int height)
	{
		sizePanel = new Dimension(width, height);
		sizeGame = new Dimension(height, height);
		sizeInventory = new Dimension(width - height, height);
	}

	private void reset()
	{
		players.clear();
		enemies.clear();
		bulletsPlayer.clear();
		bulletsEnemy.clear();
		entityItems.clear();
		particles.clear();
		inventory.clear();

		player = new Player(0.5, 0.75, 0.005);
		addPlayer(player);
		addEnemy(new EnemyBoss1(0.5, 0.15, 0.1));

		if (score > scoreMax) scoreMax = score;
		score = 0;
		life = 5;
		clearing = 0;
	}

	public void move()
	{

		moveEntities(players);
		moveEntities(enemies);
		moveBullets(bulletsPlayer, enemies);
		moveBullets(bulletsEnemy, players);
		moveEntities(entityItems);

		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).move(this)) {
				particles.remove(i);
				i--;
				continue;
			}
		}

		listenersInvokeLator.forEach(Runnable::run);
		listenersInvokeLator.clear();

		if (enemies.size() == 0) {
			clearing++;

			if (clearing == 30) {
				for (IEntityItem entityItem : entityItems) {
					entityItem.doAutoHarvest();
				}
			}
		}

	}

	private void moveEntities(ArrayList<? extends IEntity> entities)
	{
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).move(this)) {
				entities.get(i).onDie(this);
				entities.remove(i);
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

	public void addEntityItem(IEntityItem entityItem)
	{
		entityItems.add(entityItem);
	}

	public void addParticle(IParticle particle)
	{
		particles.add(particle);
	}

	public void addStack(Stack stack)
	{
		for (int i = 0; i < inventory.size(); i++) {
			Stack stack2 = inventory.get(i);
			if (stack2.item == stack.item) {
				inventory.set(i, new Stack(stack2.item, stack2.amount + stack.amount));
				return;
			}
		}
		inventory.add(stack);
	}

	public void paint(Graphics2D graphics)
	{

		// clear
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, sizePanel.width, sizePanel.height);

		drawGame(graphics, sizeGame);

		// inventory
		{
			AffineTransform transform = graphics.getTransform();
			graphics.translate(sizeGame.width, 0);

			// TODO
			graphics.setColor(new Color(128, 0, 0));
			graphics.fillRect(0, 0, sizeInventory.width, sizeInventory.height);

			drawScore(graphics, sizeInventory);
			drawFPS(graphics, sizeInventory);

			graphics.setTransform(transform);
		}

	}

	private void drawGame(Graphics2D graphics, Dimension size)
	{

		{
			AffineTransform transform = graphics.getTransform();
			graphics.scale(size.width, size.height);
			{

				// render player bullets
				for (IEntity bulletPlayer : bulletsPlayer) {
					bulletPlayer.render(this, graphics);
				}

				// render enemies
				for (IEntity enemy : enemies) {
					enemy.render(this, graphics);
				}

				// render particles
				for (IParticle particle : particles) {
					particle.render(this, graphics);
				}

				// render items
				for (IEntity entityItem : entityItems) {
					entityItem.render(this, graphics);
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

			// render particles
			for (IParticle particle : particles) {
				particle.renderOverlay(this, graphics);
			}

			// render items
			for (IEntity entityItem : entityItems) {
				entityItem.renderOverlay(this, graphics);
			}

			// render enemy bullets
			for (IEntity bulletEnemy : bulletsEnemy) {
				bulletEnemy.renderOverlay(this, graphics);
			}

			// render player
			player.renderOverlay(this, graphics);

		}

	}

	private void drawScore(Graphics2D graphics, Dimension size)
	{
		graphics.setColor(Color.white);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		graphics.drawString("Score: " + score,
			0, graphics.getFont().getSize());
		graphics.drawString("HiScore: " + scoreMax,
			0, graphics.getFont().getSize() * 2);
		graphics.drawString("clearing: " + clearing,
			0, graphics.getFont().getSize() * 3);

		graphics.drawString("残機: " + HString.rept("★", life),
			0, graphics.getFont().getSize() * 6);

		for (int i = 0; i < inventory.size(); i++) {

			graphics.drawString(inventory.get(i).item.name,
				0, graphics.getFont().getSize() * (9 + i));

			graphics.drawString(
				"" + inventory.get(i).amount,
				size.width - 5 - graphics.getFontMetrics().stringWidth("" + inventory.get(i).amount),
				graphics.getFont().getSize() * (9 + i));

		}
	}

	private void drawFPS(Graphics2D graphics, Dimension size)
	{
		graphics.setColor(Color.white);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		graphics.drawString(String.format(
			"FPS: %.2f",
			panel.responceApplyStandard.moduleFPSAdjuster.getFPS()),
			0, size.height);
		graphics.drawString(String.format(
			"CPU: %.2f%%",
			panel.responceApplyStandard.moduleFPSAdjuster.getLoadFactor() * 100),
			0, size.height - graphics.getFont().getSize());
	}

	public void die()
	{
		player = new Player(0.5, 0.75, 0.005);
		addPlayer(player);

		if (life == 0) {
			reset();
		} else {
			life--;
		}

	}

}
