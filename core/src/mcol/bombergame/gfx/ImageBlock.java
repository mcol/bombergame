package mcol.bombergame.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class ImageBlock {

    /** Number of rows in the texture. */
    private final int rowCount;

    /** Array of image blocks. */
    private final Array<Sprite> blocks;

    /** Constructor. */
    public ImageBlock(Texture texture, int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.blocks = new Array<Sprite>();

        // dimensions of each block
        int width = texture.getWidth() / columnCount;
        int height = texture.getHeight() / rowCount;

        // extract the blocks in column-major order
        for (int j = 0; j < columnCount; j++) {
            for (int i = 0; i < rowCount; i++)
                blocks.add(new Sprite(texture, j * width, i * height,
                                      width, height));
        }
    }

    /** Returns the frame at the given coordinates. */
    public Sprite getFrame(int row, int col) {
        return blocks.get(row + col * rowCount);
    }
}
