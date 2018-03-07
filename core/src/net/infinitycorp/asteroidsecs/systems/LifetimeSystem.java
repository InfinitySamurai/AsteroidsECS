package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import net.infinitycorp.asteroidsecs.components.LifetimeComponent;

public class LifetimeSystem extends EntitySystem{
    private ImmutableArray<Entity> entities;

    private ComponentMapper<LifetimeComponent> lifetimeMapper = ComponentMapper.getFor(LifetimeComponent.class);
    Engine engine;

    public LifetimeSystem(Engine engine){
        this.engine = engine;
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(LifetimeComponent.class).get());
    }

    public void update(float delta){
        LifetimeComponent lifetime;

        for (Entity e : entities){
            lifetime = lifetimeMapper.get(e);

            lifetime.lifetime -= delta;
             if (lifetime.lifetime < 0){
                engine.removeEntity(e);
             }
        }
    }

}
