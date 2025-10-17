package mino.dx.curseletcraft.database.sync;

import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.api.interfaces.IShards;
import mino.dx.curseletcraft.utils.PluginUtils;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

// SQLite
public class ShardsManager implements IShards {

    private final ShardsEconomy plugin;

    private final Connection connection;
    private final boolean isAsync;

    public ShardsManager(ShardsEconomy plugin, String dbPath) throws SQLException {
        this.plugin = plugin;
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        this.isAsync = plugin.getConfig().getBoolean("database.enable-async", true);
        createTable();
    }

    private void createTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS shards (
                    uuid TEXT PRIMARY KEY,
                    amount INTEGER NOT NULL
                );
            """);
        }
    }

    @Override
    public int getShards(UUID uuid) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT amount FROM shards WHERE uuid = ?")) {
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("amount") : 0;
        } catch (SQLException e) {
            PluginUtils.err(e.getMessage());
            return 0;
        }
    }

    @Override
    public void setShards(UUID uuid, int amount) {
        Runnable runnable = () -> {
            try (PreparedStatement ps = connection.prepareStatement("""
                INSERT INTO shards (uuid, amount)
                VALUES (?, ?)
                ON CONFLICT(uuid) DO UPDATE SET amount = excluded.amount
            """)) {
                ps.setString(1, uuid.toString());
                ps.setInt(2, Math.max(0, amount));
                ps.executeUpdate();
            } catch (SQLException e) {
                PluginUtils.err(e.getMessage());
            }
        };

        if (isAsync) {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
        } else {
            runnable.run();
        }
    }

    @Override
    public void addShards(UUID uuid, int amount) {
        int current = getShards(uuid);
        setShards(uuid, current + amount);
    }

    @Override
    public void removeShards(UUID uuid, int amount) {
        int current = getShards(uuid);
        setShards(uuid, Math.max(0, current - amount));
    }

    public void close() {
        try {
            if (!connection.isClosed()) connection.close();
        } catch (SQLException e) {
            PluginUtils.err(e.getMessage());
        }
    }
}
