package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import net.infinitycorp.asteroidsecs.Ui;
import net.infinitycorp.asteroidsecs.components.*;

public class PlayerCollisionSystem extends EntitySystem {

    private ImmutableArray<Entity> asteroids;
    private ImmutableArray<Entity> ships;
    private Engine engine;

    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<HitCircleComponent> hitCircleMapper = ComponentMapper.getFor(HitCircleComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<HitpointComponent> hitpointsMapper = ComponentMapper.getFor(HitpointComponent.class);
    private ComponentMapper<RotationComponent> roationMapper = ComponentMapper.getFor(RotationComponent.class);

    public PlayerCollisionSystem(Engine engine){
        this.engine = engine;
    }

    public void addedToEngine(Engine engine) {
        asteroids = engine.getEntitiesFor(Family.all(AsteroidTypeComponent.class, PositionComponent.class, VisualComponent.class, HitCircleComponent.class).get());
        ships = engine.getEntitiesFor(Family.all(ShipComponent.class, PositionComponent.class, HitCircleComponent.class, VisualComponent.class).get());
    }

    public void update(float delta) {
        Circle shipHitCircle;
        Circle asteroidHitCircle;

        for(Entity ship : ships){
            shipHitCircle = hitCircleMapper.get(ship).hitCircle;
            for(Entity asteroid : asteroids){
                asteroidHitCircle = hitCircleMapper.get(asteroid).hitCircle;

                if(shipHitCircle.overlaps(asteroidHitCircle)){
                    PositionComponent shipPosition = positionMapper.get(ship);
                    shipPosition.x = Gdx.graphics.getWidth() / 2;
                    shipPosition.y = Gdx.graphics.getHeight() / 2;

                    RotationComponent shipRotation = roationMapper.get(ship);
                    if(shipRotation != null){
                        shipRotation.rotation = 90;
                    }

                    VelocityComponent shipVelocity = velocityMapper.get(ship);
                    if(shipVelocity != null){
                        shipVelocity.x = 0;
                        shipVelocity.y = 0;
                    }

                    HitpointComponent shipHitpoints = hitpointsMapper.get(ship);
                    if(shipHitpoints != null) {
                        shipHitpoints.hitpoints -= 1;
                        if(shipHitpoints.hitpoints <= 0){
                            Ui.scoreUiElement.getComponent(ScoreComponent.class).score = 0;
                            shipHitpoints.hitpoints = shipHitpoints.maxHitpoints;
                        }
                    }

                    for (Entity a : asteroids){
                        engine.removeEntity(a);
                    }

                    return;
                }
            }
        }


    }
}
