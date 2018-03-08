package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;
import net.infinitycorp.asteroidsecs.AsteroidTypes;

public class AsteroidTypeComponent implements Component{

    public AsteroidTypes type;

    public AsteroidTypeComponent(AsteroidTypes type){
        this.type = type;
    }
}
