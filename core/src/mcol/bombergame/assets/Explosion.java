package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Explosion {

    /** Scaling factor for the texture. */
    private static final float SCALE = 0.03f;

    /** Animation representing the explosion. */
    private final Animation<TextureRegion> animation;

    /** Current position. */
    private Vector2 position;

    /** Horizontal and vertical dimensions. */
    private final float width, height;

    /** Time since the animation has started. */
    private float stateTime;

    /** Constructor. */
    public Explosion(float x, float y) {
        this.width = Assets.explosionTexture[0].getRegionWidth() * SCALE;
        this.height = Assets.explosionTexture[0].getRegionHeight() * SCALE;
        animation = new Animation<TextureRegion>(0.05f, Assets.explosionTexture);
        position = new Vector2(x, y);
    }

    /** Returns whether the explosion should be removed. */
    public boolean shouldRemove() {
        return animation.isAnimationFinished(stateTime);
    }

    public void update(float dt) {
        stateTime += dt;
    }

    public void render(SpriteBatch sb) {
        sb.draw(animation.getKeyFrame(stateTime),
                position.x, position.y, width, height);
    }
}
