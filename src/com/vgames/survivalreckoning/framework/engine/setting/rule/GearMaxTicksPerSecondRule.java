package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;
import com.vgames.survivalreckoning.framework.engine.setting.TokenType;

public class GearMaxTicksPerSecondRule extends SettingParseRule<Integer> {
    protected GearMaxTicksPerSecondRule(SettingsFileParser parser) {
        super(parser);
    }

    @Override
    public Integer parse() {
        consume(TokenType.EQUAL, "Expect '=' after 'max_ticks_per_second' identifier.");
        consume(TokenType.INTEGER, "Expect integer after '='.");

        return Integer.parseInt(previous().getLexeme());
    }
}
