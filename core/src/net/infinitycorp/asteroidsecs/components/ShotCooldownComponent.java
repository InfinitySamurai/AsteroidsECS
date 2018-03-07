package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class ShotCooldownComponent implements Component{
    public float cooldown;

    public ShotCooldownComponent(float cooldown) {
        this.cooldown = cooldown;
    }
}
