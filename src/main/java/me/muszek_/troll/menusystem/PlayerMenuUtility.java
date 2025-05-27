package me.muszek_.troll.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

	private Player owner;
	private Player target;

	public Player getTarget() {
		return target;
	}

	public void setTarget(Player target) {
		this.target = target;
	}

	public PlayerMenuUtility(Player owner) {
		this.owner = owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Player getOwner() {
		return owner;
	}
}
