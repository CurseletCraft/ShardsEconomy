package mino.dx.curseletcraft.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.api.IShards;
import mino.dx.curseletcraft.utils.PluginUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class RegisterPlaceholder extends PlaceholderExpansion {

    private final ShardsEconomy plugin;
    private final IShards shardManager;

    public RegisterPlaceholder(ShardsEconomy plugin) {
        this.plugin = plugin;
        this.shardManager = plugin.getShardsManager();
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String placeholder) {
        if(player != null) {
            long shards = shardManager.getShards(player.getUniqueId());

            switch (placeholder.toLowerCase()) {
                case "shards": // placeholder thành %shardsx_shards%
                    return String.valueOf(shards); // vd: 1234
                case "shards_formatted": // placeholder thành %shardsx_shards_formatted%
                    return PluginUtils.formatWithDot(shards); // vd: 1.234
            }
        }
        return null; // End
    }

    // other functions

    @Override
    public boolean persist() {
        return true; //
    }

    @Override
    @NotNull
    @SuppressWarnings("all")
    public String getAuthor() {
        return plugin.getDescription().getAuthors().get(0);
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return "shardsx";
    }

    @Override
    @NotNull
    @SuppressWarnings("all")
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

}
