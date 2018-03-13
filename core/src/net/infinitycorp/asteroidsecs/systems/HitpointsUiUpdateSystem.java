package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.infinitycorp.asteroidsecs.Ui;
import net.infinitycorp.asteroidsecs.components.*;

public class HitpointsUiUpdateSystem extends EntitySystem{
    private SpriteBatch sb;
    private BitmapFont font;

    public HitpointsUiUpdateSystem(SpriteBatch sb, BitmapFont font){
        this.sb = sb;
        this.font = font;
    }

    public void update(float delta){
        String unformattedHitpointsText = Ui.hitpointsUiElement.getComponent(TextComponent.class).text;
        int hitpoints = Ui.hitpoints.hitpoints;
        PositionComponent position = Ui.hitpointsUiElement.getComponent(PositionComponent.class);

        String hitpointsText = String.format(unformattedHitpointsText, hitpoints);
        font.draw(sb, hitpointsText, position.x, position.y);
    }
}
