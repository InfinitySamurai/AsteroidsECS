package net.infinitycorp.asteroidsecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.infinitycorp.asteroidsecs.Ui;
import net.infinitycorp.asteroidsecs.components.PositionComponent;
import net.infinitycorp.asteroidsecs.components.ScoreComponent;
import net.infinitycorp.asteroidsecs.components.TextComponent;

public class ScoreUiUpdateSystem extends EntitySystem {
    private SpriteBatch sb;
    private BitmapFont font;


    public ScoreUiUpdateSystem(SpriteBatch sb, BitmapFont font){
        this.sb = sb;
        this.font = font;
    }

    public void update(float delta){
        String unformattedScoreText = Ui.scoreUiElement.getComponent(TextComponent.class).text;
        int scoreAmount = Ui.scoreUiElement.getComponent(ScoreComponent.class).score;
        PositionComponent position = Ui.scoreUiElement.getComponent(PositionComponent.class);

        String scoreText = String.format(unformattedScoreText, scoreAmount);

        font.draw(sb, scoreText, position.x, position.y);
    }
}
