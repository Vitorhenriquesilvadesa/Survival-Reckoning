package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;
import com.vgames.survivalreckoning.framework.engine.setting.TokenType;

public class DebugGenerateCriticalFilesRule extends SettingParseRule<Boolean> {

    protected DebugGenerateCriticalFilesRule(SettingsFileParser parser) {
        super(parser);
    }

    @Override
    public Boolean parse() {
        consume(TokenType.EQUAL, "Expect '=' after 'generate_critical_files' identifier.");
        consume(TokenType.BOOLEAN, "Expect boolean after '='.");

        return Boolean.parseBoolean(previous().getLexeme());
    }
}
