package plugin.machiCra.command;

import java.io.File;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class NewMachiCraCommand implements CommandExecutor {

  @Override
  public boolean onCommand (CommandSender sender, Command command, String label, String[] args){
    if (sender instanceof Player player){
      UUID uuid = player.getUniqueId();
      File dataFolder = new File(getPlugin().getDataFolder(),"playerData")
    }
    return false;
  }

}
