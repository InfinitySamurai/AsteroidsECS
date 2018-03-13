package net.infinitycorp.asteroidsecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import net.infinitycorp.asteroidsecs.components.*;
import net.infinitycorp.asteroidsecs.systems.*;

public class AsteroidsECS extends ApplicationAdapter {
    Engine engine;
    SpriteBatch sb;
    BitmapFont font;
    Ui ui;

    @Override
    public void create() {
        sb = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        engine = new Engine();
        Ui.engine = engine;
        ui = Ui.getInstance();

        engine.addSystem(new RenderSystem(sb));
        engine.addSystem(new ScoreUiUpdateSystem(sb, font));
        engine.addSystem(new HitpointsUiUpdateSystem(sb, font));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new ScreenWrapSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new ShootingSystem(engine));
        engine.addSystem(new LifetimeSystem(engine));
        engine.addSystem(new AsteroidSpawningSystem(engine));
        engine.addSystem(new BulletCollisionSystem(engine));
        engine.addSystem(new PlayerCollisionSystem(engine));

        Texture shipTexture = new Texture("ship.png");
        Vector2 centreOfScreen = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        Entity ship = new Entity();
        ship.add(new VisualComponent(new TextureRegion(shipTexture)));
        ship.add(new PositionComponent(centreOfScreen.x, centreOfScreen.y));
        ship.add(new VelocityComponent(0, 0));
        ship.add(new MaxSpeedComponent(300));
        ship.add(new ScreenWrapComponent());
        ship.add(new RotationComponent(90, 150));
        ship.add(new PlayerControlComponent());
        ship.add(new ShootingComponent(true, 0.1f));
        ship.add(new ShipComponent());
        ship.add(new HitCircleComponent(centreOfScreen, shipTexture.getWidth() / 2));
        HitpointComponent hp = new HitpointComponent(5);
        ship.add(hp);
        Ui.hitpoints = hp;
        engine.addEntity(ship);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        engine.update(delta);
        sb.end();
    }

    @Override
    public void dispose() {
    }
}
