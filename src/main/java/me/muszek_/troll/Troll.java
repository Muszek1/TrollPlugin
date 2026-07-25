package me.muszek_.troll;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.invalidusage.InvalidUsage;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.muszek_.troll.commands.subcommands.*;
import me.muszek_.troll.config.MessageConfig;
import me.muszek_.troll.config.PluginConfig;
import me.muszek_.troll.listeners.*;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.utils.Logger;
import me.muszek_.troll.utils.UpdateChecker;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class Troll extends JavaPlugin {

  private LiteCommands<CommandSender> liteCommands;
  private static Troll instance;
  private PluginConfig pluginConfig;
  private MessageConfig messageConfig;
  private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

  private String latestVersion;
  private boolean updateAvailable = false;

  @Override
  public void onEnable() {
    instance = this;
    Logger.log(Logger.LogLevel.INFO, "EpicTroll plugin has been enabled!");

    this.pluginConfig = ConfigManager.create(PluginConfig.class, (it) -> {
      it.withConfigurer(new YamlBukkitConfigurer());
      it.withBindFile(new File(this.getDataFolder(), "config.yml"));
      it.saveDefaults();
      it.load(true);
    });

    this.messageConfig = ConfigManager.create(MessageConfig.class, (it) -> {
      it.withConfigurer(new YamlBukkitConfigurer());
      it.withBindFile(new File(this.getDataFolder(), "messages.yml"));
      it.saveDefaults();
      it.load(true);
    });

    JumplockListener jumplockListener = new JumplockListener();
    BlockCraftListener blockCraftListener = new BlockCraftListener();
    ReverseChatListener reverseChatListener = new ReverseChatListener();
    BlockToolUseListener blockToolUseListener = new BlockToolUseListener();

    getServer().getPluginManager().registerEvents(jumplockListener, this);
    getServer().getPluginManager().registerEvents(blockCraftListener, this);
    getServer().getPluginManager().registerEvents(reverseChatListener, this);
    getServer().getPluginManager().registerEvents(blockToolUseListener, this);
    this.liteCommands = LiteBukkitFactory.builder("EpicTroll", this)
            .commands(new ExplodePlayer(this),
                    new AnnoySounds(this),
                    new Anvil(this),
                    new Apple(this),
                    new BlockCraft(blockCraftListener, this),
                    new BlockToolUse(blockToolUseListener, this),
                    new Cookie(this),
                    new Diamond(this),
                    new DropInv(this),
                    new FakeOp(this),
                    new FakeXp(this),
                    new Fire(this),
                    new Freeze(this),
                    new Gui(this),
                    new Help(),
                    new Jumplock(jumplockListener, this),
                    new KnockbackStick(this),
                    new Launch(this),
                    new Mob(this),
                    new Reload(this),
                    new ReverseChat(reverseChatListener, this),
                    new Shuffle(this))
            .result(InvalidUsage.class, (invocation, result, chain) -> {
              CommandSender sender = invocation.sender();
              String rawArg = invocation.arguments().asList().isEmpty()
                      ? ""
                      : invocation.arguments().asList().get(0);
              if (!rawArg.isEmpty()) {
                String message = this.messageConfig.Player_Not_Found.replace("%player%", rawArg);
                sender.sendMessage(Colors.color(message));
                return;
              }
              sender.sendMessage(Colors.color("&cUżycie: " + result.getSchematic()));
            })
            .build();

    getServer().getPluginManager().registerEvents(new AppleListener(this, messageConfig), this);
    getServer().getPluginManager().registerEvents(new DiamondListener(), this);
    getServer().getPluginManager().registerEvents(new LaunchListener(), this);
    getServer().getPluginManager().registerEvents(new CookieListener(), this);
    getServer().getPluginManager().registerEvents(new MenuListener(), this);
    getServer().getPluginManager().registerEvents(new UpdateNotifyListener(this), this);

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

  public MessageConfig getMessageConfig() {
    return this.messageConfig;
  }

  public PluginConfig getPluginConfig() {
    return this.pluginConfig;
  }
}