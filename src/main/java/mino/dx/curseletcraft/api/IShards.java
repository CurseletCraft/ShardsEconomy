package mino.dx.curseletcraft.api;

import mino.dx.curseletcraft.database.*;

import java.util.UUID;

/**
 * Interface chính để lấy thông tin về shards.
 * <p>
 * Hỗ trợ MySQL, SQLite, được implementation với {@link ShardsManager}, {@link ShardsManagerMySQL}.
 */
public interface IShards {

    /**
     * Lấy số shard của 1 người chơi (dùng uuid).
     *
     * @param uuid UUID của một người chơi
     * @return số shards đang có (có thể là 0 nếu database lỗi hoặc lỗi khác)
     */
    int getShards(UUID uuid);

    /**
     * Đặt số shard của 1 người chơi (dùng uuid).
     *
     * @param uuid UUID của một người chơi
     * @param amount Số shard muốn đặt vào (không thể nhỏ hơn 0)
     */
    void setShards(UUID uuid, int amount);

    /**
     * Thêm số shard cho 1 người chơi (dùng uuid).
     *
     * @param uuid UUID của một người chơi
     * @param amount Số shard muốn thêm vào (không thể nhỏ hơn 0)
     */
    void addShards(UUID uuid, int amount);

    /**
     * Xóa số lượng shard của 1 người chơi (dùng uuid).
     *
     * @param uuid UUID của một người chơi
     * @param amount Số shard muốn xóa (không thể nhỏ hơn 0)
     */
    void removeShards(UUID uuid, int amount);
}
