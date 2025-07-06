package plugin.machiCra;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.machiCra.command.MachiCraCommand;
import plugin.machiCra.command.NewMachiCraCommand;

public final class Main extends JavaPlugin implements Listener{

  @Override
  public void onEnable(){
    Bukkit.getPluginManager().registerEvents(this,this);
    getCommand("machiCra").setExecutor(new MachiCraCommand(this));

    Bukkit.getPluginManager().registerEvents(this,this);
    getCommand("newMachiCra").setExecutor(new NewMachiCraCommand(this));
  }
}