package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mcol.bombergame.gfx.ImageSequence;

public class Skyscraper {

    public static final int WIDTH = 60;
    private static final int FRAME_COUNT = 7;

    private final Texture texture;
    private final ImageSequence frames;
    private final Vector2 position;
    private final Rectangle bounds;
    private int currentHeight;
    private boolean destroyed;

    /** Constructor. */
    public Skyscraper(float x) {
        texture = new Texture("skyscraper.png");
        frames = new ImageSequence(texture, FRAME_COUNT);
        position = new Vector2(x, 0);
        bounds = new Rectangle(x, position.y,
                               texture.getWidth() / FRAME_COUNT,
                               texture.getHeight());
        currentHeight = texture.getHeight();
        destroyed = false;
    }

    public boolean collides(Rectangle bomb) {
        boolean collision = bomb.overlaps(bounds);
        if (collision) {
            currentHeight -= 40;
            bounds.height = currentHeight;
            if (currentHeight <= 0)
                destroyed = true;
            frames.nextFrame();
        }
        return collision;
    }

    public void dispose() {
        texture.dispose();
    }

    // getters and setters

    public TextureRegion getTexture() {
        return frames.getCurrentFrame();
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
