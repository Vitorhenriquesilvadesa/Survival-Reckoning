package com.vgames.survivalreckoning.framework.Sprite;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Model;

import javax.swing.text.html.parser.Entity;


public class SpriteRenderer extends Component {
    private Model model;

    public SpriteRenderer(GameObject parent) {
        super(parent);
    }

    @Override
    public void cleanup() {

    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
