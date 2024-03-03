package com.vgames.survivalreckoning.framework.engine.setting.rule;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileParser;

public class SettingRuleFactory {

    private final SettingsFileParser parser;

    public SettingRuleFactory(SettingsFileParser parser) {
        this.parser = parser;
    }

    public <T> T parseWithRule(SettingRule settingRuleType, Class<T> returnType) {

        SettingParseRule<?> parseRule;

        switch (settingRuleType) {
            case GearMaxTicksPerSecond: {
                parseRule = new GearMaxTicksPerSecondRule(parser);
                break;
            }
            case GearUseColdAnnotations: {
                parseRule = new GearUseColdAnnotationsRule(parser);
                break;
            }
            case GearGameClass: {
                parseRule = new GearGameClassRule(parser);
                break;
            }
            case GearRootDirectory: {
                parseRule = new RootDirectoryRule(parser);
                break;
            }
            case WindowFullscreen: {
                parseRule = new WindowFullscreenRule(parser);
                break;
            }
            case WindowSize: {
                parseRule = new WindowSizeRule(parser);
                break;
            }
            case WindowTitle: {
                parseRule = new WindowTitleRule(parser);
                break;
            }
            case DebugGenerateCriticalFiles: {
                parseRule = new GenerateCriticalFilesRule(parser);
                break;
            }
            case DebugShowLogs: {
                parseRule = new ShowDebugLogsRule(parser);
                break;
            }
            default: {
                throw new IllegalStateException("Unknown setting parse rule.");
            }
        }

        return returnType.cast(parseRule.parse());
    }
}
