package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.infinitycorp.asteroidsecs.components.MaxSpeedComponent;
import net.infinitycorp.asteroidsecs.components.PlayerControlComponent;
import net.infinitycorp.asteroidsecs.components.RotationComponent;
import net.infinitycorp.asteroidsecs.components.VelocityComponent;

public class PlayerControlSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<RotationComponent> rotationMapper = ComponentMapper.getFor(RotationComponent.class);
    private ComponentMapper<MaxSpeedComponent> maxSpeedMapper = ComponentMapper.getFor(MaxSpeedComponent.class);

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerControlComponent.class).get());
    }

    public void update(float delta) {
        VelocityComponent velocity;
        RotationComponent rotation;
        MaxSpeedComponent maxSpeed;

        for (Entity e : entities) {
            velocity = velocityMapper.get(e);
            rotation = rotationMapper.get(e);
            maxSpeed = maxSpeedMapper.get(e);

            if(rotation != null){
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    rotation.rotation += rotation.rotationSpeed * delta;
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    rotation.rotation -= rotation.rotationSpeed * delta;
                }
            }
            if(velocity != null && rotation != null && maxSpeed != null){
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    velocity.x += Math.cos(Math.toRadians(rotation.rotation)) * 100 * delta;
                    velocity.y += Math.sin(Math.toRadians(rotation.rotation)) * 100 * delta;

                    if (Math.abs(velocity.x) > maxSpeed.maxSpeed) {
                        velocity.x = Math.signum(velocity.x) * maxSpeed.maxSpeed;
                    }
                    if (Math.abs(velocity.y) > maxSpeed.maxSpeed) {
                        velocity.y = Math.signum(velocity.y) * maxSpeed.maxSpeed;
                    }
                }
            }

        }
    }
}
