package mino.dx.curseletcraft.api.events;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@SuppressWarnings("unused")
public class ShardsChangedEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final UUID playerUUID;
    private final String playerName;
    private final int oldShards;
    private final int newShards;

    public ShardsChangedEvent(@NotNull OfflinePlayer player, int oldShards, int newShards) {
        this.playerUUID = player.getUniqueId();
        this.playerName = player.getName();
        this.oldShards = oldShards;
        this.newShards = newShards;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getOldShards() {
        return oldShards;
    }

    public int getNewShards() {
        return newShards;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
