package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import net.infinitycorp.asteroidsecs.components.*;

public class BulletCollisionSystem extends EntitySystem {

    private Engine engine;
    private ImmutableArray<Entity> bullets;
    private ImmutableArray<Entity> asteroids;
    private ImmutableArray<Entity> scores;

    private ComponentMapper<HitCircleComponent> hitCircleMapper = ComponentMapper.getFor(HitCircleComponent.class);
    private ComponentMapper<ScoreComponent> scoreMapper = ComponentMapper.getFor(ScoreComponent.class);

    public BulletCollisionSystem(Engine engine) {
        this.engine = engine;
    }

    public void addedToEngine(Engine engine) {
        bullets = engine.getEntitiesFor(Family.all(BulletComponent.class, HitCircleComponent.class).get());
        asteroids = engine.getEntitiesFor(Family.all(AsteroidTypeComponent.class, HitCircleComponent.class).get());
        scores = engine.getEntitiesFor(Family.all(ScoreComponent.class).get());
    }

    public void update(float delta) {
        Circle bulletHitCircle;
        Circle asteroidHitCircle;

        for (Entity bullet : bullets) {
            bulletHitCircle = hitCircleMapper.get(bullet).hitCircle;
            for (Entity asteroid : asteroids) {
                asteroidHitCircle = hitCircleMapper.get(asteroid).hitCircle;
                if (bulletHitCircle.overlaps(asteroidHitCircle)) {
                    engine.removeEntity(asteroid);
                    engine.removeEntity(bullet);
                    for(Entity score : scores){
                        scoreMapper.get(score).score += 1;
                    }
                }
            }
        }

    }

}
