package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.Texture;
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

    /** Image with the animation frames. */
    private final Texture texture;

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
        texture = new Texture("SmallBomb.png");
        bombAnimation = new Animation(texture, ANIMATION_FRAMES, 0.5f, 0.15f);
        sprite = bombAnimation.getCurrentFrame();
        position = new Vector2(bomberPosition);
        velocity = new Vector2(0, GRAVITY);
        bounds = new Rectangle(position.x, position.y,
                               sprite.getWidth(), sprite.getHeight());
    }

    public void update(float dt) {
        velocity.add(0, GRAVITY);
        position.add(0, velocity.y * dt);
        if (position.y < -texture.getHeight())
            position.y = -1000;
        bombAnimation.update(dt);
        bounds.setPosition(position);
    }

    public void render(SpriteBatch sb) {
        sprite.setRegion(bombAnimation.getCurrentFrame());
        sprite.setPosition(position.x, position.y);
        sprite.draw(sb);
    }

    public void dispose() {
        texture.dispose();
    }

    // getters and setters

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
