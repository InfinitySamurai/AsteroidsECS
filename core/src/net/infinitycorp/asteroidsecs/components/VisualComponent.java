package net.infinitycorp.asteroidsecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class VisualComponent implements Component {
    public TextureRegion region;
    public float scale;
    public float width;
    public float height;

    public VisualComponent (TextureRegion region){
        this(region, 1);
    }

    public VisualComponent(TextureRegion region, float scale){
        this.region = region;
        this.scale = scale;
        this.width = region.getRegionWidth() * scale;
        this.height = region.getRegionHeight() * scale;
    }
}
