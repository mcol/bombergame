package mcol.bombergame.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture menubg;
    public static Texture gamebg;
    public static Texture bomberTexture;
    public static Texture bombTexture;
    public static Texture skyscraperTexture;
    public static Texture explosionTexture;
    public static Texture hudBomberTexture;
    public static Texture hudBombTexture;

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
        assets.load("explosion.png", Texture.class);
        assets.load("hud-bomber.png", Texture.class);
        assets.load("hud-bomb.png", Texture.class);
        assets.update();
    }

    /** Loads the first background image. */
    public void getBackgroundImage() {
        while (!assets.isLoaded("menubg.png"))
            assets.update();
        menubg = assets.get("menubg.png", Texture.class);
    }

    /** Ensures that all assets have been loaded. */
    public void finishLoading() {
        assets.finishLoading();
        gamebg = assets.get("gamebg.png", Texture.class);
        bomberTexture = assets.get("bomber.png", Texture.class);
        bombTexture = assets.get("SmallBomb.png", Texture.class);
        skyscraperTexture = assets.get("skyscraper.png", Texture.class);
        explosionTexture = assets.get("explosion.png", Texture.class);
        hudBomberTexture = assets.get("hud-bomber.png", Texture.class);
        hudBombTexture = assets.get("hud-bomb.png", Texture.class);
    }

    public void dispose() {
        assets.dispose();
    }
}
