package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import mcol.bombergame.gfx.ImageBlock;
import mcol.bombergame.utils.Utils;

public class Skyscraper {

    /** Number of skyscraper types in the texture. */
    private static final int SKYSCRAPER_TYPES = 10;

    /** Width of each block in pixels. */
    private static final int BLOCK_WIDTH = 70;

    /** Height of each block in pixels. */
    private static final int BLOCK_HEIGHT = 40;

    /** Blocks in the order they appear in the texture. */
    private static final int BLOCK_RUBBLE = 0;
    private static final int BLOCK_TOP = 1;
    private static final int BLOCK_BODY = 2;
    private static final int BLOCK_BASE = 3;

    /** Image with all skyscraper blocks. */
    private static final Texture texture = new Texture("skyscraper.png");

    /** Skyscraper blocks. */
    private final ImageBlock blocks;

    /** Horizontal coordinate of the skyscraper. */
    private final float position;

    /** Collision bounding box. */
    private final Rectangle bounds;

    /** Skyscraper type. */
    private final int type;

    /** Current height of the skyscraper in blocks. */
    private int blockCount;

    /** Whether the skyscraper has ever been hit. */
    private boolean everHit;

    /** Whether the skyscraper has been completely destroyed. */
    private boolean destroyed;

    /** Constructor. */
    public Skyscraper(float x, int height) {
        blocks = new ImageBlock(texture, 4, SKYSCRAPER_TYPES);
        position = x;
        bounds = new Rectangle(x, 0, BLOCK_WIDTH, (height + 1) * BLOCK_HEIGHT);
        type = Utils.randomInteger(0, SKYSCRAPER_TYPES - 1);
        blockCount = height;
        everHit = false;
        destroyed = false;
    }

    /** Returns whether there is a collision with another entity. */
    public boolean collides(Rectangle entity) {
        boolean collision = !destroyed && entity.overlaps(bounds);
        if (collision) {
            if (everHit)
                blockCount -= 1;
            everHit = true;
            bounds.height -= BLOCK_HEIGHT;
            if (blockCount == 0)
                destroyed = true;
        }
        return collision;
    }

    public void render(SpriteBatch sb) {
        sb.draw(blocks.getFrame(everHit ? BLOCK_RUBBLE : BLOCK_TOP, type),
                position, blockCount * BLOCK_HEIGHT);
        for (int i = 1; i < blockCount; i++)
            sb.draw(blocks.getFrame(BLOCK_BODY, type),
                    position, i * BLOCK_HEIGHT);
        if (blockCount > 0)
            sb.draw(blocks.getFrame(BLOCK_BASE, type), position, 0);
    }

    public void dispose() {
        texture.dispose();
    }

    /** Returns whether the skyscraper has been destroyed. */
    public boolean isDestroyed() {
        return destroyed;
    }
}
