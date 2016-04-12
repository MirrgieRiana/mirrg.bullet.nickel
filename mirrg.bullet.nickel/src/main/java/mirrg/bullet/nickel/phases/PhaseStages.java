package mirrg.bullet.nickel.phases;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;

import mirrg.bullet.nickel.contents.SettingStages;
import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.gui.PanelButtons;
import mirrg.bullet.nickel.phase.IPhase;
import mirrg.bullet.nickel.stage.ISettingStage;

public class PhaseStages extends PhaseGUIBase
{

	public PhaseStages(GameNickel game)
	{
		super(game);
	}

	@Override
	protected synchronized void reform()
	{
		super.reform();

		PanelButtons panelButtons = new PanelButtons(11, 11, 40, 40,
			new Point2D.Double(game.sizeGame.width / 2, game.sizeGame.height / 2));
		components.add(panelButtons);

		Label label = new Label(new Point2D.Double(
			game.sizeGame.width / 2,
			game.sizeGame.height / 2 - panelButtons.rectangle.height / 2 - 30),
			"", new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		components.add(label);

		for (ISettingStage settingStage : SettingStages.values()) {
			Button button = panelButtons.putButton(game, settingStage.getR(), settingStage.getC());
			Boolean cleared = game.clearedStage.get(settingStage.getName());
			if (cleared != null && cleared) {
				button
					.setOnClick(() -> {
						IPhase phase = new PhaseBattle(game, settingStage);
						phase.init();
						game.setPhase(phase);
					})
					.setOnMouseIn(() -> {
						label.text = settingStage.getLabel();
					})
					.setColor(settingStage.getItem().getColor());
			} else {
				button
					.setEnable(false)
					.setColor(new Color(0, 0, 0, 0));
			}
		}

	}

}
