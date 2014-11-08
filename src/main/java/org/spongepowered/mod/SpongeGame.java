/**
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered.org <http://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.mod;

import com.google.common.base.Optional;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.Platform;
import org.spongepowered.api.entity.Player;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.api.service.scheduler.Scheduler;
import org.spongepowered.api.title.Title;
import org.spongepowered.api.world.World;
import org.spongepowered.mod.event.SpongeEventManager;
import org.spongepowered.mod.plugin.SpongePluginManager;

import java.util.Collection;
import java.util.UUID;

public final class SpongeGame implements Game {

    private static final String apiVersion = Game.class.getPackage().getImplementationVersion();
    private static final String implementationVersion = SpongeGame.class.getPackage().getImplementationVersion();
    private final Logger logger = LogManager.getLogger("sponge");
    private final SpongePluginManager pluginManager;
    private final SpongeEventManager eventManager;

    public SpongeGame() {
        this.pluginManager = new SpongePluginManager();
        this.eventManager = new SpongeEventManager(this);
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public Platform getPlatform() {
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT:
                return Platform.CLIENT;
            default:
                return Platform.SERVER;
        }
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public Collection<World> getWorlds() {
        throw new UnsupportedOperationException();
        /*List<World> worlds = new ArrayList<World>();
        for (WorldServer worldServer : DimensionManager.getWorlds()) {
            worlds.add((World) worldServer);
        }
        return worlds;*/
    }

    @Override
    public World getWorld(UUID uniqueId) {
        // TODO: This needs to map to world id's somehow
        return null;
    }

    @Override
    public World getWorld(String worldName) {
        for (World world : getWorlds()) {
            if (world.getName().equals(worldName)) {
                return world;
            }
        }
        return null;
    }

    @Override
    @SideOnly(Side.SERVER)
    public void broadcastMessage(String message) {
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(message));
    }

    @Override
    public Title createTitle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Title updateTitle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getAPIVersion() {
        return apiVersion != null ? apiVersion : "UNKNOWN";
    }

    @Override
    public String getImplementationVersion() {
        return implementationVersion != null ? implementationVersion : "UNKNOWN";
    }

    @Override
    public GameRegistry getRegistry() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Scheduler getScheduler() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Player> getOnlinePlayers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxPlayers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Player> getPlayer(UUID uniqueId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        throw new UnsupportedOperationException();
    }
}
