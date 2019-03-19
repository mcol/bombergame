package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bomber {

    /** Scaling factor for the texture. */
    private static final float SCALE = 0.15f;

    /** Increase in speed at each row. */
    private static final int SPEED_CHANGE = 3;

    /** Change in height at each row. */
    private static final int POSITION_CHANGE = -8;

    /** Animation representing the bomber. */
    private final Animation<TextureRegion> animation;

    /** Current position. */
    private final Vector2 position;

    /** Current velocity. */
    private final Vector2 velocity;

    /** Collision bounding box. */
    private final Rectangle bounds;

    /** Time since the animation has started. */
    private float stateTime;

    /** Constructor. */
    public Bomber(int x, int y, float speed) {
        float width = Assets.bomberTexture[0].getRegionWidth() * SCALE;
        float height = Assets.bomberTexture[0].getRegionHeight() * SCALE;
        animation = new Animation<TextureRegion>(0.25f, Assets.bomberTexture);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        position = new Vector2(x, y);
        velocity = new Vector2(speed, 0);
        bounds = new Rectangle(x, y, width, height);
    }

    /** Moves the bomber down one row. */
    public void moveDown() {
        position.x = -bounds.width / 2;
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
        stateTime += dt;
        position.add(velocity.x * dt, velocity.y * dt);
        if (position.y < 0)
            position.y = 0;
        bounds.setPosition(position);
    }

    public void render(SpriteBatch sb) {
        sb.draw(animation.getKeyFrame(stateTime),
                position.x, position.y, bounds.width, bounds.height);
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
