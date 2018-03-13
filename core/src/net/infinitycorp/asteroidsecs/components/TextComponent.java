package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class TextComponent implements Component {
    public String text;

    public TextComponent(String text){
        this.text = text;
    }
}
