package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import net.infinitycorp.asteroidsecs.components.AsteroidTypeComponent;
import net.infinitycorp.asteroidsecs.components.BulletComponent;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.VisualComponent;

public class BulletCollisionSystem extends EntitySystem {

    private Engine engine;
    private ImmutableArray<Entity> bullets;
    private ImmutableArray<Entity> asteroids;

    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VisualComponent> visualMapper = ComponentMapper.getFor(VisualComponent.class);

    public BulletCollisionSystem(Engine engine) {
        this.engine = engine;
    }

    public void addedToEngine(Engine engine) {
        bullets = engine.getEntitiesFor(Family.all(BulletComponent.class).get());
        asteroids = engine.getEntitiesFor(Family.all(AsteroidTypeComponent.class).get());
    }

    public void update(float delta) {
        PositionComponent bulletPosition;
        VisualComponent bulletVisual;

        for (Entity bullet : bullets) {
            bulletPosition = positionMapper.get(bullet);
            bulletVisual = visualMapper.get(bullet);
            Circle bulletHitCircle = new Circle(bulletPosition.x, bulletPosition.y, bulletVisual.width / 2);

            PositionComponent asteroidPosition;
            VisualComponent asteroidVisual;

            for (Entity asteroid : asteroids) {
                asteroidPosition = positionMapper.get(asteroid);
                asteroidVisual = visualMapper.get(asteroid);
                Circle asteroidHitCircle = new Circle(asteroidPosition.x, asteroidPosition.y, asteroidVisual.width / 2);

                if (bulletHitCircle.overlaps(asteroidHitCircle)) {
                    engine.removeEntity(asteroid);
                    engine.removeEntity(bullet);
                }
            }
        }

    }

}
