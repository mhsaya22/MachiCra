package plugin.machiCra.command;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.IIOException;
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
        FileReader fr = new FileReader(playerFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
          player.sendMessage(line);
        }
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

      player.sendMessage("おかえりなさい！続きから始めましょう！");
    }
      return false;
    }
  }
