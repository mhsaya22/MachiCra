package plugin.machiCra.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PlayerData {

  private String stage;
  private int level;
  private int progress;

  public PlayerData(String stage, int level, int progress){
    this.stage = stage;
    this.level = level;
    this.progress = progress;
  }

}
