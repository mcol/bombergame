package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import mcol.bombergame.BomberGame;
import mcol.bombergame.assets.Bomb;
import mcol.bombergame.assets.Bomber;
import mcol.bombergame.assets.Skyscraper;

public class PlayState extends State {

    /** Maximum number of skyscrapers generated. */
    private static final int MAX_SKYSCRAPER_COUNT = 11;
    private static final int SKYSCRAPER_GAP = 2;
    private static final int MAX_BOMB_COUNT = 3;

    /** Current level. */
    private int level;

    /** Heads-up display. */
    private final HUD hud;

    /** Background texture. */
    private final Texture background;

    /** Player object. */
    private final Bomber bomber;

    /** Array of skyscraper objects. */
    private final Array<Skyscraper> skyscrapers;

    /** Array of bomb objects. */
    private final Array<Bomb> bombs;

    /** Constructor. */
    public PlayState(BomberGame game, SpriteBatch sb) {
        super(game, sb);
        level = 1;
        hud = new HUD(sb, level);
        background = new Texture("gamebg.png");
        bomber = new Bomber(0, (int) camera.viewportHeight - 50);
        skyscrapers = new Array<Skyscraper>();
        bombs = new Array<Bomb>();
        createWorld();
    }

    /** Initializes the world. */
    private void createWorld() {
        for (int i = 0; i < MAX_SKYSCRAPER_COUNT; i++)
            skyscrapers.add(new Skyscraper(i * (70 + SKYSCRAPER_GAP), 6));
        bomber.setPosition(0, (int) camera.viewportHeight - 50);
        bombs.clear();
    }

    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (bombs.size < MAX_BOMB_COUNT)
                bombs.add(new Bomb(bomber.getPosition()));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            game.setScreen(new MenuState(game, sb));
    }

    protected void update(float dt) {
        handleInput();

        bomber.update(dt);
        if (bomber.getPosition().x > camera.viewportWidth)
            bomber.nextRow();

        for (int i = 0; i < skyscrapers.size; i++) {
            Skyscraper ss = skyscrapers.get(i);
            if (ss.collides(bomber.getBounds())) {
                game.setScreen(new MenuState(game, sb));
                return;
            }
            for (int j = 0; j < bombs.size; j++) {
                if (ss.collides(bombs.get(j).getBounds())) {
                    hud.increaseScore(1);
                    bombs.removeIndex(j);
                    if (ss.isDestroyed()) {
                        skyscrapers.removeIndex(i);
                        hud.increaseScore(10);
                    }
                }
            }
        }

        // level completed
        if (skyscrapers.size == 0) {
            level++;
            hud.setLevel(level);
            createWorld();
        }

        for (int i = 0; i < bombs.size; i++) {
            Bomb bb = bombs.get(i);
            bb.update(dt);
            if (bb.getPosition().y <= -1000) {
                bombs.removeIndex(i);
                bb.dispose();
            }
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // draw the game elements
        sb.begin();
        sb.draw(background, camera.position.x - camera.viewportWidth / 2, 0);

        for (Skyscraper ss : skyscrapers) {
            ss.render(sb);
        }

        for (Bomb bb : bombs)
            sb.draw(bb.getTexture(), bb.getPosition().x, bb.getPosition().y);
        sb.draw(bomber.getTexture(), bomber.getPosition().x, bomber.getPosition().y);
        sb.end();

        // draw the hud
        hud.render();
    }

    @Override
    public void dispose() {
        background.dispose();
        bomber.dispose();
        for (Skyscraper ss : skyscrapers)
            ss.dispose();
        for (Bomb bb : bombs)
            bb.dispose();
    }
}
