package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.AsteroidTypes;
import net.infinitycorp.asteroidsecs.components.AsteroidValueComponent;
import net.infinitycorp.asteroidsecs.factories.AsteroidFactory;

import java.util.Random;

public class AsteroidSpawningSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<AsteroidValueComponent> asteroidValueMapper = ComponentMapper.getFor(AsteroidValueComponent.class);

    private Engine engine;
    private Random random;
    private float spawnCooldownTimer;
    private final float defaultCooldownTime = 2;
    private final int MAXASTEROIDVALUE = 10;
    private AsteroidFactory asteroidFactory;

    public AsteroidSpawningSystem(Engine engine) {
        this.engine = engine;
        this.random = new Random();
        this.spawnCooldownTimer = 1;
        this.asteroidFactory = new AsteroidFactory();
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(AsteroidValueComponent.class).get());
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
        AsteroidValueComponent asteroidValueComponent;

        int totalAsteroidValue = 0;

        for (Entity e : entities){
            asteroidValueComponent = asteroidValueMapper.get(e);
            totalAsteroidValue += asteroidValueComponent.value;
        }

        if (spawnCooldownTimer < 0) {
            spawnCooldownTimer = defaultCooldownTime;

            if(totalAsteroidValue >= MAXASTEROIDVALUE){
               return;
            }

            Entity asteroid = asteroidFactory.createAsteroid(randomizeSpawnLocation(), randomizeVelocity(), AsteroidTypes.randomAsteroidType());
            engine.addEntity(asteroid);
        }
        else{
            spawnCooldownTimer -= delta;
        }
    }
}

