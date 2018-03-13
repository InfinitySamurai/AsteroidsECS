package net.infinitycorp.asteroidsecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import net.infinitycorp.asteroidsecs.components.HitpointComponent;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.ScoreComponent;
import net.infinitycorp.asteroidsecs.components.TextComponent;

public class Ui {

    public static Engine engine;
    public static Entity scoreUiElement;
    public static Entity hitpointsUiElement;
    public static HitpointComponent hitpoints;

    private Ui(){
        scoreUiElement = new Entity();
        scoreUiElement.add(new ScoreComponent());
        scoreUiElement.add(new TextComponent("Score: %d"));
        scoreUiElement.add(new PositionComponent(10, Gdx.graphics.getHeight() - 10));
        engine.addEntity(scoreUiElement);

        hitpointsUiElement = new Entity();
        hitpointsUiElement.add(new TextComponent("Lives: %d"));
        hitpointsUiElement.add(new PositionComponent(Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 10));
        engine.addEntity(hitpointsUiElement);
    }

    private static Ui ui;

    public static Ui getInstance(){
        if(ui == null){
            ui = new Ui();
        }
        return ui;
    }
}
