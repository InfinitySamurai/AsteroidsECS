package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import net.infinitycorp.asteroidsecs.components.HitCircleComponent;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.VelocityComponent;

public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<HitCircleComponent> hitCircleMapper = ComponentMapper.getFor(HitCircleComponent.class);

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(VelocityComponent.class, PositionComponent.class).get());
    }

    public void update(float delta) {
        PositionComponent position;
        VelocityComponent velocity;
        HitCircleComponent hitCircle;

        for (Entity e : entities) {
            position = this.positionMapper.get(e);
            velocity = velocityMapper.get(e);
            hitCircle = hitCircleMapper.get(e);

            position.x += velocity.x * delta;
            position.y += velocity.y * delta;

            if (hitCircle != null) {
                hitCircle.hitCircle.setPosition(position.x, position.y);
            }
        }
    }
}
