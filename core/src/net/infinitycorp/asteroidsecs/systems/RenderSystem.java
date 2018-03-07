package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.VisualComponent;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch sb;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VisualComponent> vm = ComponentMapper.getFor(VisualComponent.class);

    public RenderSystem() {
        this.sb = new SpriteBatch();
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(VisualComponent.class, PositionComponent.class).get());
    }

    public void update(float delta) {
        PositionComponent position;
        VisualComponent visual;
        sb.begin();
        for (Entity e : entities) {
            position = pm.get(e);
            visual = vm.get(e);

            float halfTextureWidth = visual.region.getRegionWidth() / 2;
            float halfTextureHeight = visual.region.getRegionHeight() / 2;

            sb.draw(visual.region, position.x - halfTextureWidth, position.y - halfTextureHeight);

        }
        sb.end();
    }
}
