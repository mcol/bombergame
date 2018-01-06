package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mcol.bombergame.gfx.Animation;

public class Bomb {

    /** Amount of gravity. */
    private static final int GRAVITY = -1;

    /** Number of frames in the texture. */
    private static final int ANIMATION_FRAMES = 6;

    /** Animation representing the bomb. */
    private final Animation bombAnimation;

    /** Single frame of the bomber animation. */
    private final Sprite sprite;

    /** Current position. */
    private final Vector2 position;

    /** Current velocity. */
    private final Vector2 velocity;

    /** Collision bounding box. */
    private final Rectangle bounds;

    /** Constructor. */
    public Bomb(Vector2 bomberPosition) {
        bombAnimation = new Animation(Assets.bombTexture,
                                      ANIMATION_FRAMES, 0.5f, 0.15f);
        sprite = bombAnimation.getCurrentFrame();
        position = new Vector2(bomberPosition);
        velocity = new Vector2(0, GRAVITY);
        bounds = new Rectangle(position.x, position.y,
                               sprite.getWidth(), sprite.getHeight());
    }

    /** Returns whether the bomb should be removed. */
    public boolean shouldRemove() {
        return position.y < -bounds.height;
    }

    public void update(float dt) {
        velocity.add(0, GRAVITY);
        position.add(0, velocity.y * dt);
        if (position.y < -sprite.getHeight())
            position.y = -1000;
        bombAnimation.update(dt);
        bounds.setPosition(position);
    }

    public void render(SpriteBatch sb) {
        sprite.setRegion(bombAnimation.getCurrentFrame());
        sprite.setPosition(position.x, position.y);
        sprite.draw(sb);
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
