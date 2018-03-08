package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;
import net.infinitycorp.asteroidsecs.AsteroidType;

public class AsteroidTypeComponent implements Component{

    public AsteroidType type;

    public AsteroidTypeComponent(AsteroidType type){
        this.type = type;
    }
}
