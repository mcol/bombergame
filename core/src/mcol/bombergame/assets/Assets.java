package mcol.bombergame.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture menubg;
    public static Texture gamebg;
    public static Texture bomberTexture;
    public static Texture bombTexture;
    public static Texture skyscraperTexture;

    /** Container for all assets. */
    private final AssetManager assets;

    /** Constructor. */
    public Assets() {
        assets = new AssetManager();
        assets.load("menubg.png", Texture.class);
        assets.load("gamebg.png", Texture.class);
        assets.load("bomber.png", Texture.class);
        assets.load("SmallBomb.png", Texture.class);
        assets.load("skyscraper.png", Texture.class);
        assets.update();
    }

    /** Ensures that all assets have been loaded. */
    public void init() {
        assets.finishLoading();
        menubg = assets.get("menubg.png", Texture.class);
        gamebg = assets.get("gamebg.png", Texture.class);
        bomberTexture = assets.get("bomber.png", Texture.class);
        bombTexture = assets.get("SmallBomb.png", Texture.class);
        skyscraperTexture = assets.get("skyscraper.png", Texture.class);
    }

    public void dispose() {
        assets.dispose();
    }
}
