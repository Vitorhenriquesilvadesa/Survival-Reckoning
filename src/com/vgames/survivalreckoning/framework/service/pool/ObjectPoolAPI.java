package com.vgames.survivalreckoning.framework.service.pool;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;
import com.vgames.survivalreckoning.framework.entity.Transform;
import com.vgames.survivalreckoning.framework.service.general.ApplicationService;

import java.util.ArrayList;
import java.util.List;

public class ObjectPoolAPI implements ApplicationService {
    private static final List<GameObject> gameObjects = new ArrayList<>();

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public void update() {
        for(GameObject gameObject : gameObjects) {
            gameObject.updateComponents();
        }
    }

    @Override
    public void shutdown() {
        for (GameObject gameObject : gameObjects) {
            gameObject.clearComponents();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Component> GameObject instantiate(Transform parent, Class<?>... components) {
        GameObject gameObject = new GameObject(parent);

        if (components != null) {
            for (Class<?> component : components) {
                gameObject.addComponent((Class<T>)component);
            }
        }

        gameObjects.add(gameObject);
        return gameObject;
    }

    public static GameObject instantiate(Transform parent) {
        GameObject gameObject = new GameObject(parent);
        attachObject(gameObject);
        return gameObject;
    }

    public static GameObject instantiate() {
        GameObject gameObject = new GameObject();
        attachObject(gameObject);
        return gameObject;
    }

    private static void attachObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
