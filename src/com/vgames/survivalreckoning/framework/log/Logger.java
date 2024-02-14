package com.vgames.survivalreckoning.framework.log;

import com.vgames.survivalreckoning.framework.log.annotation.GenerateCriticalFile;
import com.vgames.survivalreckoning.framework.log.annotation.LogAlias;
import com.vgames.survivalreckoning.framework.log.annotation.LogInfo;
import com.vgames.survivalreckoning.framework.log.annotation.NotDebugLog;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.vgames.survivalreckoning.framework.log.LogColor.*;
import static com.vgames.survivalreckoning.framework.log.LogLevel.*;

public abstract class Logger {

    private static final boolean globalDebugDefinition = true;

    private final String name;
    private final DateFormat dateFormat;
    private LogLevel logLevel;
    private boolean isVerboseException;
    private boolean isDebugging;
    private boolean generateCriticalFile;

    protected Logger() {
        if(getClass().isAnnotationPresent(LogAlias.class)) {
            String alias = getClass().getDeclaredAnnotation(LogAlias.class).value();
            this.name = alias.isEmpty() ? getClass().getSimpleName() : alias;
        } else {
            this.name = getClass().getSimpleName();
        }
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        defineProperties(getClass());
    }

    private <T> void defineProperties(Class<T> klass) {
        if (klass.isAnnotationPresent(LogInfo.class)) {
            LogInfo info = klass.getDeclaredAnnotation(LogInfo.class);
            this.logLevel = info.level();
            this.isVerboseException = info.verbose();
        } else {
            this.logLevel = LogLevel.INFO;
            this.isVerboseException = true;
        }

        if(klass.isAnnotationPresent(GenerateCriticalFile.class)) {
            this.generateCriticalFile = true;
        } else {
            this.generateCriticalFile = false;
        }

        this.isDebugging = !klass.isAnnotationPresent(NotDebugLog.class) && globalDebugDefinition;
    }

    private String getFormattedTimestamp() {
        return dateFormat.format(new Date());
    }

    private String formatLogMessage(String level, String message, String color) {
        int maxSpacesLength = Arrays.stream(values()).mapToInt(_level -> _level.name().length()).max().orElse(0);

        int currentSpacesLength = level.length();

        String formattedName = " ".repeat(1 + (maxSpacesLength - currentSpacesLength)) + BOLD + name + RESET;

        return String.format("[%s] %s %s: %s", getFormattedTimestamp(), color + level + RESET, formattedName, message);
    }

    private String formatExceptionMessage(Throwable throwable) {
        if (isVerboseException) {
            StringBuilder exceptionMessage = new StringBuilder();
            exceptionMessage.append(throwable.getClass().getName()).append(": ").append(throwable.getMessage()).append("\n");
            for (StackTraceElement element : throwable.getStackTrace()) {
                exceptionMessage.append("\t").append(element.toString()).append("\n");
            }
            return exceptionMessage.toString();
        } else {
            return throwable.getClass().getSimpleName() + (throwable.getMessage() != null ? ": " : "") + throwable.getMessage();
        }
    }

    protected void printMessage(LogLevel level, String message, String color) {
        if (level.ordinal() <= this.logLevel.ordinal()) {
            System.out.println(formatLogMessage(level.name(), message, color));
        }
    }

    protected void info(String message) {
        if (isDebugging) {
            printMessage(INFO, message, COLOR_INFO);
        }
    }

    protected void trace(String message) {
        if (isDebugging) {
            printMessage(TRACE, message, COLOR_TRACE);
        }
    }

    protected void warn(String message) {
        if (isDebugging) {
            printMessage(WARN, message, COLOR_WARN);
        }
    }

    protected void error(String message) {
        printMessage(ERROR, message, COLOR_ERROR);
    }

    protected void critical(String message) {
        printMessage(CRITICAL, message, COLOR_CRITICAL);
    }

    protected void error(String message, Throwable throwable) {
        printMessage(ERROR, message, COLOR_ERROR);
        logException(throwable);
    }

    protected void critical(String message, RuntimeException exception) {
        printMessage(CRITICAL, message, COLOR_CRITICAL);

        if(this.generateCriticalFile) {
            generateCriticalFile(exception, message);
        }
        throwException(exception);
    }

    private void throwException(RuntimeException exception) {
        throw exception;
    }

    protected void breakLine() {
        System.out.println();
    }

    private void logException(Throwable throwable) {
        System.err.println(formatExceptionMessage(throwable));
    }

    protected LogLevel getLogLevel() {
        return this.logLevel;
    }

    private void generateCriticalFile(RuntimeException exception, String message) {
        if (generateCriticalFile) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(getFormattedTimestamp().replaceAll(" ", "_").replaceAll(":", "-") + ".log"))) {
                writer.println("Critical Error:");
                writer.println("Timestamp: " + getFormattedTimestamp());
                writer.println("Message: " + message);
                writer.println("Stack Trace:");
                exception.printStackTrace(writer);
                writer.println("\nAdditional Details:");
                writer.println("Exception Type: " + exception.getClass().getName());
                writer.println("Exception Message: " + exception.getMessage());
                writer.println("Exception Cause: " + (exception.getCause() != null ? exception.getCause().toString() : "N/A"));
                writer.println("Exception Source: " + getExceptionSource(exception));
            } catch (IOException e) {
                error(message, e);
            }
        }
    }

    private String getExceptionSource(Exception exception) {
        StackTraceElement[] elements = exception.getStackTrace();
        if (elements.length > 0) {
            return elements[0].toString();
        }
        return "Unknown";
    }
}
