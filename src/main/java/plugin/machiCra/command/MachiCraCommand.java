package plugin.machiCra.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MachiCraCommand implements CommandExecutor {

  private final JavaPlugin plugin;

  public MachiCraCommand(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      UUID uuid = player.getUniqueId();

      File dataFolder = new File(plugin.getDataFolder(), "playerData");
      File playerFile = new File(dataFolder, uuid + ".txt");

      if (!playerFile.exists()) {
        player.sendMessage(
            "初回プレイデータがありません。/newMachiCra　で新規データを作成してください！");
        return true;
      }
      try {
        List<String> lines = Files.readAllLines(playerFile.toPath());
        String stage = "";
        int level = 0;
        int progress = 0;
        for (String s : lines){
          String[] parts = s.split(":");

          //配列の要素が2個だったら
          if (parts.length == 2) {
            String key = parts[0];
            String value = parts[1];

            switch (key) {
              case "ステージ" -> stage = value;
              case "レベル" -> level = Integer.parseInt(value);
              case "進捗" -> progress = Integer.parseInt(value);
            }
          }
        }

        player.sendMessage("おかえりなさい！続きから始めましょう！");
        player.sendMessage("現在のステージ" + stage);
        player.sendMessage("レベル" + level);
        player.sendMessage("進捗" + progress);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
      return true;
    }
  }
