package mcol.bombergame.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mcol.bombergame.BomberGame;
import mcol.bombergame.gfx.Font;

public class HUD implements Disposable {

    /** Format string for the level. */
    private static final String levelFormat = "Level: %2d";

    /** Format string for the score. */
    private static final String scoreFormat = "Score: %3d";

    /** Object to batch the drawing of the graphics. */
    private final SpriteBatch sb;

    /** Scene graph for the HUD information. */
    private final Stage stage;

    /** Label reporting the current level. */
    private final Label levelLabel;

    /** Label reporting the current score. */
    private final Label scoreLabel;

    /** Constructor. */
    public HUD(SpriteBatch sb) {
        this.sb = sb;

        // labels
        Label.LabelStyle labelStyle = new Label.LabelStyle(new Font(26).get(),
                                                           Color.WHITE);
        levelLabel = new Label(String.format(levelFormat, 1), labelStyle);
        scoreLabel = new Label(String.format(scoreFormat, 0), labelStyle);

        // table to organize all the labels
        Table table = new Table().top();
        table.setFillParent(true);
        table.add(levelLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        // stage
        Viewport vp = new FitViewport(BomberGame.WIDTH * 5, BomberGame.HEIGHT * 5,
                                      new OrthographicCamera());
        stage = new Stage(vp, sb);
        stage.addActor(table);
    }

    /** Sets the current level. */
    public void setLevel(int level) {
        levelLabel.setText(String.format(levelFormat, level));
    }

    /** Sets the current score. */
    public void setScore(int score) {
        scoreLabel.setText(String.format(scoreFormat, score));
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
