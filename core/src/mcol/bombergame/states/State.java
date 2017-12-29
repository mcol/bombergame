package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mcol.bombergame.BomberGame;

public abstract class State implements Screen {

    protected final OrthographicCamera camera;
    private final Viewport viewport;

    protected final BomberGame game;
    protected final SpriteBatch sb;

    /** Constructor. */
    protected State(BomberGame game, SpriteBatch sb) {
        this.game = game;
        this.sb = sb;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new FitViewport(BomberGame.WIDTH, BomberGame.HEIGHT, camera);
    }

    protected abstract void handleInput();
    protected abstract void update(float delta);
    public abstract void dispose();

    public void render(float delta) {

        update(delta);

        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /** Resizes the dimension of the viewport. */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.setToOrtho(false);
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
