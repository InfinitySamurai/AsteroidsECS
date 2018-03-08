package net.infinitycorp.asteroidsecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.components.*;

public class BulletFactory {
    Texture bulletTexture = new Texture("bullet.png");
    private final float DEFAULTLIFETIME = 2;

    public BulletFactory(){

    }

    public Entity createBullet(float x, float y, float rotation, float speed, float offsetDistance){
        Entity bullet = new Entity();
        bullet.add(new VisualComponent(new TextureRegion(bulletTexture)));

        Vector2 positionOffset = getVectorFromRotationAndMagnitude(rotation, offsetDistance);
        bullet.add(new PositionComponent(x + positionOffset.x, y + positionOffset.y));
        bullet.add(new RotationComponent(rotation, 0));
        Vector2 velocity = getVectorFromRotationAndMagnitude(rotation, speed);
        bullet.add(new VelocityComponent(velocity.x, velocity.y));
        bullet.add(new ScreenWrapComponent());
        bullet.add(new LifetimeComponent(DEFAULTLIFETIME));
        bullet.add(new BulletComponent());

        return bullet;
    }

    private Vector2 getVectorFromRotationAndMagnitude(float rotation, float magnitude){
        float x = (float)Math.cos(Math.toRadians(rotation)) * magnitude;
        float y = (float)Math.sin(Math.toRadians(rotation))* magnitude;

        return new Vector2(x, y);
    }
}
