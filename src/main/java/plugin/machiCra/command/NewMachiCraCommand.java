package plugin.machiCra.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NewMachiCraCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    public NewMachiCraCommand(JavaPlugin plugin){
      this.plugin=plugin;
    }

  @Override
  public boolean onCommand (CommandSender sender, Command command, String label, String[] args){
    if (sender instanceof Player player){
      UUID uuid = player.getUniqueId();
      File dataFolder = new File(plugin.getDataFolder(),"playerData");
      File playerFile = new File(dataFolder,uuid + ".txt");

      if (playerFile.exists()){
        player.sendMessage("すでにプレイデータがあります。/machiCra で続きから再開してください！");
        return true;
      }
      player.sendTitle(ChatColor.GREEN + "ようこそ！MachiCraへ！","あなたの村づくりが、今、始まる---",0,50,0);
      player.sendMessage(ChatColor.GREEN+"最初の村「ほんわか村」があなたを待っています…");
      try {
        dataFolder.mkdirs();
        playerFile.createNewFile();

        FileWriter fw = new FileWriter(playerFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("ステージ:1");
        bw.newLine();
        bw.write("レベル:1");
        bw.newLine();
        bw.write("タスク進捗:1");
        bw.close();
        player.sendMessage("データファイルを作成しました！");

      } catch (IOException e) {
        player.sendMessage("エラー：プレイヤーデータの作成に失敗しました。");
        e.printStackTrace();
      }

    }

    return true;
  }

}
