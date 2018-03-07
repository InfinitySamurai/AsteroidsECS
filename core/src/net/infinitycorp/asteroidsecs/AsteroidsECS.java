package net.infinitycorp.asteroidsecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.ScreenWrapComponent;
import net.infinitycorp.asteroidsecs.components.VelocityComponent;
import net.infinitycorp.asteroidsecs.components.VisualComponent;
import net.infinitycorp.asteroidsecs.systems.MovementSystem;
import net.infinitycorp.asteroidsecs.systems.RenderSystem;
import net.infinitycorp.asteroidsecs.systems.ScreenWrapSystem;

public class AsteroidsECS extends ApplicationAdapter {
    Engine engine;

    @Override
    public void create() {

        engine = new Engine();

        RenderSystem renderSystem = new RenderSystem();
        MovementSystem movementSystem = new MovementSystem();
        ScreenWrapSystem screenWrapSystem = new ScreenWrapSystem();

        engine.addSystem(renderSystem);
        engine.addSystem(movementSystem);
        engine.addSystem(screenWrapSystem);

        Texture shipTexture = new Texture("ship.png");
        Texture bulletTexture = new Texture("bullet.png");

        Entity ship = new Entity();
        ship.add(new VisualComponent(new TextureRegion(shipTexture)));
        ship.add(new PositionComponent(250, 250));
        ship.add(new VelocityComponent(-150,-150));
        ship.add(new ScreenWrapComponent());
        engine.addEntity(ship);

        Entity bullet = new Entity();
        bullet.add(new VisualComponent(new TextureRegion(bulletTexture)));
        bullet.add(new PositionComponent(250,250));
        engine.addEntity(bullet);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(delta);
    }

    @Override
    public void dispose() {
    }
}
