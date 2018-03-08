package net.infinitycorp.asteroidsecs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.infinitycorp.asteroidsecs.components.VisualComponent;

import java.util.Random;

public enum AsteroidType {
    SMALL("asteroid_small.png", 1, null),
    MEDIUM("asteroid_medium.png", 2, AsteroidType.SMALL),
    LARGE("asteroid_large.png", 3, AsteroidType.MEDIUM);

    private static Random random = new Random();

    public VisualComponent visual;
    public int asteroidValue;
    public AsteroidType nextSmallerAsteroid;

    AsteroidType(String type, int value, AsteroidType nextSmallerAsteroid) {
        this.visual = new VisualComponent(new TextureRegion(new Texture(type)), 0.75f);
        this.asteroidValue = value;
        this.nextSmallerAsteroid = nextSmallerAsteroid;
    }

    public static AsteroidType randomAsteroidType() {
        return AsteroidType.values()[random.nextInt(AsteroidType.values().length)];
    }
}