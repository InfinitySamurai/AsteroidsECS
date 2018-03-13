package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;

public class ScoreComponent implements Component{
    public int score;

    public ScoreComponent(int score){
        this.score = score;
    }

    public ScoreComponent(){
        this(0);
    }
}
