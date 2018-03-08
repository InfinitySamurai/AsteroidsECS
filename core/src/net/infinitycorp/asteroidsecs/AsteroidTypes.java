package net.infinitycorp.asteroidsecs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public enum AsteroidTypes {
    SMALL("asteroid_small.png", 1),
    MEDIUM("asteroid_medium.png", 2),
    LARGE("asteroid_large.png", 3);

    private static Random random = new Random();

    public TextureRegion textureRegion;
    public int asteroidValue;

    AsteroidTypes(String type, int value) {
        this.textureRegion = new TextureRegion(new Texture(type));
        this.asteroidValue = value;
    }

    public static AsteroidTypes randomAsteroidType() {
        return AsteroidTypes.values()[random.nextInt(AsteroidTypes.values().length)];
    }
}