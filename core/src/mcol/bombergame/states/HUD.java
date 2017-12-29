package mcol.bombergame.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mcol.bombergame.BomberGame;

public class HUD implements Disposable {

    private final SpriteBatch sb;
    private final Stage stage;

    /** Label reporting the current level. */
    private final Label levelLabel;

    /** Label reporting the current score. */
    private final Label scoreLabel;

    /** Current level. */
    private int level;

    /** Current score. */
    private int score;

    /** Constructor. */
    public HUD(SpriteBatch sb) {
        this.sb = sb;
        this.score = 0;
        this.level = 1;

        // labels
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(),
                                                           Color.WHITE);
        levelLabel = new Label(String.format("Level: %2d", score), labelStyle);
        scoreLabel = new Label(String.format("%3d", score), labelStyle);

        // table to organize all the labels
        Table table = new Table().top();
        table.setFillParent(true);
        table.add(levelLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        // stage
        Viewport vp = new FitViewport(BomberGame.WIDTH, BomberGame.HEIGHT,
                                      new OrthographicCamera());
        stage = new Stage(vp, sb);
        stage.addActor(table);
    }

    /** Increases the current level. */
    public void increaseLevel() {
        level++;
        levelLabel.setText(String.format("Level: %2d", level));
    }

    /** Increases the current score. */
    public void increaseScore(int points) {
        score += points;
        scoreLabel.setText(String.format("%3d", score));
    }

    public void render() {
        sb.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
