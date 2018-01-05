package mcol.bombergame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mcol.bombergame.assets.Assets;
import mcol.bombergame.states.MenuState;

public class BomberGame extends Game {

    /** Width of the game viewport. */
    public static final int WIDTH = 120 * 16 / 9;

    /** Height of the game viewport. */
    public static final int HEIGHT = 120;

    /** Name of the game. */
    public static final String TITLE = "Bomber";

    /** Object to batch the drawing of the graphics. */
    private SpriteBatch batch;

    /** Asset manager. */
    private Assets assets;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assets = new Assets();
        assets.getBackgroundImage();
        setScreen(new MenuState(this, batch));
        assets.finishLoading();
    }

    @Override
    public void dispose() {
        batch.dispose();
        assets.dispose();
    }
}
