package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class LifetimeComponent implements Component{
    public float lifetime;

    public LifetimeComponent(float lifetime){
        this.lifetime = lifetime;
    }
}
