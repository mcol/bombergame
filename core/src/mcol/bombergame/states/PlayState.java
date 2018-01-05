package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import mcol.bombergame.BomberGame;
import mcol.bombergame.assets.Assets;
import mcol.bombergame.assets.Bomb;
import mcol.bombergame.assets.Bomber;
import mcol.bombergame.assets.Explosion;
import mcol.bombergame.assets.Skyscraper;
import mcol.bombergame.gfx.Background;
import mcol.bombergame.utils.Utils;

public class PlayState extends State {

    /** Maximum number of skyscrapers generated. */
    private static final int MAX_SKYSCRAPER_COUNT = 21;

    /** Maximum number of bombs active at the same time. */
    private static final int MAX_BOMB_COUNT = 3;

    /** Speed of the bomber at the start of the game. */
    private static final int BOMBER_START_SPEED = 10;

    /** Value of the bonus at the start of each level. */
    private static final int LEVEL_BONUS_START = 60;

    /** Drop in bonus at each row. */
    private static final int LEVEL_BONUS_DROP = 5;

    /** Heads-up display. */
    private final HUD hud;

    /** Player object. */
    private final Bomber bomber;

    /** Array of skyscraper objects. */
    private final Array<Skyscraper> skyscrapers;

    /** Array of bomb objects. */
    private final Array<Bomb> bombs;

    /** Array of explosion objects. */
    private final Array<Explosion> explosions;

    /** Width of a skyscraper block. */
    private final int ssWidth;

    /** Number of skyscrapers standing. */
    private int ssCount;

    /** Whether the bomber has collided with a skyscraper. */
    private boolean crashed;

    /** Amount of time passed since the crash. */
    private float timeSinceCrash;

    /** Current level. */
    private int level;

    /** Bonus awarded at each level. */
    private int bonus;

    /** Constructor. */
    public PlayState(BomberGame game, SpriteBatch sb) {
        super(game, sb);
        level = 1;
        hud = new HUD(sb, level);
        background = new Background(Assets.gamebg, 0.2f, 0.5f);
        bomber = new Bomber(0, 0, BOMBER_START_SPEED);
        skyscrapers = new Array<Skyscraper>();
        bombs = new Array<Bomb>();
        explosions = new Array<Explosion>();
        ssWidth = 10;
        crashed = false;
        timeSinceCrash = 0;
        createWorld(level);
    }

    /** Initializes the world. */
    private void createWorld(int level) {
        skyscrapers.clear();
        ssCount = Math.min(3 + level * 2, MAX_SKYSCRAPER_COUNT);
        float gap = (camera.viewportWidth - ssCount * ssWidth) / (ssCount + 1);
        float pos = gap + ssWidth;
        int max = 1 + 2 * level;
        for (int i = 0; i < ssCount; i++)
            skyscrapers.add(new Skyscraper(gap + i * pos, ssWidth,
                                           Utils.randomInteger(level, max)));
        bomber.setPosition(0, (int) camera.viewportHeight - 10);
        bomber.setSpeed(BOMBER_START_SPEED + level * 5);
        bombs.clear();
        bonus = LEVEL_BONUS_START;
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            if (bombs.size < MAX_BOMB_COUNT)
                bombs.add(new Bomb(bomber.getPosition()));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            game.setScreen(new MenuState(game, sb));
    }

    @Override
    protected void update(float dt) {
        handleInput();

        background.update(dt);

        bomber.update(dt);
        if (bomber.getPosition().x > camera.viewportWidth) {
            bomber.nextRow();
            bonus -= LEVEL_BONUS_DROP;
        }

        for (int i = 0; i < skyscrapers.size; i++) {
            Skyscraper ss = skyscrapers.get(i);
            if (ss.collides(bomber.getBounds())) {
                Vector2 position = bomber.getPosition();
                float adj = bomber.getBounds().width;
                explosions.add(new Explosion(position.x + adj, position.y));
                crashed = true;
            }
            for (int j = 0; j < bombs.size; j++) {
                if (ss.collides(bombs.get(j).getBounds())) {
                    Vector2 position = bombs.get(j).getPosition();
                    float adj = ssWidth * 6 / 10;
                    explosions.add(new Explosion(position.x, position.y - adj));
                    hud.increaseScore(1);
                    bombs.removeIndex(j);
                    if (ss.isDestroyed()) {
                        ssCount--;
                        hud.increaseScore(10);
                    }
                }
            }
        }

        // level completed
        if (ssCount == 0 && explosions.size == 0) {
            hud.increaseScore(bonus * level);
            level++;
            hud.setLevel(level);
            createWorld(level);
        }

        for (int i = 0; i < bombs.size; i++) {
            Bomb bb = bombs.get(i);
            bb.update(dt);
            if (bb.getPosition().y <= -1000) {
                bombs.removeIndex(i);
            }
        }

        for (int i = 0; i < explosions.size; i++) {
            Explosion ee = explosions.get(i);
            ee.update(dt);
            if (ee.shouldRemove())
                explosions.removeIndex(i);
        }

        if (crashed) {
            bomber.setPosition(0, 100);
            timeSinceCrash += dt;
            if (timeSinceCrash > 1) {
                game.setScreen(new MenuState(game, sb));
                return;
            }
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // draw the game elements
        sb.begin();

        background.render(sb);

        for (Skyscraper ss : skyscrapers) {
            ss.render(sb);
        }

        for (Bomb bb : bombs)
            bb.render(sb);

        for (Explosion ee : explosions)
            ee.render(sb);

        if (!crashed)
            bomber.render(sb);

        sb.end();

        // draw the hud
        hud.render();
    }
}
