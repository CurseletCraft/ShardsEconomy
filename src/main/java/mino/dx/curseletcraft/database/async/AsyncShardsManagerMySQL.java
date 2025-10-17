package mino.dx.curseletcraft.database.async;

import mino.dx.curseletcraft.ShardsEconomy;
import mino.dx.curseletcraft.api.interfaces.IShards;
import mino.dx.curseletcraft.api.interfaces.IShardsAsync;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AsyncShardsManagerMySQL implements IShardsAsync {

    private IShards syncImpl;

    public AsyncShardsManagerMySQL(ShardsEconomy plugin) {

    }

    @Override
    public CompletableFuture<Integer> getShardsAsync(UUID uuid) {
        return null;
    }

    @Override
    public CompletableFuture<Void> setShardsAsync(UUID uuid, int amount) {
        return null;
    }

    @Override
    public CompletableFuture<Void> addShardsAsync(UUID uuid, int amount) {
        return null;
    }

    @Override
    public CompletableFuture<Void> removeShardsAsync(UUID uuid, int amount) {
        return null;
    }

    @Override
    public int getShards(UUID uuid) {
        return 0;
    }

    @Override
    public void setShards(UUID uuid, int amount) {

    }

    @Override
    public void addShards(UUID uuid, int amount) {

    }

    @Override
    public void removeShards(UUID uuid, int amount) {

    }
}
