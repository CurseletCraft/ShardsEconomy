package mino.dx.curseletcraft.database;

import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.api.IShards;
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
//                if (!dataFolder.exists()) {
//                    if (!dataFolder.mkdirs()) {
//                        plugin.getLogger().severe("Không thể tạo thư mục plugin: " + dataFolder.getAbsolutePath());
//                        throw new RuntimeException("Thư mục plugin không thể được tạo.");
//                    }
//                }

                File dbFile = new File(dataFolder, "shards.db");
                shardManager = new ShardsManager(dbFile.getPath());

            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Không thể khởi tạo ShardsManager: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @ApiStatus.Experimental
    @SuppressWarnings("unused")
    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public IShards getShardsManager() {
        return shardManager;
    }
}

