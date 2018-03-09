package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.infinitycorp.asteroidsecs.components.*;
import net.infinitycorp.asteroidsecs.factories.BulletFactory;

public class ShootingSystem extends EntitySystem{
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<RotationComponent> rotationMapper = ComponentMapper.getFor(RotationComponent.class);
    private ComponentMapper<ShootingComponent> shootingMapper = ComponentMapper.getFor(ShootingComponent.class);
    private ComponentMapper<VisualComponent> visualMapper = ComponentMapper.getFor(VisualComponent.class);


    private BulletFactory bulletFactory = new BulletFactory();

    private Engine engine;

    public ShootingSystem(Engine engine){
        this.engine = engine;
    }


    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(ShootingComponent.class, PositionComponent.class, RotationComponent.class).get());
    }

    public void update(float delta){
        ShootingComponent shoot;
        PositionComponent position;
        RotationComponent rotation;
        VisualComponent visual;

        for (Entity e : entities){
            shoot = shootingMapper.get(e);
            position = positionMapper.get(e);
            rotation = rotationMapper.get(e);
            visual = visualMapper.get(e);

            if(shoot.canShoot){
                if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                    float offset = 0;
                    if(visual != null){
                        offset = visual.region.getRegionHeight() / 2;
                    }
                    engine.addEntity(bulletFactory.createBullet(position.x, position.y, rotation.rotation, 300, offset));
                    shoot.canShoot = false;
                    shoot.cooldown = shoot.defaultCooldown;
                }
            }
            else {
                shoot.cooldown -= delta;
                if(shoot.cooldown < 0){
                    shoot.cooldown = 0;
                    shoot.canShoot = true;
                }
            }
        }
    }
}
