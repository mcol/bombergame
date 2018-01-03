package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mcol.bombergame.BomberGame;

public class MenuState extends State {

    /** Background texture. */
    private final Texture background;

    /** Constructor. */
    public MenuState(BomberGame game, SpriteBatch sb) {
        super(game, sb);
        background = new Texture("menubg.png");
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
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        sb.begin();
        sb.draw(background, 0, 0, BomberGame.WIDTH, BomberGame.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
