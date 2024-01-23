package com.vgames.survivalreckoning.service.rendering.annotation;


import com.vgames.survivalreckoning.service.rendering.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RenderingPriority {
    Priority priority() default Priority.MEDIUM;
}
