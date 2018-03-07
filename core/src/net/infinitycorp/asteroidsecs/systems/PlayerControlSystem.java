package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.infinitycorp.asteroidsecs.components.PlayerControlComponent;
import net.infinitycorp.asteroidsecs.components.VelocityComponent;

public class PlayerControlSystem extends EntitySystem{
    private ImmutableArray<Entity> entities;

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerControlComponent.class, VelocityComponent.class).get());
    }

    public void update(float delta){
        VelocityComponent velocityComponent;

//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//            movement.rotateSprite(sprite, 1, delta);
//        }
//        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//            movement.rotateSprite(sprite, -1, delta);
//        }
//
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            movement.changeVelocity(sprite, delta);
//        }
    }
}
