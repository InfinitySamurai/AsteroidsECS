package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class HitCircleComponent implements Component {
    public Circle hitCircle;

    public HitCircleComponent(Vector2 position, float radius){
        this.hitCircle = new Circle(position, radius);
    }

    public HitCircleComponent(float x, float y, float radius){
        this.hitCircle = new Circle(x, y, radius);
    }
}
