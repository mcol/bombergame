package mcol.bombergame.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {

    private final Array<TextureRegion> frames;
    private final float frameDuration;
    private float currentFrameTime;
    private int index;

    /** Constructor. */
    public Animation(Texture texture, int frameCount, float cycleTime) {
        int frameWidth = texture.getWidth() / frameCount;
        this.frames = new Array<TextureRegion>();
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(texture, i * frameWidth, 0,
                                         frameWidth, texture.getHeight()));
        }
        this.frameDuration = cycleTime / frameCount;
        this.currentFrameTime = 0;
        this.index = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > frameDuration) {
            index++;
            currentFrameTime = 0;
        }
        if (index >= frames.size)
            index = 0;
    }

    public TextureRegion getCurrentFrame() {
        return frames.get(index);
    }
}
