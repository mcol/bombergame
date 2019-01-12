package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mcol.bombergame.gfx.Animation;

public class Bomber {

    /** Increase in speed at each row. */
    private static final int SPEED_CHANGE = 3;

    /** Change in height at each row. */
    private static final int POSITION_CHANGE = -8;

    /** Number of frames in the texture. */
    private static final int ANIMATION_FRAMES = 2;

    /** Animation representing the bomber. */
    private final Animation bomberAnimation;

    /** Single frame of the bomber animation. */
    private final Sprite sprite;

    /** Current position. */
    private final Vector2 position;

    /** Current velocity. */
    private final Vector2 velocity;

    /** Collision bounding box. */
    private final Rectangle bounds;

    /** Constructor. */
    public Bomber(int x, int y, float speed) {
        bomberAnimation = new Animation(Assets.bomberTexture,
                                        ANIMATION_FRAMES, 0.5f, 0.15f);
        sprite = bomberAnimation.getCurrentFrame();
        position = new Vector2(x, y);
        velocity = new Vector2(speed, 0);
        bounds = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
    }

    /** Moves the bomber down one row. */
    public void moveDown() {
        position.x = -sprite.getWidth() / 2;
        position.y += POSITION_CHANGE;
        velocity.x += SPEED_CHANGE;
    }

    /** Moves the bomber up one row. */
    public void moveUp() {
        position.y -= 2 * POSITION_CHANGE;
        velocity.x -= SPEED_CHANGE;
    }

    /** Moves the bomber off the screen. */
    public void moveOffscreen() {
        velocity.x += 2;
        velocity.y += 1;
    }

    public void update(float dt) {
        position.add(velocity.x * dt, velocity.y * dt);
        if (position.y < 0)
            position.y = 0;
        bomberAnimation.update(dt);
        sprite.setRegion(bomberAnimation.getCurrentFrame());
        sprite.setPosition(position.x, position.y);
        bounds.setPosition(position);
    }

    public void render(SpriteBatch sb) {
        sprite.draw(sb);
    }

    // getters and setters

    public Vector2 getPosition() {
        return position;
    }

    /** Sets the bomber coordinates. */
    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    /** Sets the bomber speed. */
    public void setSpeed(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }
}
