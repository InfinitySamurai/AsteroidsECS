package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import net.infinitycorp.asteroidsecs.components.AsteroidTypeComponent;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.VisualComponent;

public class PlayerCollisionSystem extends EntitySystem {

    private ImmutableArray<Entity> asteroids;
    private Entity player;

    public void addedToEngine(Engine engine) {
        asteroids = engine.getEntitiesFor(Family.all(AsteroidTypeComponent.class, PositionComponent.class, VisualComponent.class).get());
    }
}
