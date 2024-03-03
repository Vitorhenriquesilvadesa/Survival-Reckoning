package com.vgames.survivalreckoning;

import com.vgames.survivalreckoning.framework.application.Application;
import com.vgames.survivalreckoning.game.SurvivalReckoning;

public class Main {
    public static void main(String[] args) {
        Application.init(SurvivalReckoning.class, "src/resources/config/project.gconfig");
        Application.run();
    }
}
