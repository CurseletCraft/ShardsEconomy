package mino.dx.curseletcraft.api;

import mino.dx.curseletcraft.ShardsEconomy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;

@SuppressWarnings("unused")
@ApiStatus.Experimental
public class ShardsAPI {
    public static IShards get() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("ShardsEconomy");
        if (plugin instanceof ShardsEconomy shardsPlugin) {
            return shardsPlugin.getShardsManager();
        }
        throw new IllegalStateException("ShardsEconomy plugin is not loaded or invalid");
    }
}
