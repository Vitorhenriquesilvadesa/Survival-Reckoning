package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;
import com.vgames.survivalreckoning.framework.engine.setting.TokenType;

public class ShowDebugLogsRule extends SettingParseRule<Boolean> {

    protected ShowDebugLogsRule(SettingsFileParser parser) {
        super(parser);
    }

    @Override
    public Boolean parse() {
        consume(TokenType.EQUAL, "Expect '=' after 'show_logs' identifier.");
        consume(TokenType.BOOLEAN, "Expect boolean after '='.");

        return Boolean.parseBoolean(previous().getLexeme());
    }
}
