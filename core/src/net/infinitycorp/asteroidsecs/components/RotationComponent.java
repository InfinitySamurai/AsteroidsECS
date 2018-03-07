package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class RotationComponent implements Component {
    public float rotation;
    public float rotationSpeed;

    public RotationComponent(float rotation, float rotationSpeed){
        this.rotation = rotation;
        this.rotationSpeed = rotationSpeed;
    }
}
