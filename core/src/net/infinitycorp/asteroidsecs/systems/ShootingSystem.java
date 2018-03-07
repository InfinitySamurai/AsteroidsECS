package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.infinitycorp.asteroidsecs.components.*;

public class ShootingSystem extends EntitySystem{
    private ImmutableArray<Entity> entities;

    private ComponentMapper<CanShootComponent> canShootMapper = ComponentMapper.getFor(CanShootComponent.class);
    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<RotationComponent> rotationMapper = ComponentMapper.getFor(RotationComponent.class);

    private Engine engine;

    Texture bulletTexture = new Texture("bullet.png");


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
                    Entity newBullet = new Entity();
                    float speed = 100;
                    newBullet.add(new VisualComponent(new TextureRegion(bulletTexture)));

                    newBullet.add(new PositionComponent(position.x, position.y));
                    newBullet.add(new RotationComponent(rotation.rotation, 0));
                    newBullet.add(new VelocityComponent((float)Math.cos(Math.toRadians(rotation.rotation)) * speed,(float)Math.sin(Math.toRadians(rotation.rotation))* speed));
                    newBullet.add(new ScreenWrapComponent());

                    engine.addEntity(newBullet);
                }
            }
        }
    }
}
