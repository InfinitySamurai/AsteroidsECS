package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.ScreenWrapComponent;
import net.infinitycorp.asteroidsecs.components.VisualComponent;

public class ScreenWrapSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VisualComponent> visualMapper = ComponentMapper.getFor(VisualComponent.class);

    private float windowWidth;
    private float windowHeight;

    public ScreenWrapSystem() {
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, ScreenWrapComponent.class).get());
    }

    public void update(float delta) {
        PositionComponent position;
        VisualComponent visual;

        for (Entity e : entities) {
            position = positionMapper.get(e);
            visual = visualMapper.get(e);

            float xoffset = 0;
            float yoffset = 0;

            if (visual != null) {
                xoffset = visual.width / 2;
                yoffset = visual.height / 2;
            }

            if (position.x > windowWidth + xoffset) {
                position.x = 0 - xoffset;
            } else if (position.x < 0 - xoffset) {
                position.x = windowWidth + xoffset;
            }

            if (position.y > windowHeight + yoffset) {
                position.y = 0 -yoffset;
            } else if (position.y < 0 - yoffset) {
                position.y = windowHeight + yoffset;
            }
        }
    }

}
