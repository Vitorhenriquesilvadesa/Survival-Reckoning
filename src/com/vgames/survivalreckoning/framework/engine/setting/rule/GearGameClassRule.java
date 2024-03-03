package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;
import com.vgames.survivalreckoning.framework.engine.setting.TokenType;

public class GearGameClassRule extends SettingParseRule<Class<? extends Game>> {


    protected GearGameClassRule(SettingsFileParser parser) {
        super(parser);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Game> parse() {
        consume(TokenType.EQUAL, "Expect '=' after 'game_class' identifier.");
        consume(TokenType.STRING, "Expect class name in string format after '='");

        try {
            return (Class<? extends Game>) Class.forName(previous().getLexeme());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
