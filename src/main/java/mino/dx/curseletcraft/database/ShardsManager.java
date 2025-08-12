package mino.dx.curseletcraft.database;

import mino.dx.curseletcraft.api.IShards;
import mino.dx.curseletcraft.utils.PluginUtils;

import java.sql.*;
import java.util.UUID;

// SQLite
@SuppressWarnings("all")
public class ShardsManager implements IShards {
    private final Connection connection;

    public ShardsManager(String dbPath) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
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
