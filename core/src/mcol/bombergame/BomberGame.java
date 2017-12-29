package mcol.bombergame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mcol.bombergame.states.MenuState;

public class BomberGame extends Game {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Bomber";

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuState(this, batch));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
