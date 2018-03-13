package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class HitpointComponent implements Component{
    public int hitpoints;
    public int maxHitpoints;

    public HitpointComponent(int hitpoints, int maxHitpoints){
        this.hitpoints = hitpoints;
        this.maxHitpoints = maxHitpoints;
    }

    public HitpointComponent(int hitpoints){
        this(hitpoints, hitpoints);
    }
}
