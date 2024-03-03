package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;
import com.vgames.survivalreckoning.framework.engine.setting.Token;
import com.vgames.survivalreckoning.framework.engine.setting.TokenType;

public abstract class SettingParseRule<T> {

    protected SettingsFileParser parser;

    protected SettingParseRule(SettingsFileParser parser) {
        this.parser = parser;
    }

    public abstract T parse();

    protected Token advance() {
        return parser.advance();
    }

    protected Token peek() {
        return parser.peek();
    }

    protected void consume(TokenType exptect, String message) {
        parser.consume(exptect, message);
    }

    protected Token previous() {
        return parser.previous();
    }
}
