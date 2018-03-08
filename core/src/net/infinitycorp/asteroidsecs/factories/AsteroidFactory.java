package net.infinitycorp.asteroidsecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.AsteroidTypes;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.ScreenWrapComponent;
import net.infinitycorp.asteroidsecs.components.VelocityComponent;
import net.infinitycorp.asteroidsecs.components.VisualComponent;

public class AsteroidFactory {

    public AsteroidFactory() {
    }

    public Entity createAsteroid(Vector2 position, Vector2 velocity, AsteroidTypes type) {
        Entity asteroid = new Entity();
        asteroid.add(new VisualComponent(type.textureRegion));
        asteroid.add(new PositionComponent(position.x, position.y));
        asteroid.add(new VelocityComponent(velocity.x, velocity.y));
        asteroid.add(new ScreenWrapComponent());

        return asteroid;
    }
}
