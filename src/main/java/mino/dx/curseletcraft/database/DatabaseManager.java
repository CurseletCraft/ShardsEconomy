package mino.dx.curseletcraft.database;

import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.api.interfaces.IShards;
import mino.dx.curseletcraft.database.async.AsyncShardsManager;
import mino.dx.curseletcraft.database.async.AsyncShardsManagerMySQL;
import mino.dx.curseletcraft.database.sync.ShardsManager;
import mino.dx.curseletcraft.database.sync.ShardsManagerMySQL;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.ApiStatus;

import java.io.File;
import java.sql.SQLException;

public class DatabaseManager {

    private final IShards shardManager;
    private final DatabaseType databaseType;

    public DatabaseManager(ShardsEconomy plugin) {
        FileConfiguration config = plugin.getConfig();

        // true = mysql, false = sqlite
        try {
            if (config.getBoolean("database.enable")) {
                databaseType = DatabaseType.MYSQL;
                shardManager = new ShardsManagerMySQL(plugin);
            } else {
                databaseType = DatabaseType.SQLITE;
                File dataFolder = plugin.getDataFolder();
                File dbFile = new File(dataFolder, "shards.db");
                shardManager = new ShardsManager(plugin, dbFile.getPath());
            }

            plugin.getLogger().info("Database enabled");
            plugin.getLogger().info("Database type: " + databaseType);

        } catch (SQLException e) {
            plugin.getLogger().severe("Không thể khởi tạo ShardsManager: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @ApiStatus.Experimental
    public DatabaseManager(ShardsEconomy plugin, String... varags) {
        FileConfiguration config = plugin.getConfig();
        boolean isAsync = config.getBoolean("database.enable-async", false);
        boolean isMySQL = config.getBoolean("database.enable-mysql", false);

        // Khai báo bên ngoài
        IShards shardsManager; // private
        DatabaseType type; // private

        if(isAsync) {
            shardsManager = isMySQL ? new AsyncShardsManagerMySQL(plugin) : new AsyncShardsManager(plugin);
        } else {
            shardsManager = isMySQL ? new ShardsManagerMySQL(plugin) : new ShardsManager(plugin);
        }
    }

    public IShards getShardsManager() {
        return shardManager;
    }

    @Override
    public String toString() {
        return "Using " + databaseType + " as Database Type.";
    }
}