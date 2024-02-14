package com.vgames.survivalreckoning.framework.service.rendering.element.material;

public class Material {

    private Texture texture;
    public float smoothness;
    public float reflectivity;
    public boolean isDoubleSided;

    public Material(Texture texture, float smoothness, float reflectivity, boolean isDoubleSided) {
        this.texture = texture;
        this.smoothness = smoothness;
        this.reflectivity = reflectivity;
        this.isDoubleSided = isDoubleSided;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
