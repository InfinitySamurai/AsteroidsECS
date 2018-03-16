package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.AsteroidType;
import net.infinitycorp.asteroidsecs.components.AsteroidTypeComponent;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.ShipComponent;
import net.infinitycorp.asteroidsecs.events.AsteroidDestructionListener;
import net.infinitycorp.asteroidsecs.factories.AsteroidFactory;

import java.util.Random;

public class AsteroidSpawningSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<AsteroidTypeComponent> asteroidValueMapper = ComponentMapper.getFor(AsteroidTypeComponent.class);
    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);

    private Engine engine;
    private Random random;
    private Entity ship;
    private float spawnCooldownTimer;
    private final float defaultCooldownTime = 2;
    private final int MAXASTEROIDVALUE = 10;
    private AsteroidFactory asteroidFactory;
    private AsteroidDestructionListener listener;

    public AsteroidSpawningSystem(Engine engine) {
        this.engine = engine;
        this.random = new Random();
        this.spawnCooldownTimer = 1;
        this.asteroidFactory = new AsteroidFactory();
        this.listener = new AsteroidDestructionListener();
        engine.addEntityListener(listener);
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(AsteroidTypeComponent.class).get());
        ImmutableArray<Entity> ships = engine.getEntitiesFor(Family.all(ShipComponent.class).get());

        if (ships.size() > 0) {
            ship = ships.first();
        }
    }

    private Vector2 getRandomPositionNotNearPlayer(Vector2 playerPosition, float safeRadius) {
        while (true) {
            Vector2 potentialSpawnPosition = new Vector2().setToRandomDirection().scl(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if (potentialSpawnPosition.dst(playerPosition) > safeRadius) {
                return potentialSpawnPosition;
            }
        }
    }

    public void update(float delta) {
        AsteroidTypeComponent asteroidTypeComponent;

        int totalAsteroidValue = 0;

        for (Entity e : entities) {
            asteroidTypeComponent = asteroidValueMapper.get(e);
            totalAsteroidValue += asteroidTypeComponent.type.asteroidValue;
        }
        regularAsteroidSpawner(totalAsteroidValue, delta);
        spawnBrokenAsteroids();
    }

    private void spawnBrokenAsteroids() {
        Entity destroyedAsteroid = listener.getDestroyedAsteroid();
        if (destroyedAsteroid != null) {
            AsteroidType newAsteroidType = asteroidValueMapper.get(destroyedAsteroid).type.nextSmallerAsteroid;
            PositionComponent position = positionMapper.get(destroyedAsteroid);
            if (newAsteroidType != null) {
                Vector2 spawnPosition = new Vector2(position.x, position.y);

                engine.addEntity(asteroidFactory.createRandomizedAsteroid(spawnPosition, newAsteroidType, true));
                engine.addEntity(asteroidFactory.createRandomizedAsteroid(spawnPosition, newAsteroidType, true));
            }
        }
    }

    private void regularAsteroidSpawner(int totalAsteroidValue, float delta) {
        float playerSpawnRadius = 50;
        PositionComponent playerPositionComponent = positionMapper.get(ship);
        Vector2 playerPosition = new Vector2(playerPositionComponent.x, playerPositionComponent.y);

        if (spawnCooldownTimer < 0) {
            spawnCooldownTimer = defaultCooldownTime;

            if (totalAsteroidValue >= MAXASTEROIDVALUE) {
                return;
            }


            Entity asteroid = asteroidFactory.createRandomizedAsteroid(getRandomPositionNotNearPlayer(playerPosition, playerSpawnRadius), AsteroidType.randomAsteroidType(), false);
            engine.addEntity(asteroid);
        } else {
            spawnCooldownTimer -= delta;
        }
    }
}

