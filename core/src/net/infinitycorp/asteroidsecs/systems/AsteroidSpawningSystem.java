package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.AsteroidTypes;
import net.infinitycorp.asteroidsecs.factories.AsteroidFactory;

import java.util.Random;

public class AsteroidSpawningSystem extends EntitySystem {

    private Engine engine;
    private Random random;
    private float spawnCooldownTimer;
    private final float defaultCooldownTime = 2;
    private AsteroidFactory asteroidFactory;

    public AsteroidSpawningSystem(Engine engine) {
        this.engine = engine;
        this.random = new Random();
        this.spawnCooldownTimer = 1;
        this.asteroidFactory = new AsteroidFactory();
    }

    private Vector2 randomizeSpawnLocation() {
        float x = random.nextFloat() * Gdx.graphics.getWidth();
        float y = random.nextFloat() * Gdx.graphics.getHeight();

        return new Vector2(x, y);
    }

    private Vector2 randomizeVelocity() {
        float speed = 100;

        double randomDirection = random.nextDouble() * 2 * Math.PI;
        float velx = (float) Math.cos(randomDirection) * speed;
        float vely = (float) Math.sin(randomDirection) * speed;

        return new Vector2(velx, vely);
    }

    public void update(float delta) {
        if (spawnCooldownTimer < 0) {
            spawnCooldownTimer = defaultCooldownTime;

            Entity asteroid = asteroidFactory.createAsteroid(randomizeSpawnLocation(), randomizeVelocity(), AsteroidTypes.randomAsteroidType());
            engine.addEntity(asteroid);
        }
        else{
            spawnCooldownTimer -= delta;
        }
    }
}

