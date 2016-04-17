package mirrg.bullet.nickel.phases;

import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;

import mirrg.bullet.nickel.core.DataNickel;
import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.core.SessionNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.phase.IPhase;

public class PhaseTitle extends PhaseGUIBase
{

	private SessionNickel[] sessions;

	public PhaseTitle(GameNickel game)
	{
		super(game);

		sessions = new SessionNickel[3];
		for (int i = 0; i < 3; i++) {

			File file = new File(i + ".save.xml");

			if (file.isFile()) {
				Object data = GameNickel.createXStream().fromXML(file);

				if (data instanceof DataNickel) {
					sessions[i] = new SessionNickel((DataNickel) data, file);
					continue;
				}
			}

			sessions[i] = new SessionNickel(null, file);
		}

	}

	@Override
	protected void reform()
	{
		super.reform();

		components.add(new Label(new Point2D.Double(game.sizeGame.width / 2, game.sizeGame.height * 0.3),
			"Nickel Bullet", new Font(Font.SANS_SERIF, Font.PLAIN, 64)));

		for (int i = 0; i < 3; i++) {
			int i2 = i;

			components.add(new Button(game, new Rectangle2D.Double(
				game.sizeGame.width / 2 - 300,
				game.sizeGame.height * (0.5 + 0.1 * i),
				600, 55))
					.setOnMouseUp(() -> {

						if (sessions[i2].data == null) {
							sessions[i2].data = new DataNickel();
							sessions[i2].data.putDefault();
						}

						IPhase phase = new PhaseStages(game, sessions[i2]);
						phase.init();
						game.setPhase(phase);
					}));
			components.add(new Label(new Point2D.Double(
				game.sizeGame.width / 2,
				game.sizeGame.height * (0.5 + 0.1 * i)),
				sessions[i].data == null ? "New Game" : "Load Game",
				new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.TOP, Label.CENTER));
			if (sessions[i].data != null) {
				components.add(new Label(new Point2D.Double(
					game.sizeGame.width / 2,
					game.sizeGame.height * (0.5 + 0.1 * i) + 30),
					sessions[i].data.getWeaponLeft().map(weapon -> weapon.getNameLocalized()).orElse("")
						+ "／"
						+ sessions[i].data.getWeaponRight().map(weapon -> weapon.getNameLocalized()).orElse(""),
					new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.TOP, Label.CENTER));
			}
		}

	}

}
