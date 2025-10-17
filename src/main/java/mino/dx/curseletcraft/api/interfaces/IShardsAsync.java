package mino.dx.curseletcraft.api.interfaces;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IShardsAsync extends IShards {
    CompletableFuture<Integer> getShardsAsync(UUID uuid);
    CompletableFuture<Void> setShardsAsync(UUID uuid, int amount);
    CompletableFuture<Void> addShardsAsync(UUID uuid, int amount);
    CompletableFuture<Void> removeShardsAsync(UUID uuid, int amount);
}
