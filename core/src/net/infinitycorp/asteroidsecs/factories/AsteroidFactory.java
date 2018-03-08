package net.infinitycorp.asteroidsecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.AsteroidTypes;
import net.infinitycorp.asteroidsecs.components.*;

public class AsteroidFactory {

    private final float ASTEROIDSCALE = 0.5f;

    public AsteroidFactory() {
    }

    public Entity createAsteroid(Vector2 position, Vector2 velocity, AsteroidTypes type) {
        Entity asteroid = new Entity();
        asteroid.add(new VisualComponent(type.textureRegion, ASTEROIDSCALE));
        asteroid.add(new PositionComponent(position.x, position.y));
        asteroid.add(new VelocityComponent(velocity.x, velocity.y));
        asteroid.add(new ScreenWrapComponent());
        asteroid.add(new AsteroidTypeComponent(type));
        asteroid.add(new HitCircleComponent(position,  type.textureRegion.getRegionWidth() / 2 * ASTEROIDSCALE));

        return asteroid;
    }
}
