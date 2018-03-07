package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import net.infinitycorp.asteroidsecs.components.MaxSpeedComponent;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.VelocityComponent;

public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> poistionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(VelocityComponent.class, PositionComponent.class).get());
    }

    public void update(float delta) {
        PositionComponent position;
        VelocityComponent velocity;

        for (Entity e : entities) {
            position = poistionMapper.get(e);
            velocity = velocityMapper.get(e);

            position.x += velocity.x * delta;
            position.y += velocity.y * delta;
        }
    }
}
