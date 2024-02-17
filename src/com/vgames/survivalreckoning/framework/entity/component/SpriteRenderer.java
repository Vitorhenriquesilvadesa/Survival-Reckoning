package com.vgames.survivalreckoning.framework.entity.component;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Model;


public class SpriteRenderer extends Component {

    private Texture texture;

    public SpriteRenderer(GameObject parent) {
        super(parent);
    }

    @Override
    public void cleanup() {

    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
