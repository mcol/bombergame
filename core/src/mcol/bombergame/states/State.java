package mcol.bombergame.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

    protected final GameStateManager gsm;
    protected final OrthographicCamera camera;

    /** Constructor. */
    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
