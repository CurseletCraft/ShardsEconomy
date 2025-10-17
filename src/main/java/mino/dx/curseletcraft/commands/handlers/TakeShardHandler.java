package mino.dx.curseletcraft.commands.handlers;

import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.api.events.ShardsChangedEvent;
import mino.dx.curseletcraft.api.interfaces.IShards;
import mino.dx.curseletcraft.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class TakeShardHandler {
    public static void execute(ShardsEconomy plugin, CommandSender sender, String targetName, int amount) {
        OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
        UUID uuid = target.getUniqueId();
        IShards manager = plugin.getShardsManager();

        manager.removeShards(uuid, amount);

        // call event
        int balance = manager.getShards(uuid);
        ShardsChangedEvent event = new ShardsChangedEvent(target, balance, amount);
        event.callEvent();

        sender.sendMessage(PluginUtils.formatMessage("&aĐã xóa &e" + amount + " &dShard &akhỏi " + target.getName()));
    }
}
