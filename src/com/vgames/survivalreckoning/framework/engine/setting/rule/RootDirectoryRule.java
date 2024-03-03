package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;
import com.vgames.survivalreckoning.framework.engine.setting.TokenType;

public class RootDirectoryRule extends SettingParseRule<String> {

    protected RootDirectoryRule(SettingsFileParser parser) {
        super(parser);
    }

    @Override
    public String parse() {
        consume(TokenType.EQUAL, "Expect '=' after 'root_directory' identifier.");
        consume(TokenType.STRING, "Expect string after '='.");
        return previous().getLexeme();
    }
}
