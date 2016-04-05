package mirrg.bullet.nickel;

import mirrg.applet.nitrogen.AppletNitrogen;
import mirrg.applet.nitrogen.HAppletNitrogen;
import mirrg.applet.nitrogen.HAppletNitrogen.ResponceApplyStandard;
import mirrg.applet.nitrogen.NitrogenEventApplet;
import mirrg.applet.nitrogen.NitrogenEventComponent;
import mirrg.applet.nitrogen.modules.threading.NitrogenEventGameThread;

public class PanelNickel extends AppletNitrogen
{

	private static final long serialVersionUID = 6370904732290917883L;

	public IGame game;
	public ResponceApplyStandard responceApplyStandard;

	public PanelNickel()
	{
		responceApplyStandard = HAppletNitrogen.applyStandard(this);
		responceApplyStandard.moduleGameThread.objectiveFPS = 30;

		//

		getEventManager().register(NitrogenEventApplet.Init.class, event -> {

		});
		getEventManager().register(NitrogenEventGameThread.Init.class, event -> {
			game.init(getWidth(), getHeight());
		});
		getEventManager().register(NitrogenEventComponent.Resized.class, event -> {
			game.resized(getWidth(), getHeight());
		});
		getEventManager().register(NitrogenEventGameThread.Tick.class, event -> {
			synchronized (game) {
				game.move();
			}
		});
		getEventManager().register(NitrogenEventGameThread.PostTick.class, event -> {
			responceApplyStandard.moduleInputStatus.spend();
		});
		getEventManager().register(NitrogenEventGameThread.Render.class, event -> {
			repaint();
		});
		getEventManager().register(NitrogenEventApplet.Paint.class, event -> {

			synchronized (game) {
				game.paint(responceApplyStandard.moduleTripleBuffer.getBufferDirty().getGraphics());
			}

			responceApplyStandard.moduleTripleBuffer.flip();

			event.graphics.drawImage(responceApplyStandard.moduleTripleBuffer.getBufferSafety().getBuffer(), 0, 0, this);
		});

	}

	public void setGame(IGame game)
	{
		this.game = game;
	}

}
