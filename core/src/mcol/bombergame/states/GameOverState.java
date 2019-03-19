package mcol.bombergame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import mcol.bombergame.BomberGame;
import mcol.bombergame.assets.Assets;
import mcol.bombergame.gfx.Background;
import mcol.bombergame.gfx.Font;

import java.util.Locale;

public class GameOverState extends State {

    /** Scene graph for the game over state. */
    private final Stage stage;

    /** Constructor. */
    public GameOverState(BomberGame game, SpriteBatch sb, int score) {
        super(game, sb);
        background = new Background(Assets.gamebg, 0.2f, 0.5f);

        Label.LabelStyle labelStyle = new Label.LabelStyle(new Font(42).get(),
                                                           Color.WHITE);
        // table to organize all the labels
        Table table = new Table();
        table.setFillParent(true);
        table.add(new Label("Game Over", labelStyle)).expandX();
        table.row();
        table.add(new Label(String.format(Locale.US, "Score: %d", score),
                            labelStyle)).expandX();

        // stage
        stage = new Stage(new FitViewport(BomberGame.WIDTH * 5, BomberGame.HEIGHT * 5,
                                          new OrthographicCamera()), sb);
        stage.addActor(table);
        stage.draw();
    }

    private void handleInput() {
        if (Gdx.input.justTouched())
            game.setScreen(new MenuState(game, sb));
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }

    @Override
    protected void update(float delta) {
        handleInput();
        background.update(delta);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        sb.begin();
        background.render(sb);
        sb.end();

        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}
