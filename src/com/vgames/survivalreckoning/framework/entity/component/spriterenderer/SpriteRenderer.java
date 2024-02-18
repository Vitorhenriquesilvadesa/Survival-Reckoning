package com.vgames.survivalreckoning.framework.entity.component.spriterenderer;

import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DMesh;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DSize;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.event.reactive.Reactive;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;


public class SpriteRenderer extends Component {

    private Texture texture;
    private boolean meshSizeDefined = false;

    public SpriteRenderer(GameObject parent) {
        super(parent);
        Engine.fromService(GraphicsAPI.class).pushEntityInRenderingPool(parent);
    }

    public void update() {
        if(!meshSizeDefined) {
            if(texture != null) {
                getComponent(Box2DMesh.class).setProps(new Box2DSize(texture.getWidth(), texture.getHeight()));
                meshSizeDefined = true;
            }
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
