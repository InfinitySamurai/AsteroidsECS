package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.AsteroidType;
import net.infinitycorp.asteroidsecs.components.AsteroidTypeComponent;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.events.AsteroidDestructionListener;
import net.infinitycorp.asteroidsecs.factories.AsteroidFactory;

import java.util.Random;

public class AsteroidSpawningSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<AsteroidTypeComponent> asteroidValueMapper = ComponentMapper.getFor(AsteroidTypeComponent.class);
    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);

    private Engine engine;
    private Random random;
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
        AsteroidTypeComponent asteroidTypeComponent;

        int totalAsteroidValue = 0;

        for (Entity e : entities){
            asteroidTypeComponent = asteroidValueMapper.get(e);
            totalAsteroidValue += asteroidTypeComponent.type.asteroidValue;
        }

        regularAsteroidSpawner(totalAsteroidValue, delta);
        spawnBrokenAsteroids();
    }

    private void spawnBrokenAsteroids(){
        Entity destroyedAsteroid = listener.getDestroyedAsteroid();
        if(destroyedAsteroid != null){
            AsteroidType newAsteroidType = asteroidValueMapper.get(destroyedAsteroid).type.nextSmallerAsteroid;
            PositionComponent position = positionMapper.get(destroyedAsteroid);
            if(newAsteroidType != null){
                engine.addEntity(asteroidFactory.createAsteroid(new Vector2(position.x, position.y), randomizeVelocity(), newAsteroidType));
                engine.addEntity(asteroidFactory.createAsteroid(new Vector2(position.x, position.y), randomizeVelocity(), newAsteroidType));
            }
        }
    }

    private void regularAsteroidSpawner(int totalAsteroidValue, float delta){
        if (spawnCooldownTimer < 0) {
            spawnCooldownTimer = defaultCooldownTime;

            if(totalAsteroidValue >= MAXASTEROIDVALUE){
                return;
            }

            Entity asteroid = asteroidFactory.createAsteroid(randomizeSpawnLocation(), randomizeVelocity(), AsteroidType.randomAsteroidType());;
            engine.addEntity(asteroid);
        }
        else{
            spawnCooldownTimer -= delta;
        }
    }
}

