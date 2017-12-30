package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mcol.bombergame.gfx.Animation;

public class Bomber {

    /** Speed at the start of the game. */
    private static final int SPEED_START = 80;

    /** Increase in speed at each row. */
    private static final int SPEED_CHANGE = 15;

    /** Change in height at each row. */
    private static final int POSITION_CHANGE = -40;

    /** Number of frames in the texture. */
    private static final int FRAME_COUNT = 2;

    /** Image with the animation frames. */
    private final Texture texture;

    /** Animation representing the bomber. */
    private final Animation bomberAnimation;

    /** Current position. */
    private final Vector2 position;

    /** Collision bounding box. */
    private final Rectangle bounds;

    /** Current speed. */
    private float xMove;

    /** Constructor. */
    public Bomber(int x, int y) {
        texture = new Texture("bomber.png");
        bomberAnimation = new Animation(texture, FRAME_COUNT, 0.5f);
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, texture.getWidth() / FRAME_COUNT, texture.getHeight());
        xMove = SPEED_START;
    }

    /** Sets the the bomber coordinates. */
    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    /** Moves the bomber to the next row. */
    public void nextRow() {
        position.x = -texture.getWidth() / FRAME_COUNT / 2;
        position.y += POSITION_CHANGE;
        xMove += SPEED_CHANGE;
    }

    public void update(float dt) {
        position.add(xMove * dt, 0);
        if (position.y < 0)
            position.y = 0;
        bomberAnimation.update(dt);
        bounds.setPosition(position);
    }

    public void dispose() {
        texture.dispose();
    }

    // getters and setters

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return bomberAnimation.getCurrentFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
