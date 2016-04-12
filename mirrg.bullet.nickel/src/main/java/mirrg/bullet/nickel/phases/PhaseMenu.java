package mirrg.bullet.nickel.phases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.gui.Counter;
import mirrg.bullet.nickel.gui.IComponent;
import mirrg.bullet.nickel.gui.PanelButtons;
import mirrg.bullet.nickel.phase.IPhase;

public class PhaseMenu implements IPhase
{

	private GameNickel game;
	private IPhase parent;
	private ArrayList<IComponent> components = new ArrayList<>();

	public PhaseMenu(GameNickel game, IPhase parent)
	{
		this.game = game;
		this.parent = parent;
	}

	@Override
	public void init()
	{
		reform();
	}

	@Override
	public void onResized()
	{
		parent.onResized();
		reform();
	}

	protected synchronized void reform()
	{
		components.clear();

		PanelButtons panelButtons = new PanelButtons(2, 1, 120, 40,
			new Point2D.Double(game.sizeGame.width / 2, game.sizeGame.height / 2));
		components.add(panelButtons);

		{
			panelButtons.putButton(game, 0, 0)
				.setOnClick(() -> {
					game.setPhase(parent);
				});
			panelButtons.putLabel(game, 0, 0, "戦闘続行", new Font(Font.SANS_SERIF, Font.PLAIN, 24));

			panelButtons.putButton(game, 1, 0)
				.setOnClick(() -> {
					IPhase phase = new PhaseStages(game);
					phase.init();
					game.setPhase(phase);
				});
			panelButtons.putLabel(game, 1, 0, "戦闘中断", new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		}
	}

	@Override
	public synchronized void move()
	{
		if (game.panel.responceApplyStandard.moduleInputStatus.getKeyBoard().getState(KeyEvent.VK_ESCAPE) == 1) {

			game.setPhase(parent);

		} else {

			for (IComponent component : components) {
				component.move();
			}

		}
	}

	@Override
	public synchronized void paint(Graphics2D graphics)
	{
		parent.paint(graphics);

		graphics.setColor(new Color(0, 0, 0, 192));
		graphics.fill(new Rectangle(0, 0, game.sizeGame.width, game.sizeGame.height));

		for (IComponent component : components) {
			component.paint(graphics);
		}
	}

	@Override
	public void paintScore(Graphics2D graphics, Dimension size, Counter counter)
	{
		parent.paintScore(graphics, size, counter);
	}

}
