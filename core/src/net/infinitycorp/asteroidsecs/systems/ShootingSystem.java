package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.infinitycorp.asteroidsecs.components.*;
import net.infinitycorp.asteroidsecs.factories.BulletFactory;

public class ShootingSystem extends EntitySystem{
    private ImmutableArray<Entity> entities;

    private ComponentMapper<CanShootComponent> canShootMapper = ComponentMapper.getFor(CanShootComponent.class);
    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<RotationComponent> rotationMapper = ComponentMapper.getFor(RotationComponent.class);
    private ComponentMapper<ShotCooldownComponent> cooldownMapper = ComponentMapper.getFor(ShotCooldownComponent.class);

    private BulletFactory bulletFactory = new BulletFactory();

    private Engine engine;

    public ShootingSystem(Engine engine){
        this.engine = engine;
    }


    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerControlComponent.class, PositionComponent.class, RotationComponent.class).get());
    }

    public void update(float delta){
        CanShootComponent canShoot;
        PositionComponent position;
        RotationComponent rotation;
        ShotCooldownComponent cooldown;

        for (Entity e : entities){
            canShoot = canShootMapper.get(e);
            position = positionMapper.get(e);
            rotation = rotationMapper.get(e);
            cooldown = cooldownMapper.get(e);

            if(canShoot != null){
                if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                    engine.addEntity(bulletFactory.createBullet(position.x, position.y, rotation.rotation, 300));
                    e.remove(canShoot.getClass());
                    e.add(new ShotCooldownComponent(0.5f));
                }
            }
            else if(cooldown != null){
                cooldown.cooldown -= delta;
                if(cooldown.cooldown < 0){
                    e.remove(cooldown.getClass());
                    e.add(new CanShootComponent());
                }
            }
        }
    }
}
