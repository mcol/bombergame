package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bomb {

    /** Scaling factor for the texture. */
    private static final float SCALE = 0.15f;

    /** Amount of gravity. */
    private static final int GRAVITY = -1;

    /** Animation representing the bomb. */
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
    public Bomb(Vector2 bomberPosition) {
        float width = Assets.bombTexture[0].getRegionWidth() * SCALE;
        float height = Assets.bombTexture[0].getRegionHeight() * SCALE;
        animation = new Animation<TextureRegion>(0.15f, Assets.bombTexture);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        position = new Vector2(bomberPosition);
        velocity = new Vector2(0, GRAVITY);
        bounds = new Rectangle(position.x, position.y,
                               width, height);
    }

    /** Returns whether the bomb should be removed. */
    public boolean shouldRemove() {
        return position.y < -bounds.height;
    }

    public void update(float dt) {
        stateTime += dt;
        velocity.add(0, GRAVITY);
        position.add(0, velocity.y * dt);
        if (position.y < -bounds.height)
            position.y = -1000;
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

    public float getX() {
        return position.x;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
