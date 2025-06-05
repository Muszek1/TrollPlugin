package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TrollCommandAnnoySounds extends SubCommand {

	private final Random random = new Random();
	private final List<Sound> annoyingSounds = Arrays.asList(
			Sound.ENTITY_VILLAGER_NO,
			Sound.ENTITY_ENDERMAN_SCREAM,
			Sound.ENTITY_ITEM_BREAK,
			Sound.ENTITY_CREEPER_PRIMED,
			Sound.ENTITY_ELDER_GUARDIAN_CURSE,
			Sound.BLOCK_NOTE_BLOCK_BASS,
			Sound.BLOCK_BELL_USE
	);

	@Override
	public String getName() {
		return "annoysounds";
	}

	@Override
	public String getDescription() {
		return "plays annoying sounds for the player";
	}

	@Override
	public String getSyntax() {
		return "/troll annoysounds <player>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (!player.hasPermission("epictroll.blockcraft")) {
			player.sendMessage(Colors.color(Settings.LangKey.NO_PERMISSION.get()));
			return;
		}
		if (args.length == 1) {
			player.sendMessage(Colors.color(Settings.LangKey.ANNOYSOUNDS_USAGE.get()));
			return;
		}

		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null) {
			player.sendMessage(Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", args[0])));
			return;
		}
		player.sendMessage(Colors.color(Settings.LangKey.ANNOYSOUNDS_SENT.get()).replace("%player%", args[1]));

		new BukkitRunnable() {
			int count = 0;

			@Override
			public void run() {
				if (count >= 20 || !target.isOnline()) {
					cancel();
					return;
				}
				Sound sound = annoyingSounds.get(random.nextInt(annoyingSounds.size()));
				target.playSound(target.getLocation(), sound, 1.0f, 1.0f);
				count++;
			}
		}.runTaskTimer(getPlugin(), 0L, 10L);
	}

	private org.bukkit.plugin.Plugin getPlugin() {
		return org.bukkit.Bukkit.getPluginManager().getPlugin("EpicTroll");
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}

		return null;
	}
}