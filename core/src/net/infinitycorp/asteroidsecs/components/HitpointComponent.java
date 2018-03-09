package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class HitpointComponent implements Component{
    public int hitpoints;

    public HitpointComponent(int hitpoints){
        this.hitpoints = hitpoints;
    }
}
