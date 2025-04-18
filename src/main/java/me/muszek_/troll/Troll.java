package me.muszek_.troll;

import me.muszek_.troll.commands.CommandManager;
import me.muszek_.troll.listeners.AppleListener;
import me.muszek_.troll.settings.Settings;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Troll extends JavaPlugin {

	private static Troll instance;

	@Override
	public void onEnable() {
		instance = this;
		getLogger().warning("EpicTroll plugin has been enabled!");

		YamlUpdater updater = new YamlUpdater(this);
		FileConfiguration config = updater.update("config.yml");
		FileConfiguration lang = updater.update("lang.yml");
		
		getCommand("troll").setExecutor(new CommandManager());
		getCommand("troll").setTabCompleter(new CommandManager());
		getServer().getPluginManager().registerEvents(new AppleListener(this), this);

		Settings.load();

		int pluginId = 25451;
		Metrics metrics = new Metrics(this, pluginId);
	}

	@Override
	public void onDisable() {
		getLogger().warning("EpicTroll plugin has been disabled!");

	}

	public static Troll getInstance() {
		return instance;
	}
}
