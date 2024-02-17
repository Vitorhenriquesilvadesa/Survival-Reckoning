package com.vgames.survivalreckoning.framework.entity.component.spriterenderer;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;


public class SpriteRenderer extends Component {

    private Texture texture;

    public SpriteRenderer(GameObject parent) {
        super(parent);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
