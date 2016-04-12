package mirrg.bullet.nickel.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.Hashtable;

import mirrg.bullet.nickel.contents.SettingStages;
import mirrg.bullet.nickel.contents.weapons.bullets.TypeWeaponBullets;
import mirrg.bullet.nickel.gui.Counter;
import mirrg.bullet.nickel.item.Inventory;
import mirrg.bullet.nickel.item.StackWeapon;
import mirrg.bullet.nickel.phase.IPhase;
import mirrg.bullet.nickel.phases.PhaseStages;
import mirrg.bullet.nickel.stage.ISettingStage;

public class GameNickel implements IGame
{

	public PanelNickel panel;

	public Inventory inventory;
	public Hashtable<String, Boolean> clearedStage = new Hashtable<>();

	public int scoreMax;

	public Dimension sizePanel;
	public Dimension sizeGame;
	public Dimension sizeInventory;

	public IPhase phase;

	public StackWeapon weaponMain;
	public StackWeapon weaponSub;

	public GameNickel(PanelNickel panel)
	{
		this.panel = panel;
	}

	@Override
	public void init(int width, int height)
	{

		scoreMax = 100;
		inventory = new Inventory();
		weaponMain = new StackWeapon(TypeWeaponBullets.nickel.create("P", true));
		inventory.addStack(weaponMain);
		weaponSub = new StackWeapon(TypeWeaponBullets.calcite.create("P", true));
		inventory.addStack(weaponSub);
		clearedStage.put(SettingStages.landOpening.name, true);

		updateLayout(width, height);
		{
			IPhase phase = new PhaseStages(this);
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

		graphics.setColor(Color.white);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));

		graphics.drawString("HiScore: " + scoreMax,
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize());

		phase.paintScore(graphics, size, counter);
		counter.add(graphics.getFont().getSize());

		graphics.drawString("メインショット：",
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize());

		graphics.drawString("　" + weaponMain.getName(),
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize());

		graphics.drawString("サブショット：",
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize());

		graphics.drawString("　" + weaponSub.getName(),
			0, counter.value + graphics.getFont().getSize());
		counter.add(graphics.getFont().getSize());
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

	public void onClear(ISettingStage settingStage)
	{
		ISettingStage s;

		s = SettingStages.get(settingStage.getR() - 1, settingStage.getC());
		if (s != null) clear(s);

		s = SettingStages.get(settingStage.getR() + 1, settingStage.getC());
		if (s != null) clear(s);

		s = SettingStages.get(settingStage.getR(), settingStage.getC() - 1);
		if (s != null) clear(s);

		s = SettingStages.get(settingStage.getR(), settingStage.getC() + 1);
		if (s != null) clear(s);
	}

	public void clear(ISettingStage settingStage)
	{
		clearedStage.put(settingStage.getName(), true);
	}

}
