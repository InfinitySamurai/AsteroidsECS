package net.infinitycorp.asteroidsecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.components.*;

public class BulletFactory {
    Texture bulletTexture = new Texture("bullet.png");

    public BulletFactory(){

    }

    public Entity createBullet(float x, float y, float rotation, float speed){
        Entity bullet = new Entity();
        bullet.add(new VisualComponent(new TextureRegion(bulletTexture)));

        bullet.add(new PositionComponent(x, y));
        bullet.add(new RotationComponent(rotation, 0));
        Vector2 velocity = getVelocitiesFromRotationAndSpeed(rotation, speed);
        bullet.add(new VelocityComponent(velocity.x, velocity.y));
        bullet.add(new ScreenWrapComponent());

        return bullet;
    }

    private Vector2 getVelocitiesFromRotationAndSpeed(float rotation, float speed){
        float x = (float)Math.cos(Math.toRadians(rotation)) * speed;
        float y = (float)Math.sin(Math.toRadians(rotation))* speed;

        return new Vector2(x, y);
    }
}
