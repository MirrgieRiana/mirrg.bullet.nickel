package mirrg.bullet.nickel.phases;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.bullet.nickel.Counter;
import mirrg.bullet.nickel.GameNickel;
import mirrg.bullet.nickel.IPhase;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.IComponent;
import mirrg.bullet.nickel.gui.Label;

public class PhaseGUIBase implements IPhase
{

	public GameNickel game;
	public ArrayList<IComponent> components = new ArrayList<>();

	public PhaseGUIBase(GameNickel game)
	{
		this.game = game;

		putButtonsMenu();
	}

	private void putButtonsMenu()
	{
		Rectangle2D.Double rectangle;
		Counter counter = new Counter();

		putButton(() -> new PhaseStages(game), "バトル", counter);
		putButton(() -> new PhaseInventory(game), "インベントリ", counter);
		putButton(() -> new PhaseCraft(game), "合成", counter);
		putButton(() -> new PhaseCraft(game), "強化", counter); // TODO

	}

	private void putButton(Supplier<IPhase> supplierPhase, String label, Counter counter)
	{
		Rectangle2D.Double rectangle;
		rectangle = new Rectangle2D.Double(5, 5 + (40 + 5) * counter.value, 150, 40);
		components.add(new Button(game, rectangle, () -> {
			game.setPhase(supplierPhase.get());
		}));
		components.add(new Label(new Point2D.Double(rectangle.getCenterX(), rectangle.getCenterY()),
			label, new Font(Font.SANS_SERIF, Font.PLAIN, 24)));
		counter.add(1);
	}

	@Override
	public void move()
	{
		for (IComponent component : components) {
			component.move();
		}
	}

	@Override
	public void paint(Graphics2D graphics)
	{
		game.drawBackground(graphics);

		for (IComponent component : components) {
			component.paint(graphics);
		}

		game.drawInventory(graphics);
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
