package com.vgames.survivalreckoning.entity.component;

import com.vgames.survivalreckoning.entity.Entity;
import com.vgames.survivalreckoning.log.annotation.LogAlias;
import com.vgames.survivalreckoning.service.rendering.RenderingElement;
import com.vgames.survivalreckoning.service.rendering.annotation.ShaderName;
import com.vgames.survivalreckoning.service.rendering.element.Sprite;

@LogAlias(alias = "SpriteRenderer")
@ShaderName(name = "texture")
public class SpriteRendererComponent extends Component implements RenderingElement {

    private Sprite sprite;

    public SpriteRendererComponent(Sprite sprite, Entity parent) {
        super(parent);
        this.sprite = sprite;
        if(parent.getComponent(TransformComponent.class) == null) {
            error("Missing in parent entity: TransformComponent. -> Parent: " + parent.getClass().getSimpleName());
        } else {
            sprite.setParent(parent.getComponent(TransformComponent.class));
        }

        instantiateRenderingObject(this);
    }

    public void setSprite(Sprite newSprite) {
        this.sprite = newSprite;
    }

    @Override
    public void render() {

    }

    @Override
    public void update() {

    }
}
