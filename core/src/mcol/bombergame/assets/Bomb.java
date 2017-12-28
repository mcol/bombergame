package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mcol.bombergame.gfx.Animation;

public class Bomb {

    private static final int GRAVITY = -5;
    private static final int ANIMATION_FRAMES = 6;

    private final Texture texture;
    private final Animation bombAnimation;
    private final Vector2 position;
    private final Vector2 velocity;
    private final Rectangle bounds;

    /** Constructor. */
    public Bomb(Vector2 bomberPosition) {
        texture = new Texture("SmallBomb.png");
        bombAnimation = new Animation(texture, ANIMATION_FRAMES, 0.5f);
        position = new Vector2(bomberPosition);
        velocity = new Vector2(0, GRAVITY);
        bounds = new Rectangle(position.x, position.y,
                               texture.getWidth() / ANIMATION_FRAMES,
                               texture.getHeight());
    }

    public void update(float dt) {
        velocity.add(0, GRAVITY);
        position.add(0, velocity.y * dt);
        if (position.y < -texture.getHeight())
            position.y = -1000;
        bombAnimation.update(dt);
        bounds.setPosition(position);
    }

    public void dispose() {
        texture.dispose();
    }

    // getters and setters

    public TextureRegion getTexture() {
        return bombAnimation.getCurrentFrame();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
