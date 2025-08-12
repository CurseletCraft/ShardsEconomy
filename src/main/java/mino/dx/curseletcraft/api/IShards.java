package mino.dx.curseletcraft.api;

import java.util.UUID;

public interface IShards {
    int getShards(UUID uuid);
    void setShards(UUID uuid, int amount);
    void addShards(UUID uuid, int amount);
    void removeShards(UUID uuid, int amount);
}
