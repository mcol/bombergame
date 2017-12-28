package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mcol.bombergame.BomberGame;

public class MenuState extends State {

    /** Background texture. */
    private final Texture background;

    /** Constructor. */
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menubg.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())  {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, BomberGame.WIDTH, BomberGame.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
