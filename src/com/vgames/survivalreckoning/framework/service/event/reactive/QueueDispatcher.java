package com.vgames.survivalreckoning.framework.service.event.reactive;

import com.vgames.survivalreckoning.framework.log.Logger;
import com.vgames.survivalreckoning.framework.log.annotation.GenerateCriticalFile;
import com.vgames.survivalreckoning.framework.log.annotation.LogAlias;
import com.vgames.survivalreckoning.framework.service.event.actions.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LogAlias("EventAPI")
@GenerateCriticalFile
public class QueueDispatcher extends Logger {

    private final Map<Class<? extends Event>, Map<Method, List<Object>>> classMapMap;
    private final Map<Class<? extends Event>, EventQueue> eventQueueMap;

    public QueueDispatcher() {
        this.classMapMap = new HashMap<>();
        this.eventQueueMap = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void subscribe(Object listener) {

        Class<?> klass = listener.getClass();
        Method[] klassMethods = klass.getMethods();

        for (Method method : klassMethods) {

            if (method.isAnnotationPresent(Reactive.class)) {

                Class<?>[] parameters = method.getParameterTypes();

                if (parameters.length != 1) {
                    critical("Reactive methods must have one parameter.", new RuntimeException("Invalid method declaration."));
                }

                if (Event.class.isAssignableFrom(parameters[0])) {

                    Map<Method, List<Object>> batch = classMapMap.get(parameters[0]);

                    if (batch == null) {

                        batch = new HashMap<>();
                        classMapMap.put((Class<? extends Event>) parameters[0], batch);
                    }

                    if (!batch.containsKey(method)) {

                        batch.put(method, new ArrayList<>());
                        batch.get(method).add(listener);
                    }
                }
            }
        }
    }

    public void unsubscribe(Object listener) {

        Class<?> klass = listener.getClass();
        Method[] klassMethods = klass.getMethods();

        for (Method method : klassMethods) {

            if (method.isAnnotationPresent(Reactive.class)) {

                Class<?>[] parameters = method.getParameterTypes();

                if (parameters.length != 1) {
                    critical("Reactive methods must have one parameter.", new RuntimeException("Invalid method declaration."));
                }

                if (Event.class.isAssignableFrom(parameters[0])) {

                    Map<Method, List<Object>> batch = classMapMap.get(parameters[0]);

                    if (batch != null) {
                        batch.remove(method);
                    }
                }
            }
        }
    }

    public void dispatchEvent(Event event) {
        Class<? extends Event> eventClass = event.getClass();

        if (!eventQueueMap.containsKey(event.getClass())) {
            eventQueueMap.put(eventClass, new EventQueue());
        }

        eventQueueMap.get(eventClass).pushEvent(event);
    }

    public void dispatchQueues() {

        for (Class<? extends Event> klass : classMapMap.keySet()) {

            EventQueue eventQueue = eventQueueMap.get(klass);

            if (eventQueue.isEmpty()) {
                continue;
            }

            Event event = eventQueue.popEvent();

            for (Method method : classMapMap.get(klass).keySet()) {

                Map<Method, List<Object>> batch = classMapMap.get(klass);
                List<Object> instances = batch.get(method);

                if (instances.isEmpty()) {
                    eventQueue.clearEvents();
                    continue;
                }

                for (Object instance : instances) {
                    try {
                        if(Modifier.isPrivate(method.getModifiers())) {
                            critical("The method '" + method.getName() + "' in class '" + instance.getClass().getSimpleName() + "' must be PUBLIC.");
                        }
                        method.invoke(instance, event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        critical("Cannot invoke method '" + method.getName() + "'", new RuntimeException(e));
                    }
                }
            }
        }
    }
}