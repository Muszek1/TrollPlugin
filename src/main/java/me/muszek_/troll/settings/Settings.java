package me.muszek_.troll.settings;

import me.muszek_.troll.Troll;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public final class Settings {
	public enum LangKey {
		PLAYER_NOT_FOUND("Player_Not_Found"),
		MOB_NOT_FOUND("Mob_Not_Found"),
		NO_PERMISSION("No_Permissions"),
		PLUGIN_RELOADED("Plugin_Reloaded"),
		WRONG_NUMBER("Wrong_Number"),

		FIRE_USAGE("Fire.Usage"),
		FIRE_MESSAGE("Fire.Message"),
		FIRE_INVALID_DURATION("Fire.Invalid_Duration"),

		MOB_USAGE("Mob.Usage"),
		MOB_MESSAGE("Mob.Message"),

		ANVIL_USAGE("Anvil.Usage"),
		ANVIL_MESSAGE("Anvil.Message"),
		ANVIL_ERROR("Anvil.Error"),

		FREEZE_USAGE("Freeze.Usage"),
		FREEZE_MESSAGE("Freeze.Message"),
		FREEZE_TARGET_MESSAGE("Freeze.Target_Message"),
		FREEZE_INVALID_DURATION("Freeze.Invalid_Duration"),

		EXPLODEPLAYER_MESSAGE("ExplodePlayer.Message"),
		EXPLODEPLAYER_USAGE("ExplodePlayer.Usage"),
		EXPLODEPLAYER_GOING_TO_EXPLODE("ExplodePlayer.Going_To_Explode"),
		EXPLODEPLAYER_YOU_WERE_BLOWN_UP("ExplodePlayer.You_Were_Blown_Up"),

		APPLE_USAGE("Apple.Usage"),
		APPLE_EATEN("Apple.Eaten"),
		APPLE_GIVEN("Apple.Given"),

		DIAMOND_GIVEN("Diamond.Given"),

		LAUNCH_USAGE("Launch.Usage"),
		LAUNCHED_PLAYER("Launch.Launched"),

		COOKIE_GIVEN("Cookie.Given"),

		JUMPLOCK_USAGE("Jumplock.Usage"),
		JUMPLOCK_LOCK("Jumplock.Lock"),
		JUMPLOCK_UNLOCK("Jumplock.Unlock"),

		FAKEOP_USAGE("Fakeop.Usage"),
		FAKEOP_MESSAGE_SENT("Fakeop.Message_Sent"),
		FAKEOP_MESSAGE_CONFIRMATION("Fakeop.Message_Confirmation"),

		KNOCKBACK_USAGE("Knockback.Usage"),
		KNOCKBACK_GIVEN("Knockback.Given"),
		BLOCKCRAFT_USAGE("Blockcraft.Usage"),
		BLOCKCRAFT_BLOCK("Blockcraft.Block"),
		BLOCKCRAFT_UNBLOCK("Blockcraft.Unblock"),

		GUI_USAGE("Gui.Usage"),

		REVERSEDCHAT_USAGE("Reversechat.Usage"),
		REVERSEDCHAT_REVERSE("Reversechat.Reverse"),
		REVERSEDCHAT_UNREVERSED("Reversechat.Unreversed"),

		ANNOYSOUNDS_USAGE("Annoysounds.Usage"),
		ANNOYSOUNDS_SENT("Annoysounds.Sent"),

		DROPINV_USAGE("Dropinv.Usage"),
		DROPINV_DROPPED("Dropinv.Dropped"),


		BLOCKTOOLUSE_USAGE("Blocktooluse.Usage"),
		BLOCKTOOLUSE_BLOCK("Blocktooluse.Block"),
		BLOCKTOOLUSE_UNLOCK("Blocktooluse.Unlock"),

		FAKEXP_USAGE("Fakexp.Usage"),
		FAKEXP_GIVEN("Fakexp.Given"),
		;

		private final String path;
		private String value;

		LangKey(String path) {
			this.path = path;
		}

		public String get() {
			return value;
		}

		public static void load(YamlConfiguration lang) {
			for (LangKey key : values()) {
				key.value = lang.getString(key.path, "§cMissing lang: " + key.path);
			}
		}
	}

	public enum ConfigKey {
		FIRE_DEFAULT_DURATION("Fire.Default_Duration", 3),
		FREEZE_DEFAULT_DURATION("Freeze.Default_Duration", 3),
		DIAMOND_DURATION("Diamond.Duration", 10),
		COOKIE_ITEM_NAME("Cookie.Item_Name", "Cookie of Doom"),
		COOKIE_GLOW("Cookie.Glow", false),
		KNOCKBACK_ITEM_NAME("Knockback.Item_Name", "Knockback Stick");

		private final String path;
		private final Object defaultValue;
		private Object value;

		ConfigKey(String path, Object defaultValue) {
			this.path = path;
			this.defaultValue = defaultValue;
		}

		public <T> T get() {
			return (T) value;
		}

		public static void load(YamlConfiguration config) {
			for (ConfigKey key : values()) {
				if (key.defaultValue instanceof Integer) {
					key.value = config.getInt(key.path, (Integer) key.defaultValue);
				} else if (key.defaultValue instanceof Boolean) {
					key.value = config.getBoolean(key.path, (Boolean) key.defaultValue);
				} else {
					key.value = config.getString(key.path, String.valueOf(key.defaultValue));
				}
			}
		}
	}

	public static void load() {
		Troll plugin = Troll.getInstance();
		File configFile = new File(plugin.getDataFolder(), "config.yml");
		File langFile = new File(plugin.getDataFolder(), "lang.yml");

		if (!configFile.exists()) plugin.saveResource("config.yml", false);
		if (!langFile.exists()) plugin.saveResource("lang.yml", false);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		YamlConfiguration lang = YamlConfiguration.loadConfiguration(langFile);

		LangKey.load(lang);
		ConfigKey.load(config);
	}
}
