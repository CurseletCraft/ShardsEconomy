package mino.dx.curseletcraft.listeners;

import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.api.events.ShardsChangedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

@SuppressWarnings("ClassCanBeRecord")
public class ShardChangedListener implements Listener {

    private final ShardsEconomy plugin;

    public ShardChangedListener(ShardsEconomy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChangedShards(ShardsChangedEvent event) {
        boolean changedLogEnabled = plugin.getConfig().getBoolean("enable-log-change", true);

        UUID uuid = event.getPlayerUUID();
        String playerName = event.getPlayerName() == null ? "unknown" : event.getPlayerName(); // tránh NPE

        int oldShards = event.getOldShards();
        int newShards = event.getNewShards();

        String ctx = "Shards changed: {player_name: '%s', player_uuid: '%s', old_shards: '%d', new_shards: '%d'}"
                .formatted(playerName, uuid, oldShards, newShards);
        if(changedLogEnabled) {
            plugin.getLogger().info(ctx);
        }
    }
}
