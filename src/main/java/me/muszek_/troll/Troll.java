package me.muszek_.troll;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.permission.MissingPermissions;
import dev.rollczi.litecommands.scope.Scope;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.muszek_.troll.commands.subcommands.*;
import me.muszek_.troll.config.MessageConfig;
import me.muszek_.troll.config.PluginConfig;
import me.muszek_.troll.handlers.CustomInvalidUsageHandler;
import me.muszek_.troll.handlers.MissingPermissionHandler;
import me.muszek_.troll.listeners.*;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.resolve.EntityTypeArgumentResolver;
import me.muszek_.troll.resolve.PlayerArgumentResolver;
import me.muszek_.troll.utils.Logger;
import me.muszek_.troll.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public final class Troll extends JavaPlugin implements Listener {

  private LiteCommands<CommandSender> liteCommands;
  private static Troll instance;
  private PluginConfig pluginConfig;
  private MessageConfig messageConfig;
  private JumplockListener jumplockListener;
  private static final HashMap<UUID, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
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

    this.jumplockListener = new JumplockListener();
    BlockCraftListener blockCraftListener = new BlockCraftListener();
    ReverseChatListener reverseChatListener = new ReverseChatListener();
    BlockToolUseListener blockToolUseListener = new BlockToolUseListener();

    getServer().getPluginManager().registerEvents(this, this);
    getServer().getPluginManager().registerEvents(jumplockListener, this);
    getServer().getPluginManager().registerEvents(blockCraftListener, this);
    getServer().getPluginManager().registerEvents(reverseChatListener, this);
    getServer().getPluginManager().registerEvents(blockToolUseListener, this);
    this.liteCommands = LiteBukkitFactory.builder("EpicTroll", this)
            .editor(Scope.command("troll"), editor ->
                    editor.aliases("trolling", "etroll", "epictroll")
            )
            .argument(EntityType.class, new EntityTypeArgumentResolver(this.messageConfig))
            .argument(Player.class, new PlayerArgumentResolver(this.messageConfig))
            .commands
                   (new Gui(this),
                    new MainCommand(this),
                    new ExplodePlayer(this),
                    new AnnoySounds(this),
                    new Anvil(this),
                    new Apple(this),
                    new BlockCraft(blockCraftListener, this),
                    new BlockToolUse(blockToolUseListener, this),
                    new Cookie(this),
                    new Diamond(this),
                    new DropInv(this),
                    new FakeOp(this),
                    new com.muszek_.troll.commands.subcommands.FakeXp(this),
                    new Fire(this),
                    new Freeze(this),
                    new Help(),
                    new Jumplock(jumplockListener, this),
                    new KnockbackStick(this),
                    new Launch(this),
                    new Mob(this),
                    new Pumpkin(this),
                    new Reload(this),
                    new ReverseChat(reverseChatListener, this),
                    new Shuffle(this),
                    new Cobweb(this))
            .result(MissingPermissions.class, new MissingPermissionHandler(this.messageConfig))
            .invalidUsage(new CustomInvalidUsageHandler(this.messageConfig))
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

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    playerMenuUtilityMap.remove(player.getUniqueId());
  }

  @Override
  public void onDisable() {
    if (this.liteCommands != null) {
      this.liteCommands.unregister();
    }

    if (this.jumplockListener != null) {
      for (UUID uuid : this.jumplockListener.getJumpLocked()) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null && player.isOnline()) {
          player.removePotionEffect(PotionEffectType.JUMP);
        }
      }
    }

    getLogger().warning("EpicTroll plugin has been disabled!");
  }

  public static Troll getInstance() {
    return instance;
  }

  public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
    if (playerMenuUtilityMap.containsKey(player.getUniqueId())) {
      return playerMenuUtilityMap.get(player.getUniqueId());
    } else {
      PlayerMenuUtility playerMenuUtility = new PlayerMenuUtility(player);
      playerMenuUtilityMap.put(player.getUniqueId(), playerMenuUtility);
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