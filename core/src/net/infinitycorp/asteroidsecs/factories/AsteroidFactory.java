package net.infinitycorp.asteroidsecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.AsteroidType;
import net.infinitycorp.asteroidsecs.components.*;

import java.util.Random;

public class AsteroidFactory {

    Random random;

    public AsteroidFactory() {
        random = new Random();
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

    public Entity createRandomizedAsteroid(Vector2 position, AsteroidType type, boolean offCentre){
        Vector2 newPosition = position;
        Vector2 velocity = new Vector2().setToRandomDirection();
        float speed = 50 + random.nextFloat() * 100;

        if(offCentre){
            newPosition = position.add(velocity.cpy().scl(10));
        }
        return createAsteroid(newPosition, velocity.scl(speed), type);
    }
}
