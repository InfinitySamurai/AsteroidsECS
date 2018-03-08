package net.infinitycorp.asteroidsecs.events;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import net.infinitycorp.asteroidsecs.components.AsteroidTypeComponent;

public class AsteroidDestructionListener implements EntityListener {
    private boolean handled;
    private Entity asteroidDestroyed;

    public Entity getDestroyedAsteroid(){
        if(handled){
            return null;
        }
        handled = true;
        return asteroidDestroyed;
    }

    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {
        if(entity.getComponent(AsteroidTypeComponent.class) != null){
            this.handled = false;
            this.asteroidDestroyed = entity;
        }

    }
}
