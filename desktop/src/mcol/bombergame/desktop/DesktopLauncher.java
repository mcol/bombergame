package mcol.bombergame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mcol.bombergame.BomberGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BomberGame.WIDTH;
		config.height = BomberGame.HEIGHT;
		config.title = BomberGame.TITLE;
		new LwjglApplication(new BomberGame(), config);
	}
}
