package net.infinitycorp.asteroidsecs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public enum AsteroidTypes {
    SMALL("asteroid_small.png"),
    MEDIUM("asteroid_medium.png"),
    LARGE("asteroid_large.png");

    private static Random random = new Random();

    public TextureRegion textureRegion;

    AsteroidTypes(String type) {
        this.textureRegion = new TextureRegion(new Texture(type));
    }

    public static AsteroidTypes randomAsteroidType(){
        return AsteroidTypes.values()[random.nextInt(AsteroidTypes.values().length)];
    }
}