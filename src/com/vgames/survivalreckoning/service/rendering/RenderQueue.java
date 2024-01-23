package com.vgames.survivalreckoning.service.rendering;

import com.vgames.survivalreckoning.service.rendering.annotation.RenderingPriority;

import java.util.ArrayList;
import java.util.List;

public class RenderQueue {

    private final List<RenderingElement> highPriorityRenderingQueue;
    private final List<RenderingElement> mediumPriorityRenderingQueue;
    private final List<RenderingElement> lowPriorityRenderingQueue;

    public RenderQueue() {
        this.highPriorityRenderingQueue = new ArrayList<>();
        this.mediumPriorityRenderingQueue = new ArrayList<>();
        this.lowPriorityRenderingQueue = new ArrayList<>();
    }

    public void attachObject(RenderingElement object) {
        switch (getObjectPriority(object)) {
            case HIGH: {
                this.highPriorityRenderingQueue.add(object);
                break;
            }
            case MEDIUM: {
                this.mediumPriorityRenderingQueue.add(object);
                break;
            }
            case LOW: {
                this.lowPriorityRenderingQueue.add(object);
                break;
            }
        }
    }

    private Priority getObjectPriority(RenderingElement object) {
        Class<?> klass = object.getClass();
        Class<?> superKlass = klass.getSuperclass();
        boolean isAnnotated = klass.isAnnotationPresent(RenderingPriority.class);
        boolean isSuperAnnotated = superKlass.isAnnotationPresent(RenderingPriority.class);

        if (isAnnotated) {
            return klass.getDeclaredAnnotation(RenderingPriority.class).priority();
        } else if (isSuperAnnotated) {
            return superKlass.getDeclaredAnnotation(RenderingPriority.class).priority();
        } else {
            return Priority.MEDIUM;
        }
    }

    public void render(Renderer renderer) {
        for (RenderingElement object : this.highPriorityRenderingQueue) {
            renderer.render(object);
        }

        for (RenderingElement object : this.mediumPriorityRenderingQueue) {
            renderer.render(object);
        }

        for (RenderingElement object : this.lowPriorityRenderingQueue) {
            renderer.render(object);
        }
    }
}
