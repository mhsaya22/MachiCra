package plugin.machiCra.Maneger;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.machiCra.data.PlayerData;

@Setter
@Getter


public class PlayerFileManager {

  private final File dataFolder;

  public PlayerFileManager(JavaPlugin plugin) {
    this.dataFolder = new File(plugin.getDataFolder(), "playerData");
    if (!dataFolder.exists()) {
      dataFolder.mkdirs();
    }
  }

  public File getPlayerId(UUID uuid) {
    return new File(dataFolder, uuid + ".txt");
  }

  public boolean playerFileExists(UUID uuid) {
    return getPlayerId(uuid).exists();
  }





  /**
   * 指定されたUUIDに対応する新しいプレイヤーデータファイルを作成します。
   * @param uuid プレイヤーUUID
   */
  public boolean createPlayerFile(UUID uuid){
    File playerFile = getPlayerId(uuid);
    if (playerFile.exists()){
      return false;
    }
    try{
      playerFile.createNewFile();
      try(BufferedWriter bw = new BufferedWriter(new FileWriter(playerFile))){
        bw.write("ステージ:1");
        bw.newLine();
        bw.write("レベル:1");
        bw.newLine();
        bw.write("タスク進捗:1");
      }
return true;
    } catch (IOException e){
      e.printStackTrace();
      return false;
    }
  }

  /**
   * プレイヤーデータファイルを読み取り、ステージ・レベル・進捗の情報からPlayerDataの作成をします。
   *
   * @param playerFile プレイヤーデータファイル
   * @return PlayerData
   */
  public PlayerData playerFile(File playerFile) {
    try {
      List<String> lines = Files.readAllLines(playerFile.toPath());
      String stage = "";
      int level = 0;
      int progress = 0;

      for (String s : lines) {
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
      return new PlayerData(stage, level, progress);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}

