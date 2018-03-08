package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class AsteroidValueComponent implements Component{

    public int value;

    public AsteroidValueComponent(int value){
        this.value = value;
    }
}
