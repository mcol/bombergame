package mcol.bombergame.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mcol.bombergame.utils.Utils;

public class Background {

    /** Image to display in the background. */
    private final Sprite background;

    /** Speed of movement of the background. */
    private final float speed;

    /** Width of the background image after scaling. */
    private final float scaledWidth;

    /** Current horizontal coordinate. */
    private float x;

    /** Constructor. */
    public Background(String fileName, float scale, float bgSpeed) {
        background = new Sprite(new Texture(fileName));
        background.setScale(scale);
        background.setOrigin(0, 0);
        speed = bgSpeed;
        scaledWidth = background.getWidth() * scale;
        x = -Utils.randomInteger(0, (int) scaledWidth / 2);
    }

    public void update(float delta) {
        x = (x - speed * delta) % scaledWidth;
    }

    public void render(SpriteBatch sb) {
        sb.disableBlending();
        background.setPosition(x, 0);
        background.draw(sb);
        background.setPosition(x + scaledWidth, 0);
        background.draw(sb);
        sb.enableBlending();
    }

    public void dispose() {
        background.getTexture().dispose();
    }
}
