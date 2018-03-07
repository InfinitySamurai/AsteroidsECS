package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class ShootingComponent implements Component {
    public boolean canShoot;
    public float cooldown;
    public float defaultCooldown;

    public ShootingComponent(boolean canShoot, float defaultCooldown) {
        this.canShoot = canShoot;
        this.cooldown = 0;
        this.defaultCooldown = defaultCooldown;
    }

    public ShootingComponent(boolean canShoot, float cooldown, float defaultCooldown) {
        this.canShoot = canShoot;
        this.cooldown = cooldown;
        this.defaultCooldown = defaultCooldown;
    }
}
