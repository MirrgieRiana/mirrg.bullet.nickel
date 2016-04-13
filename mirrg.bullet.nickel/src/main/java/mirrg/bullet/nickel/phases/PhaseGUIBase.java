package mirrg.bullet.nickel.phases;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.bullet.nickel.contents.Recipes;
import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Counter;
import mirrg.bullet.nickel.gui.IComponent;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.phase.IPhase;

public class PhaseGUIBase implements IPhase
{

	public GameNickel game;
	public ArrayList<IComponent> components = new ArrayList<>();

	public PhaseGUIBase(GameNickel game)
	{
		this.game = game;
	}

	@Override
	public void init()
	{
		fireReform();
	}

	@Override
	public void onResized()
	{
		fireReform();
	}

	public void fireReform()
	{
		reform();
		fireReflesh();
	}

	protected synchronized void reform()
	{
		components.clear();
		putButtonsMenu();
	}

	private void putButtonsMenu()
	{
		Counter counter = new Counter();

		putButton(() -> new PhaseStages(game), "戦闘", counter);
		putButton(() -> new PhaseInventory(game), "インベントリ", counter);
		putButton(() -> new PhaseCraft(game, Recipes.recipesMake), "武器生産", counter);
		putButton(() -> new PhaseCraft(game, Recipes.recipesUpgrade), "武器強化", counter);
		putButton(() -> new PhaseCraft(game, Recipes.recipesCombine), "素材加工", counter);
		putButton(() -> new PhaseCraft(game, Recipes.recipesRefine), "素材精錬", counter);
		putButton(() -> new PhaseCraft(game, Recipes.recipesDebug), "デバッグ入手", counter);
		putButton(() -> new PhaseDebug(game), "デバッグ機能", counter);

	}

	private void putButton(Supplier<IPhase> supplierPhase, String label, Counter counter)
	{
		Rectangle2D.Double rectangle;
		rectangle = new Rectangle2D.Double(5, 5 + (40 + 5) * counter.value, 150, 40);
		components.add(new Button(game, rectangle)
			.setOnClick(() -> {
				IPhase phase = supplierPhase.get();
				phase.init();
				game.setPhase(phase);
			}));
		components.add(new Label(new Point2D.Double(rectangle.getCenterX(), rectangle.getCenterY()),
			label, new Font(Font.SANS_SERIF, Font.PLAIN, 24)));
		counter.add(1);
	}

	private ArrayList<Runnable> listenersInvokeLater = new ArrayList<>();

	public void hookInvokeLater(Runnable listener)
	{
		listenersInvokeLater.add(listener);
	}

	public void fireInvokeLater()
	{
		listenersInvokeLater.forEach(Runnable::run);
		listenersInvokeLater.clear();
	}

	@Override
	public synchronized void move()
	{
		for (IComponent component : components) {
			component.move();
		}

		fireInvokeLater();
	}

	@Override
	public synchronized void paint(Graphics2D graphics)
	{
		game.drawBackground(graphics);
		game.drawInventory(graphics);

		for (IComponent component : components) {
			component.paint(graphics);
		}
	}

	private ArrayList<Runnable> listenersReflesh = new ArrayList<>();

	public void hookReflesh(Runnable listener)
	{
		listenersReflesh.add(listener);
	}

	public void fireReflesh()
	{
		listenersReflesh.forEach(Runnable::run);
	}

}
