package me.muszek_.troll;

import me.muszek_.troll.commands.CommandManager;
import me.muszek_.troll.listeners.*;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.Logger;
import me.muszek_.troll.utils.UpdateChecker;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Troll extends JavaPlugin {

	private static Troll instance;

	private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();


	@Override
	public void onEnable() {
		instance = this;
		Logger.log(Logger.LogLevel.INFO, "EpicTroll plugin has been enabled!");

		YamlUpdater updater = new YamlUpdater(this);
		FileConfiguration config = updater.update("config.yml");
		FileConfiguration lang = updater.update("lang.yml");

		JumplockListener jumplockListener = new JumplockListener();
		BlockCraftListener BlockCraftListener = new BlockCraftListener();
		getServer().getPluginManager().registerEvents(jumplockListener, this);
		getServer().getPluginManager().registerEvents(BlockCraftListener, this);

		CommandManager commandManager = new CommandManager(jumplockListener, BlockCraftListener);
		getCommand("troll").setExecutor(commandManager);
		getCommand("troll").setTabCompleter(commandManager);
		getServer().getPluginManager().registerEvents(new AppleListener(this), this);
		getServer().getPluginManager().registerEvents(new DiamondListener(this), this);
		getServer().getPluginManager().registerEvents(new LaunchListener(this), this);
		getServer().getPluginManager().registerEvents(new CookieListener(this), this);
		getServer().getPluginManager().registerEvents(new MenuListener(), this);


		Settings.load();

		int pluginId = 25451;
		Metrics metrics = new Metrics(this, pluginId);

		new UpdateChecker(this, 124041).getLatestVersion(version -> {
			if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
				Logger.log(Logger.LogLevel.INFO, "Plugin EpicTroll is up to date. ");
			} else {
				Logger.log(Logger.LogLevel.WARNING, "Plugin EpicTroll has an update. Update: https://www.spigotmc.org/resources/124041/ ");
			}

		});


	}

	@Override
	public void onDisable() {
		getLogger().warning("EpicTroll plugin has been disabled!");

	}

	public static Troll getInstance() {
		return instance;
	}

	public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
		PlayerMenuUtility playerMenuUtility;

		if (playerMenuUtilityMap.containsKey(player)) {
			return playerMenuUtilityMap.get(player);
		} else {
			playerMenuUtility = new PlayerMenuUtility(player);
			playerMenuUtilityMap.put(player, playerMenuUtility);
			return playerMenuUtility;
		}
	}
}
