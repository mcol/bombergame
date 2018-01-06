package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mcol.bombergame.gfx.Animation;

public class Explosion {

    /** Number of frames in the texture. */
    private static final int ANIMATION_FRAMES = 16;

    /** Animation representing the explosion. */
    private final Animation explosionAnimation;

    /** Single frame of the explosion animation. */
    private final Sprite sprite;

    /** Constructor. */
    public Explosion(float x, float y) {
        explosionAnimation = new Animation(Assets.explosionTexture,
                                           ANIMATION_FRAMES, 0.4f, 0.03f);
        sprite = explosionAnimation.getCurrentFrame();
        sprite.setPosition(x, y);
    }

    /** Returns whether the explosion should be removed. */
    public boolean shouldRemove() {
        return explosionAnimation.hasPlayedOnce();
    }

    public void update(float dt) {
        explosionAnimation.update(dt);
        sprite.setRegion(explosionAnimation.getCurrentFrame());
    }

    public void render(SpriteBatch sb) {
        sprite.draw(sb);
    }
}
