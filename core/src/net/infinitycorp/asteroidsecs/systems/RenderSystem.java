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
        float rotate;

        sb.begin();
        for (Entity e : entities) {
            position = positionMapper.get(e);
            visual = velocityMapper.get(e);
            rotation = rotationMapper.get(e);

            if(rotation == null){
                rotate = 0;
            }
            else{
                rotate = rotation.rotation;
            }

            float textureWidth = visual.region.getRegionWidth();
            float textureHeight = visual.region.getRegionHeight();

            float xPositionOfEntity = position.x - textureWidth / 2;
            float yPositionOfEntity = position.y - textureHeight / 2;

//            Affine2 transform = new Affine2();
//
//            if(rotation != null){
//                transform.preRotate(rotation.rotation);
//            }
//
//            transform.preTranslate(new Vector2(xPositionOfEntity, yPositionOfEntity));

            sb.draw(visual.region,
                    xPositionOfEntity,
                    yPositionOfEntity,
                    textureWidth / 2,
                    textureHeight / 2,
                    textureWidth, textureHeight,
                    1,
                    1,
                    rotate);
        }
        sb.end();
    }
}
