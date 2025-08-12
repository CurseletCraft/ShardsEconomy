package mino.dx.curseletcraft.commands.handlers;

import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.api.IShards;
import mino.dx.curseletcraft.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class SetShardHandler {

    public static void execute(ShardsEconomy plugin, CommandSender sender, String targetName, int amount) {

        OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
        UUID uuid = target.getUniqueId();
        IShards manager = plugin.getShardsManager();

        manager.setShards(uuid, amount);
        sender.sendMessage(PluginUtils.formatMessage("&aĐã đặt &dShard &acho " + target.getName() + " &avới số lượng &e" + amount));
    }
}
