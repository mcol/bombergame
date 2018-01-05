package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import mcol.bombergame.BomberGame;
import mcol.bombergame.assets.Assets;
import mcol.bombergame.assets.Bomb;
import mcol.bombergame.assets.Bomber;
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

    /** Number of skyscrapers standing. */
    private int ssCount;

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
        createWorld(level);
    }

    /** Initializes the world. */
    private void createWorld(int level) {
        skyscrapers.clear();
        ssCount = Math.min(3 + level * 2, MAX_SKYSCRAPER_COUNT);
        int pos = (int) camera.viewportWidth / ssCount;
        int gap = ((int) camera.viewportWidth - (ssCount - 1) * pos) / ssCount;
        int max = 1 + 2 * level;
        for (int i = 0; i < ssCount; i++)
            skyscrapers.add(new Skyscraper(gap + i * pos,
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
                game.setScreen(new MenuState(game, sb));
                return;
            }
            for (int j = 0; j < bombs.size; j++) {
                if (ss.collides(bombs.get(j).getBounds())) {
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
        if (ssCount == 0) {
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
        bomber.render(sb);
        sb.end();

        // draw the hud
        hud.render();
    }
}
