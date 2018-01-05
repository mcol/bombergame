package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mcol.bombergame.BomberGame;
import mcol.bombergame.assets.Assets;
import mcol.bombergame.gfx.Background;

public class MenuState extends State {

    /** Constructor. */
    public MenuState(BomberGame game, SpriteBatch sb) {
        super(game, sb);
        background = new Background(Assets.menubg, 0.2f, 0.5f);
    }

    private void handleInput() {
        if (Gdx.input.justTouched())  {
            game.setScreen(new PlayState(game, sb));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        background.update(dt);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        sb.begin();
        background.render(sb);
        sb.end();

    }
}
