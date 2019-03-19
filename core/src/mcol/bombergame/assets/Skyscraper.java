package mcol.bombergame.assets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import mcol.bombergame.gfx.ImageBlock;

public class Skyscraper {

    /** Number of skyscraper types in the texture. */
    private static final int SKYSCRAPER_TYPES = 10;

    /** Blocks in the order they appear in the texture. */
    private static final int BLOCK_RUBBLE = 0;
    private static final int BLOCK_TOP = 1;
    private static final int BLOCK_BODY = 2;
    private static final int BLOCK_BASE = 3;

    /** Skyscraper blocks. */
    private final ImageBlock blocks;

    /** Horizontal coordinate of the skyscraper. */
    private final float position;

    /** Width of each block. */
    private final int blockWidth;

    /** Height of each block. */
    private final int blockHeight;

    /** Collision bounding box. */
    private final Rectangle bounds;

    /** Skyscraper type. */
    private final int type;

    /** Starting height of the skyscraper in blocks. */
    private final int startBlockCount;

    /** Current height of the skyscraper in blocks. */
    private int blockCount;

    /** Whether the skyscraper has ever been hit. */
    private boolean everHit;

    /** Whether the skyscraper has been completely destroyed. */
    private boolean destroyed;

    /** Constructor. */
    public Skyscraper(float x, int width, int height) {
        blocks = new ImageBlock(Assets.skyscraperTexture, 4, SKYSCRAPER_TYPES);
        position = x;
        blockWidth = width;
        blockHeight = width * 6 / 10;
        bounds = new Rectangle(x, 0, blockWidth, (height + 1) * blockHeight);
        type = MathUtils.random(SKYSCRAPER_TYPES - 1);
        startBlockCount = height;
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
            bounds.height -= blockHeight;
            if (blockCount == 0)
                destroyed = true;
        }
        return collision;
    }

    /** Extracts the skyscraper block to draw. */
    private Sprite extractBlock(int block, int height) {
        Sprite sprite = blocks.getFrame(block, type);
        sprite.setSize(blockWidth, blockHeight);
        sprite.setPosition(position, height * blockHeight);
        return sprite;
    }

    public void render(SpriteBatch sb) {
        extractBlock(everHit ? BLOCK_RUBBLE : BLOCK_TOP, blockCount).draw(sb);

        for (int i = 1; i < blockCount; i++)
            extractBlock(BLOCK_BODY, i).draw(sb);

        if (blockCount > 0)
            extractBlock(BLOCK_BASE, 0).draw(sb);
    }

    // getters and setters

    public float getX() {
        return position;
    }

    public int getStartBlockCount() {
        return startBlockCount;
    }

    /** Returns whether the skyscraper has been destroyed. */
    public boolean isDestroyed() {
        return destroyed;
    }
}
