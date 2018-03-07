package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.ScreenWrapComponent;

public class ScreenWrapSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

    private float windowWidth;
    private float windowHeight;

    public ScreenWrapSystem(){
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, ScreenWrapComponent.class).get());
    }

    public void update(float delta) {
        PositionComponent position;

        for (Entity e : entities) {
            position = pm.get(e);

            if (position.x > windowWidth){
                position.x = 0;
            }
            else if (position.x < 0){
                position.x = windowWidth;
            }

            if (position.y > windowHeight){
                position.y = 0;
            }
            else if(position.y < 0){
                position.y = windowHeight;
            }
        }
    }

}
