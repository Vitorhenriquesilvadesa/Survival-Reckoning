package com.vgames.survivalreckoning.framework.entity.component.camera;

import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.Transform;
import com.vgames.survivalreckoning.framework.entity.component.Component;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.Camera;

public class CameraComponent extends Component {

    private final Camera camera;
    public CameraComponent(GameObject parent) {
        super(parent);
        camera = new Camera();
    }

    public void start() {
        camera.transform = new Transform(parent.transform);
        Vector3 position = parent.transform.getPosition();
        camera.transform.setPosition(new Vector3(position.x, position.y, position.z - 1));
    }



    public Camera getCamera() {
        return camera;
    }

    public void setToActive() {
        Engine.fromService(GraphicsAPI.class).setActiveCamera(camera);
    }
}
