package mino.dx.curseletcraft.api;

/**
 * Ví dụ cách sử dụng ShardsAPI trong plugin khác.
 * <p>
 * 1. Đảm bảo plugin của bạn khai báo depend/softdepend tới ShardsEconomy trong plugin.yml:
 * <p>
 * <code>depend: [ShardsEconomy]</code>
 * <p>
 * 2. Trong class main (extends JavaPlugin), lấy IShards khi plugin được enable:
 * <pre><code>
 * import mino.dx.curseletcraft.api.interfaces.IShards;
 * import mino.dx.curseletcraft.api.ShardsAPI;
 * import org.bukkit.plugin.java.JavaPlugin;
 *
 * public class YourPlugin extends JavaPlugin {
 *
 *     private IShards shardsManager;
 *
 *    {@literal @Override}
 *     public void onEnable() {
 *         // Lấy instance của ShardsEconomy
 *         shardsManager = ShardsAPI.get();
 *
 *         // Gọi class khác để dùng thử
 *         TestClass test = new TestClass(this);
 *         test.exampleMethod();
 *     }
 *
 *     public IShards getShardsManager() {
 *         return shardsManager;
 *     }
 * }
 * </code></pre>
 * 3. Trong class khác, bạn có thể lấy shardsManager từ plugin main và gọi các phương thức:
 * <pre><code>
 * import mino.dx.curseletcraft.api.interfaces.IShards;
 * import java.util.UUID;
 *
 * public class TestClass {
 *
 *     private final YourPlugin plugin;
 *
 *     public TestClass(YourPlugin plugin) {
 *         this.plugin = plugin;
 *     }
 *
 *     public void exampleMethod() {
 *         IShards shardsManager = plugin.getShardsManager();
 *
 *         UUID exampleUUID = UUID.fromString("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx");
 *         int shards = shardsManager.getShards(exampleUUID);
 *
 *         plugin.getLogger().info("UUID " + exampleUUID + " đang có " + shards + " shard(s).");
 *     }
 * }
 * </code></pre>
 * 4. Các method hữu ích trong IShards:
 * <br>
 *    <br>- getShards(UUID uuid)    → Lấy số shards hiện có
 *    <br>- setShards(UUID uuid, int amount) → Đặt số shards mới
 *    <br>- addShards(UUID uuid, int amount) → Cộng thêm shards
 *    <br>- removeShards(UUID uuid, int amount) → Trừ shards
 */
@SuppressWarnings("all")
public class ShardsAPITuto {
}
