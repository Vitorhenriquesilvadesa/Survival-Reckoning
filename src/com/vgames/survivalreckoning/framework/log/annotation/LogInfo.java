package com.vgames.survivalreckoning.framework.log.annotation;


import com.vgames.survivalreckoning.framework.log.LogLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInfo {
    LogLevel level() default LogLevel.INFO;
    boolean verbose() default true;
}
