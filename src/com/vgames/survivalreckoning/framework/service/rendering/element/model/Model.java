package com.vgames.survivalreckoning.framework.service.rendering.element.model;

import com.vgames.survivalreckoning.framework.service.rendering.element.material.Material;

public class Model {

    private Mesh mesh;
    private Material material;

    public Model(Mesh model, Material material) {
        this.mesh = model;
        this.material = material;
    }

    public Mesh getRawModel() {
        return mesh;
    }

    public Material getMaterial() {
        return material;
    }

    public void setRawModel(Mesh mesh) {
        this.mesh = mesh;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
