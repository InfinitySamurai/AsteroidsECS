package net.infinitycorp.asteroidsecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.AsteroidType;
import net.infinitycorp.asteroidsecs.components.*;

public class AsteroidFactory {

    public AsteroidFactory() {
    }

    public Entity createAsteroid(Vector2 position, Vector2 velocity, AsteroidType type) {
        Entity asteroid = new Entity();
        asteroid.add(type.visual);
        asteroid.add(new PositionComponent(position.x, position.y));
        asteroid.add(new VelocityComponent(velocity.x, velocity.y));
        asteroid.add(new ScreenWrapComponent());
        asteroid.add(new AsteroidTypeComponent(type));
        asteroid.add(new HitCircleComponent(position,  type.visual.width / 2));

        return asteroid;
    }
}
