package com.vgames.survivalreckoning.framework.service.rendering.element.model;

import com.vgames.survivalreckoning.framework.service.rendering.RenderingElement;

public class TexturedModel {

    private RawModel rawModel;
    private Material material;

    public TexturedModel(RawModel model, Material material) {
        this.rawModel = model;
        this.material = material;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public Material getMaterial() {
        return material;
    }

    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
