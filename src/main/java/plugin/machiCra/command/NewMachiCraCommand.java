package plugin.machiCra.command;

import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.machiCra.Maneger.PlayerFileManager;

public class NewMachiCraCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private PlayerFileManager playerFileManager;

    public NewMachiCraCommand(JavaPlugin plugin){
      this.plugin=plugin;
      this.playerFileManager = new PlayerFileManager(plugin);
    }

  @Override
  public boolean onCommand (CommandSender sender, Command command, String label, String[] args){
    if (sender instanceof Player player){
      UUID uuid = player.getUniqueId();

      if (playerFileManager.playerFileExists(uuid)){
        player.sendMessage("すでにプレイデータがあります。/machiCra で続きから再開してください！");
        return true;
      }
      boolean create = playerFileManager.createPlayerFile(uuid);
      if (!create){
        player.sendMessage("エラー：プレイヤーデータの作成に失敗しました。");
        return true;
      }
      player.sendTitle(ChatColor.GREEN + "ようこそ！MachiCraへ！","あなたの村づくりが、今、始まる---",0,50,0);
      player.sendMessage(ChatColor.GREEN+"最初の村「ほんわか村」があなたを待っています…");
    }
    return true;
  }
}
