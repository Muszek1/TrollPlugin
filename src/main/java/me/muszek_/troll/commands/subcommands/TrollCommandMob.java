package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TrollCommandMob extends SubCommand {


	@Override
	public String getName() {
		return "mob";
	}

	@Override
	public String getDescription() {
		return "spawns a mob behind a player";
	}

	@Override
	public String getSyntax() {
		return "/troll mob <player> <mob> <amount>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (!player.hasPermission("epictroll.mob")) {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
		}
		if (args.length == 1 || args.length == 2) {
			player.sendMessage(Colors.color(Settings.Mob.MOB_USAGE));
			return;
		}


		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null) {
			player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[1])));
			return;
		}
		EntityType mob;
		try {
			mob = EntityType.valueOf(args[2].toUpperCase());
		} catch (IllegalArgumentException e) {
			player.sendMessage(Colors.color(Settings.MOB_NOT_FOUND).replace("%mob%", args[2]));
			return;
		}

		Location playerLocation = target.getLocation();
		Location spawnLocation = playerLocation.clone().add(playerLocation.getDirection().normalize().multiply(-0.9));
		int amount = 1;
		if (args.length >= 4) {
			try {
				amount = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {
				player.sendMessage(Colors.color(Settings.WRONG_NUMBER));
				return;
			}
		}
		for (int i = 0; i < amount; i++) {
			target.getWorld().spawnEntity(spawnLocation, mob);
		}

		player.sendMessage(Colors.color((Settings.Mob.MOB_MESSAGE).replace("%player%", target.getName()).replace("%mob%", mob.getName())));


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
		if (args.length == 3) {
			List<String> mobList = new ArrayList<>();
			for (EntityType type : EntityType.values()) {

				if (type.getEntityClass() != null &&
						org.bukkit.entity.LivingEntity.class.isAssignableFrom(type.getEntityClass())) {
					mobList.add(type.name());
				}
			}

			return mobList;
		}
		return null;
	}


}


