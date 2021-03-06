package mcol.bombergame.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mcol.bombergame.BomberGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        config.title = BomberGame.TITLE;
        config.addIcon("icon.png", FileType.Internal);
        new LwjglApplication(new BomberGame(), config);
    }
}
