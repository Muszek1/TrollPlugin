package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class TrollCommandLaunch extends SubCommand {
	@Override
	public String getName() {
		return "launch";
	}

	@Override
	public String getDescription() {
		return "launches the player into sky";
	}

	@Override
	public String getSyntax() {
		return "/troll launch <player>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (!player.hasPermission("epictroll.mob")) {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
		}
		if (args.length == 1) {
			player.sendMessage(Colors.color(Settings.Launch.LAUNCH_USAGE));
			return;
		}
		Player target = Bukkit.getPlayer(args[1]);

		if (target == null) {
			player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[1])));
			return;
		}
		target.setVelocity(new Vector(0, 2.5, 0));

		// Sound + particles
		target.getWorld().spawnParticle(Particle.EXPLOSION, target.getLocation(), 20, 0.5, 0.5, 0.5);
		target.getWorld().spawnParticle(Particle.CLOUD, target.getLocation(), 10, 0.5, 0.5, 0.5);
		target.getWorld().playSound(target.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f, 1f);
		target.setFallDistance(0f);
		player.sendMessage(Colors.color(Settings.Launch.LAUNCHED_PLAYER).replace("%player%", args[1]));

	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			List<String> playerNames = new ArrayList<>();
			Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
			Bukkit.getServer().getOnlinePlayers().toArray(players);
			for (int i = 0; i < players.length; i++) {
				playerNames.add(players[i].getName());
			}

			return playerNames;

		}

		return null;
	}
}
