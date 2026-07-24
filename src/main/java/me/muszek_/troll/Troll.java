package me.muszek_.troll;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.invalidusage.InvalidUsage;
import me.muszek_.troll.commands.subcommands.*;
import me.muszek_.troll.listeners.*;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.Logger;
import me.muszek_.troll.utils.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Troll extends JavaPlugin {

  private LiteCommands<CommandSender> liteCommands;
  private static Troll instance;
  private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

  private String latestVersion;
  private boolean updateAvailable = false;

  @Override
  public void onEnable() {
    instance = this;
    Logger.log(Logger.LogLevel.INFO, "EpicTroll plugin has been enabled!");

    YamlUpdater updater = new YamlUpdater(this);
    FileConfiguration config = updater.update("config.yml");
    FileConfiguration lang = updater.update("lang.yml");

    JumplockListener jumplockListener = new JumplockListener();
    BlockCraftListener blockCraftListener = new BlockCraftListener();
    ReverseChatListener reverseChatListener = new ReverseChatListener();
    BlockToolUseListener blockToolUseListener = new BlockToolUseListener();

    getServer().getPluginManager().registerEvents(jumplockListener, this);
    getServer().getPluginManager().registerEvents(blockCraftListener, this);
    getServer().getPluginManager().registerEvents(reverseChatListener, this);
    getServer().getPluginManager().registerEvents(blockToolUseListener, this);
    this.liteCommands = LiteBukkitFactory.builder("EpicTroll", this)
            .commands(new ExplodePlayer(),
                    new AnnoySounds(this),
                    new Anvil(),
                    new Apple(),
                    new BlockCraft(blockCraftListener),
                    new BlockToolUse(blockToolUseListener),
                    new Cookie(),
                    new Diamond(),
                    new DropInv(),
                    new FakeOp(),
                    new FakeXp(this),
                    new Fire(),
                    new Freeze(),
                    new Gui(this),
                    new Help(),
                    new Jumplock(jumplockListener),
                    new KnockbackStick(),
                    new Launch(),
                    new Mob(),
                    new Reload(),
                    new ReverseChat(reverseChatListener),
                    new Shuffle())
            .result(InvalidUsage.class, (invocation, result, chain) -> {
              CommandSender sender = invocation.sender();

              String rawArg = invocation.arguments().asList().isEmpty()
                      ? ""
                      : invocation.arguments().asList().get(0);

              if (!rawArg.isEmpty()) {
                String message = Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", rawArg);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                return;
              }

              sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUżycie: " + result.getSchematic()));
            })
            .build();

    getServer().getPluginManager().registerEvents(new AppleListener(), this);
    getServer().getPluginManager().registerEvents(new DiamondListener(), this);
    getServer().getPluginManager().registerEvents(new LaunchListener(), this);
    getServer().getPluginManager().registerEvents(new CookieListener(), this);
    getServer().getPluginManager().registerEvents(new MenuListener(), this);
    getServer().getPluginManager().registerEvents(new UpdateNotifyListener(this), this);

    Settings.load();

    int pluginId = 25451;
    Metrics metrics = new Metrics(this, pluginId);

    new UpdateChecker(this, 124041).getLatestVersion(version -> {
      String current = this.getDescription().getVersion();
      this.latestVersion = version;
      this.updateAvailable = !current.equalsIgnoreCase(version);

      if (!updateAvailable) {
        Logger.log(Logger.LogLevel.INFO, "Plugin EpicTroll is up to date.");
      } else {
        Logger.log(Logger.LogLevel.WARNING,
                "Plugin EpicTroll has an update. Update: https://www.spigotmc.org/resources/124041/");
      }
    });
  }

  public boolean isUpdateAvailable() {
    return updateAvailable;
  }

  public String getLatestVersion() {
    return latestVersion;
  }

  @Override
  public void onDisable() {
    if (this.liteCommands != null) {
      this.liteCommands.unregister();
    }
    getLogger().warning("EpicTroll plugin has been disabled!");
  }

  public static Troll getInstance() {
    return instance;
  }

  public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
    if (playerMenuUtilityMap.containsKey(player)) {
      return playerMenuUtilityMap.get(player);
    } else {
      PlayerMenuUtility playerMenuUtility = new PlayerMenuUtility(player);
      playerMenuUtilityMap.put(player, playerMenuUtility);
      return playerMenuUtility;
    }
  }
}