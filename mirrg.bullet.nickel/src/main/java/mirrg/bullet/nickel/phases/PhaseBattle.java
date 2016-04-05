package mirrg.bullet.nickel.phases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import mirrg.bullet.nickel.Counter;
import mirrg.bullet.nickel.GameNickel;
import mirrg.bullet.nickel.IPhase;
import mirrg.bullet.nickel.IStage;
import mirrg.bullet.nickel.entities.Player;
import mirrg.bullet.nickel.entity.IBullet;
import mirrg.bullet.nickel.entity.IEntity;
import mirrg.bullet.nickel.entity.IEntityItem;
import mirrg.bullet.nickel.entity.ILiving;
import mirrg.bullet.nickel.entity.IParticle;
import mirrg.bullet.nickel.entity.IPlayer;
import mirrg.util.hydrogen.HString;

public class PhaseBattle implements IPhase
{

	public GameNickel game;

	public ArrayList<ILiving> players = new ArrayList<>();
	public ArrayList<ILiving> enemies = new ArrayList<>();
	public ArrayList<IBullet> bulletsPlayer = new ArrayList<>();
	public ArrayList<IBullet> bulletsEnemy = new ArrayList<>();
	public ArrayList<IEntityItem> entityItems = new ArrayList<>();
	public ArrayList<IParticle> particles = new ArrayList<>();

	public IPlayer player;
	private int score = 0;
	public int life = 5;
	public int clearing = 0;
	public int dying = 0;
	public IStage stage;

	public PhaseBattle(GameNickel game, IStage stage)
	{
		this.game = game;
		this.stage = stage;

		respawn();

		stage.init(this);
	}

	@Override
	public void move()
	{

		stage.move(this);

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

		if (stage.isFinished()) {
			if (enemies.size() == 0) {
				clearing++;

				if (clearing == 30) {
					doAutoHarvest();
				}

				if (clearing == 100) {
					game.setPhase(new PhaseStages(game));
				}
			}
		}

		if (players.size() == 0) {
			dying++;
			if (dying == 100) {
				game.setPhase(new PhaseStages(game));
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
					targets.get(j).damage(bullets.get(i).getAttack());
					bullets.get(i).damage(targets.get(j).getToughness());
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

	public void die()
	{
		if (life > 0) {
			life--;
			respawn();
		}
	}

	public void respawn()
	{
		player = new Player(0.5, 0.75, 0.005, game.weaponMain.weapon, game.weaponSub.weapon);
		addPlayer(player);
	}

	public void doAutoHarvest()
	{
		for (IEntityItem entityItem : entityItems) {
			entityItem.doAutoHarvest();
		}
	}

	public void addScore(int addition)
	{
		score += addition;
		if (score > game.scoreMax) game.scoreMax = score;
	}

	@Override
	public void paint(Graphics2D graphics)
	{
		game.drawBackground(graphics);

		{
			AffineTransform transform = graphics.getTransform();
			graphics.scale(game.sizeGame.width, game.sizeGame.height);
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
				for (IEntity player : players) {
					player.render(this, graphics);
				}

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
			for (IEntity player : players) {
				player.renderOverlay(this, graphics);
			}

		}

		game.drawInventory(graphics);
	}

	@Override
	public void paintScore(Graphics2D graphics, Dimension size, Counter counter)
	{
		graphics.setColor(Color.white);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));

		graphics.drawString("Score: " + score,
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize());

		graphics.drawString("clearing: " + clearing,
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize());

		graphics.drawString("dying: " + dying,
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize() * 2);

		graphics.drawString("残機: " + HString.rept("★", life),
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize());
	}

}
