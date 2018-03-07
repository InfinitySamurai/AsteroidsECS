package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.RotationComponent;
import net.infinitycorp.asteroidsecs.components.VisualComponent;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch sb;

    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VisualComponent> velocityMapper = ComponentMapper.getFor(VisualComponent.class);
    private ComponentMapper<RotationComponent> rotationMapper = ComponentMapper.getFor(RotationComponent.class);

    public RenderSystem() {
        this.sb = new SpriteBatch();
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(VisualComponent.class, PositionComponent.class).get());
    }

    public void update(float delta) {
        PositionComponent position;
        VisualComponent visual;
        RotationComponent rotation;

        sb.begin();
        for (Entity e : entities) {
            position = positionMapper.get(e);
            visual = velocityMapper.get(e);
            rotation = rotationMapper.get(e);

            float xPositionOfEntity = position.x - visual.region.getRegionWidth() / 2;
            float yPositionOfEntity = position.y - visual.region.getRegionHeight() / 2;

            Affine2 transform = new Affine2();

            if(rotation != null){
                transform.preRotate(rotation.rotation);
            }

            transform.preTranslate(new Vector2(xPositionOfEntity, yPositionOfEntity));

            sb.draw(visual.region, visual.region.getRegionWidth(), visual.region.getRegionHeight(), transform);
        }
        sb.end();
    }
}
