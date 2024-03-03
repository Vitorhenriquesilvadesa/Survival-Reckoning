package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;
import com.vgames.survivalreckoning.framework.engine.setting.TokenType;

public class WindowFullscreenRule extends SettingParseRule<Boolean> {

    protected WindowFullscreenRule(SettingsFileParser parser) {
        super(parser);
    }

    @Override
    public Boolean parse() {
        consume(TokenType.EQUAL, "Expect '=' after fullscreen identifier.");
        consume(TokenType.BOOLEAN, "Expect boolean value after '='");

        return Boolean.parseBoolean(previous().getLexeme());
    }
}
