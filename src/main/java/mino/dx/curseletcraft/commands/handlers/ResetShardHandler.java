package mino.dx.curseletcraft.commands.handlers;

import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class ResetShardHandler {

    public static void execute(ShardsEconomy plugin, CommandSender sender, String targetName) {

        OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
        UUID uuid = target.getUniqueId();

        plugin.getShardsManager().setShards(uuid, 0);
        sender.sendMessage(PluginUtils.formatMessage("&aĐã reset &dShard &avề 0 cho người chơi &e" + target.getName()));
    }
}
