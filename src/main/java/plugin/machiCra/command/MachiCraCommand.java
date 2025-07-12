package plugin.machiCra.command;

import java.io.File;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.machiCra.Maneger.PlayerFileManager;
import plugin.machiCra.data.PlayerData;

public class MachiCraCommand implements CommandExecutor {

  private final JavaPlugin plugin;
private final PlayerFileManager playerFileManager;

  public MachiCraCommand(JavaPlugin plugin) {
    this.plugin = plugin;
    this.playerFileManager = new PlayerFileManager(plugin);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = playerFileManager.getPlayerId(uuid);
      if (!playerFile.exists()) {
        player.sendMessage(
            "初回プレイデータがありません。/newMachiCra　で新規データを作成してください！");
        return true;
      }
      PlayerData data = playerFileManager.playerFile(playerFile);

      player.sendMessage("おかえりなさい！続きから始めましょう！");
      player.sendMessage("現在のステージ" + data.getStage());
      player.sendMessage("レベル" + data.getLevel());
      player.sendMessage("進捗" + data.getProgress());
    }
    return true;
  }

}