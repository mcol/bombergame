package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mcol.bombergame.BomberGame;
import mcol.bombergame.gfx.Background;

public class MenuState extends State {

    /** Background image. */
    private final Background background;

    /** Constructor. */
    public MenuState(BomberGame game, SpriteBatch sb) {
        super(game, sb);
        background = new Background("menubg.png", 1.0f, 3);
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

    @Override
    public void dispose() {
        background.dispose();
    }
}
