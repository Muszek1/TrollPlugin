package me.muszek_.troll;

import me.muszek_.troll.commands.CommandManager;
import me.muszek_.troll.settings.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public final class Troll extends JavaPlugin {

	private static Troll instance;

	@Override
	public void onEnable() {
		instance = this;
		getLogger().warning("EpicTroll plugin has been enabled!");

		getCommand("troll").setExecutor(new CommandManager());
		getCommand("troll").setTabCompleter(new CommandManager());
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
