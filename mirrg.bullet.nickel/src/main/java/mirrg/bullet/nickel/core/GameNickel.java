package mirrg.bullet.nickel.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import mirrg.bullet.nickel.contents.CardWeapons;
import mirrg.bullet.nickel.gui.Counter;
import mirrg.bullet.nickel.phase.IPhase;
import mirrg.bullet.nickel.phases.PhaseTitle;
import mirrg.bullet.nickel.weapon.card.CardWeapon;

public class GameNickel implements IGame
{

	public PanelNickel panel;

	public Dimension sizePanel;
	public Dimension sizeGame;
	public Dimension sizeInventory;

	public IPhase phase;

	public GameNickel(PanelNickel panel)
	{
		this.panel = panel;
	}

	@Override
	public void init(int width, int height)
	{
		updateLayout(width, height);
		{
			IPhase phase = new PhaseTitle(this);
			phase.init();
			setPhase(phase);
		}
	}

	@Override
	public void resized(int width, int height)
	{
		updateLayout(width, height);
		phase.onResized();
	}

	private void updateLayout(int width, int height)
	{
		sizePanel = new Dimension(width, height);
		sizeGame = new Dimension(height, height);
		sizeInventory = new Dimension(width - height, height);
	}

	public void setPhase(IPhase phase)
	{
		this.phase = phase;
	}

	@Override
	public void move()
	{
		phase.move();
	}

	@Override
	public void paint(Graphics2D graphics)
	{
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		phase.paint(graphics);
	}

	public void drawBackground(Graphics2D graphics)
	{
		// clear
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, sizePanel.width, sizePanel.height);
	}

	public void drawInventory(Graphics2D graphics)
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

	public void drawScore(Graphics2D graphics, Dimension size)
	{
		Counter counter = new Counter();

		phase.paintScore(graphics, size, counter);
		counter.add(graphics.getFont().getSize());

	}

	public void drawFPS(Graphics2D graphics, Dimension size)
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

	public static XStream createXStream()
	{
		XStream xStream = new XStream();
		xStream.registerConverter(new Converter() {

			@SuppressWarnings("rawtypes")
			@Override
			public boolean canConvert(Class type)
			{
				return type == CardWeapon.class;
			}

			@Override
			public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
			{
				writer.setValue(((CardWeapon) source).getNameOre());
			}

			@Override
			public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
			{
				String nameOre = reader.getValue();
				return CardWeapons.get(nameOre).orElseThrow(() -> new RuntimeException("No such ore: " + nameOre));
			}

		});

		return xStream;
	}

}
