package mcol.bombergame.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Font {

    /** Name of the source font used. */
    private static final String FONT_NAME = "SourceCodePro-Regular.otf";

    /** The font generated for the game. */
    BitmapFont font;

    /** Constructor. */
    public Font(int size) {
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
        FreeTypeFontParameter par = new FreeTypeFontParameter();
        par.size = size;
        par.magFilter = TextureFilter.Linear;
        font = gen.generateFont(par);
        gen.dispose();
    }

    /** Returns the font generated. */
    public BitmapFont get() {
        return font;
    }
}
