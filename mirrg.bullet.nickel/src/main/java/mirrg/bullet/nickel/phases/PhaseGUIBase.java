package mirrg.bullet.nickel.phases;

import java.awt.Graphics2D;
import java.util.ArrayList;

import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.gui.IComponent;
import mirrg.bullet.nickel.phase.IPhase;

public class PhaseGUIBase implements IPhase
{

	protected GameNickel game;
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
