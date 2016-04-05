package mirrg.bullet.nickel.phases;

import java.awt.geom.Point2D;

import mirrg.bullet.nickel.GameNickel;
import mirrg.bullet.nickel.gui.PanelButtons;
import mirrg.bullet.nickel.stages.Stage1;
import mirrg.bullet.nickel.stages.Stage2;
import mirrg.bullet.nickel.stages.Stage3;

public class PhaseStages extends PhaseGUIBase
{

	public PhaseStages(GameNickel game)
	{
		super(game);

		{
			PanelButtons panelButtons = new PanelButtons(10, 10, new Point2D.Double(game.sizeGame.width / 2, game.sizeGame.height / 2));
			components.add(panelButtons);

			panelButtons.putButton(game, 0, 0, () -> {
				game.setPhase(new PhaseBattle(game, new Stage1()));
			});
			panelButtons.putButton(game, 0, 1, () -> {
				game.setPhase(new PhaseBattle(game, new Stage2()));
			});
			panelButtons.putButton(game, 0, 2, () -> {
				game.setPhase(new PhaseBattle(game, new Stage3()));
			});
		}

	}

}
