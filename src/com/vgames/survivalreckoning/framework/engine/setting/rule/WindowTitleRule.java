package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;
import com.vgames.survivalreckoning.framework.engine.setting.TokenType;

public class WindowTitleRule extends SettingParseRule<String> {

    protected WindowTitleRule(SettingsFileParser parser) {
        super(parser);
    }

    @Override
    public String parse() {
        consume(TokenType.EQUAL, "Expect '=' after title identifier.");
        consume(TokenType.STRING, "Expect string after '='.");

        return previous().getLexeme();
    }
}
