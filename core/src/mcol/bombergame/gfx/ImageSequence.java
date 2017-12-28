package mcol.bombergame.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ImageSequence {

    private final Array<TextureRegion> frames;
    private int index;
    private boolean loopedOnce;

    /** Constructor. */
    public ImageSequence(Texture texture, int frameCount) {
        int frameWidth = texture.getWidth() / frameCount;
        this.frames = new Array<TextureRegion>();
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(texture, i * frameWidth, 0,
                                         frameWidth, texture.getHeight()));
        }
        this.index = 0;
        this.loopedOnce = false;
    }

    public void nextFrame() {
        index++;
        if (index == frames.size) {
            index = 0;
            loopedOnce = true;
        }
    }

    public TextureRegion getCurrentFrame() {
        return frames.get(index);
    }

    public boolean hasLoopedOnce() {
        return loopedOnce;
    }
}
