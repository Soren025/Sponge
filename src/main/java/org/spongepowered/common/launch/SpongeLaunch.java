package org.spongepowered.common.launch;

import static com.google.common.base.Preconditions.checkState;

import java.io.File;

import javax.annotation.Nullable;

public class SpongeLaunch {

    @Nullable private static File gameDir;
    @Nullable private static File configDir;
    @Nullable private static File pluginsDir;

    private SpongeLaunch() {
    }

    public static void initialize(File gameDir, File configDir, File pluginsDir) {
        if (gameDir == null) {
            gameDir = new File(".");
        }

        SpongeLaunch.gameDir = gameDir;
        SpongeLaunch.configDir = configDir != null ? configDir : new File(gameDir, "config");
        SpongeLaunch.pluginsDir = pluginsDir != null ? pluginsDir : new File(gameDir, "mods");
    }

    public static File getGameDirectory() {
        checkState(gameDir != null, "Sponge was not initialized");
        return gameDir;
    }

    public static File getConfigDirectory() {
        checkState(configDir != null, "Sponge was not initialized");
        return configDir;
    }

    public static File getPluginsDirectory() {
        checkState(pluginsDir != null, "Sponge was not initialized");
        return pluginsDir;
    }

}
