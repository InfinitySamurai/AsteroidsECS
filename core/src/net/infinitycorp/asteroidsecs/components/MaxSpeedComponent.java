package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class MaxSpeedComponent implements Component {
    public float maxSpeed;

    public MaxSpeedComponent(float speed){
        this.maxSpeed = speed;
    }
}
