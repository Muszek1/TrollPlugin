package me.muszek_.troll.settings;

import me.muszek_.troll.Troll;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;

public final class Settings {
	public static String PLAYER_NOT_FOUND;
	public static String MOB_NOT_FOUND;
	public static String NO_PERMISSION;
	public static String PLUGIN_RELOADED;
	public static String WRONG_NUMBER;


	public final static class Fire {
		public static int FIRE_DEFAULT_DURATION;
		public static String FIRE_USAGE;
		public static String FIRE_MESSAGE;
		public static String FIRE_INVALID_DURATION;
	}

	public final static class Mob {
		public static String MOB_USAGE;
		public static String MOB_MESSAGE;
	}

	public final static class Anvil {
		public static String ANVIL_USAGE;
		public static String ANVIL_MESSAGE;
		public static String ANVIL_ERROR;
	}

	public final static class Freeze {
		public static String FREEZE_USAGE;
		public static String FREEZE_MESSAGE;
		public static String FREEZE_TARGET_MESSAGE;
		public static int FREEZE_DEFAULT_DURATION;
		public static String FREEZE_INVALID_DURATION;
	}

	public final static class Knockback {
		public static String KNOCKBACK_ITEM_NAME;
		public static String KNOCKBACK_USAGE;
	}

	public final static class ExplodePlayer {
		public static String EXPLODEPLAYER_MESSAGE;
		public static String EXPLODEPLAYER_GOING_TO_EXPLODE;
		public static String EXPLODEPLAYER_USAGE;
		public static String EXPLODEPLAYER_YOU_WERE_BLOWN_UP;


	}

	public final static class Apple {
		public static String APPLE_USAGE;
		public static String APPLE_EATEN;
		public static String APPLE_GIVEN;
	}

	public final static class Diamond {
		public static String DIAMOND_GIVEN;
		public static int DIAMOND_DURATION;
	}

	public final static class Launch {
		public static String LAUNCHED_PLAYER;
		public static String LAUNCH_USAGE;
	}

	public final static class Cookie {
		public static String COOKIE_GIVEN;
		public static String COOKIE_ITEM_NAME;
		public static Boolean COOKIE_GLOW;
	}

	public final static class Jumplock {
		public static String JUMPLOCK_USAGE;
		public static String JUMPLOCK_LOCK;
		public static String JUMPLOCK_UNLOCK;

	}

	public static void load() {
		Troll instance = Troll.getInstance();
		String pathConfig = "config.yml";
		String pathLang = "lang.yml";
		File configFile = new File(instance.getDataFolder(), pathConfig);
		File langFile = new File(instance.getDataFolder(), pathLang);

		if (!configFile.exists()) {
			instance.saveResource(pathConfig, false);
		}
		YamlConfiguration
				defaults = YamlConfiguration.loadConfiguration(new InputStreamReader(instance.getResource(pathConfig)));
		YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);


		if (!langFile.exists()) {
			instance.saveResource(pathLang, false);
		}
		YamlConfiguration
				defaults2 = YamlConfiguration.loadConfiguration(new InputStreamReader(instance.getResource(pathConfig)));
		YamlConfiguration lang = YamlConfiguration.loadConfiguration(langFile);

		PLAYER_NOT_FOUND = lang.getString("Player_Not_Found");
		MOB_NOT_FOUND = lang.getString("Mob_Not_Found");
		NO_PERMISSION = lang.getString("No_Permissions");
		PLUGIN_RELOADED = lang.getString("Plugin_Reloaded");
		WRONG_NUMBER = lang.getString("Wrong_Number");
		Fire.FIRE_DEFAULT_DURATION = config.getInt("Fire.Default_Duration", 3);
		Fire.FIRE_MESSAGE = lang.getString("Fire.Message");
		Fire.FIRE_USAGE = lang.getString("Fire.Usage");
		Fire.FIRE_INVALID_DURATION = lang.getString("Fire.Invalid_Duration");
		Mob.MOB_USAGE = lang.getString("Mob.Usage");
		Mob.MOB_MESSAGE = lang.getString("Mob.Message");
		Anvil.ANVIL_USAGE = lang.getString("Anvil.Usage");
		Anvil.ANVIL_MESSAGE = lang.getString("Anvil.Message");
		Anvil.ANVIL_ERROR = lang.getString("Anvil.Error");
		Knockback.KNOCKBACK_ITEM_NAME = config.getString("Knockback.Item_Name");
		Knockback.KNOCKBACK_USAGE = lang.getString("Knockback.Usage");
		Freeze.FREEZE_MESSAGE = lang.getString("Freeze.Message");
		Freeze.FREEZE_TARGET_MESSAGE = lang.getString("Freeze.Target_Message");
		Freeze.FREEZE_USAGE = lang.getString("Freeze.Usage");
		Freeze.FREEZE_DEFAULT_DURATION = config.getInt("Freeze.Default_Duration", 3);
		Freeze.FREEZE_INVALID_DURATION = lang.getString("Freeze.Invalid_Duration");
		ExplodePlayer.EXPLODEPLAYER_MESSAGE = lang.getString("ExplodePlayer.Message");
		ExplodePlayer.EXPLODEPLAYER_USAGE = lang.getString("ExplodePlayer.Usage");
		ExplodePlayer.EXPLODEPLAYER_GOING_TO_EXPLODE = lang.getString("ExplodePlayer.Going_To_Explode");
		ExplodePlayer.EXPLODEPLAYER_YOU_WERE_BLOWN_UP = lang.getString("ExplodePlayer.You_Were_Blown_Up");
		Apple.APPLE_USAGE = lang.getString("Apple.Usage");
		Apple.APPLE_EATEN = lang.getString("Apple.Eaten");
		Apple.APPLE_GIVEN = lang.getString("Apple.Given");
		Diamond.DIAMOND_DURATION = config.getInt("Diamond.Duration", 10);
		Diamond.DIAMOND_GIVEN = lang.getString("Diamond.Given");
		Launch.LAUNCH_USAGE = lang.getString("Launch.Usage");
		Launch.LAUNCHED_PLAYER = lang.getString("Launch.Launched");
		Cookie.COOKIE_GIVEN = lang.getString("Cookie.Given");
		Cookie.COOKIE_ITEM_NAME = config.getString("Cookie.Item_Name");
		Cookie.COOKIE_GLOW = config.getBoolean("Cookie.Glow");
		Jumplock.JUMPLOCK_LOCK = lang.getString("Jumplock.Lock");
		Jumplock.JUMPLOCK_USAGE = lang.getString("Jumplock.Usage");
		Jumplock.JUMPLOCK_UNLOCK = lang.getString("Jumplock.Unlock");

	}
}
