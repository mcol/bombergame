package mcol.bombergame.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class Animation {

    /** Array of sprites to animate. */
    private final Array<Sprite> frames;

    /** Amount of time spent on each frame. */
    private final float frameDuration;

    /** Amount of time spent on the current frame. */
    private float currentFrameTime;

    /** Index of the current animation frame. */
    private int index;

    /** Whether the animation has played at least once. */
    private boolean playedOnce;

    /** Constructor. */
    public Animation(Texture texture, int frameCount,
                     float cycleTime, float scale) {
        int frameWidth = texture.getWidth() / frameCount;
        this.frames = new Array<Sprite>();
        for (int i = 0; i < frameCount; i++) {
            Sprite sprite = new Sprite(texture, i * frameWidth, 0,
                                       frameWidth, texture.getHeight());
            sprite.setSize(frameWidth * scale, texture.getHeight() * scale);
            frames.add(sprite);
        }
        this.frameDuration = cycleTime / frameCount;
        this.currentFrameTime = 0;
        this.index = 0;
        this.playedOnce = false;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > frameDuration) {
            index++;
            currentFrameTime = 0;
        }
        if (index >= frames.size) {
            index = 0;
            playedOnce = true;
        }
    }

    public Sprite getCurrentFrame() {
        return frames.get(index);
    }

    /** Returns whether the animation has played at least once. */
    public boolean hasPlayedOnce() {
        return playedOnce;
    }
}
