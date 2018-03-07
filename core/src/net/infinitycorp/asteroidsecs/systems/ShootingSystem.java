package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.infinitycorp.asteroidsecs.components.*;
import net.infinitycorp.asteroidsecs.factories.BulletFactory;

public class ShootingSystem extends EntitySystem{
    private ImmutableArray<Entity> entities;

    private ComponentMapper<CanShootComponent> canShootMapper = ComponentMapper.getFor(CanShootComponent.class);
    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<RotationComponent> rotationMapper = ComponentMapper.getFor(RotationComponent.class);

    private BulletFactory bulletFactory = new BulletFactory();

    private Engine engine;

    public ShootingSystem(Engine engine){
        this.engine = engine;
    }


    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerControlComponent.class, CanShootComponent.class, PositionComponent.class, RotationComponent.class).get());
    }

    public void update(float delta){
        CanShootComponent canShoot;
        PositionComponent position;
        RotationComponent rotation;

        for (Entity e : entities){
            canShoot = canShootMapper.get(e);
            position = positionMapper.get(e);
            rotation = rotationMapper.get(e);

            if(canShoot != null){
                if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                    engine.addEntity(bulletFactory.createBullet(position.x, position.y, rotation.rotation, 300));
                }
            }
        }
    }
}
