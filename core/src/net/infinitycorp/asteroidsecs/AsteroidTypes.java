package net.infinitycorp.asteroidsecs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public enum AsteroidTypes {
    SMALL("asteroid_small.png", 1, null),
    MEDIUM("asteroid_medium.png", 2, AsteroidTypes.SMALL),
    LARGE("asteroid_large.png", 3, AsteroidTypes.MEDIUM);

    private static Random random = new Random();

    public TextureRegion textureRegion;
    public int asteroidValue;
    public AsteroidTypes nextSmallerAsteroid;

    AsteroidTypes(String type, int value, AsteroidTypes nextSmallerAsteroid) {
        this.textureRegion = new TextureRegion(new Texture(type));
        this.asteroidValue = value;
        this.nextSmallerAsteroid = nextSmallerAsteroid;
    }

    public static AsteroidTypes randomAsteroidType() {
        return AsteroidTypes.values()[random.nextInt(AsteroidTypes.values().length)];
    }
}