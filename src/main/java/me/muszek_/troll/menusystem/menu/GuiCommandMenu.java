package me.muszek_.troll.menusystem.menu;

import me.muszek_.troll.Colors;
import me.muszek_.troll.menusystem.Menu;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiCommandMenu extends Menu {
	public GuiCommandMenu(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
	}

	@Override
	public String getMenuName() {
		return "Troll GUI";
	}

	@Override
	public int getSlots() {
		return 54;
	}

	@Override
	public void handleMenu(InventoryClickEvent e) {
		Player target = playerMenuUtility.getTarget();
		Player player = playerMenuUtility.getOwner();
		switch (e.getCurrentItem().getType()) {
			case ANVIL:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll anvil " + target.getName());
				break;
			case GOLDEN_APPLE:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll apple " + target.getName());
				break;
			case CRAFTING_TABLE:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll blockcraft " + target.getName());
				break;
			case COOKIE:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll cookie " + target.getName());
				break;
			case DIAMOND:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll diamond " + target.getName());
				break;
			case TNT:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll explode " + target.getName());
				break;
			case BEACON:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll fakeop " + target.getName());
				break;
			case FLINT_AND_STEEL:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll fire " + target.getName());
				break;
			case PACKED_ICE:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll freeze " + target.getName());
				break;
			case RABBIT_FOOT:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll jumplock " + target.getName());
				break;
			case FIREWORK_ROCKET:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll launch " + target.getName());
				break;
			case CREEPER_HEAD:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll mob " + target.getName() + " creeper");
				break;
			case BARRIER:
				e.getWhoClicked().closeInventory();
				break;
		}

	}

	@Override
	public void setMenuItems() {

		//Anvil
		ItemStack anvil = new ItemStack(Material.ANVIL);
		ItemMeta anvilMeta = anvil.getItemMeta();
		anvilMeta.setDisplayName(Colors.color("&7&lAnvil"));
		anvilMeta.setLore(Arrays.asList(Colors.color("&e- &fSpawns anvil above player")));
		anvil.setItemMeta(anvilMeta);
		inventory.setItem(11, anvil);

		//Apple
		ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta appleMeta = apple.getItemMeta();
		appleMeta.setDisplayName(Colors.color("&a&lPoisoned Apple"));
		appleMeta.setLore(Arrays.asList(Colors.color("&e- &fGives a poisoned apple")));
		apple.setItemMeta(appleMeta);
		inventory.setItem(12, apple);

		//CRAFTING_TABLE
		ItemStack craftingtable = new ItemStack(Material.CRAFTING_TABLE);
		ItemMeta craftingtableMeta = craftingtable.getItemMeta();
		craftingtableMeta.setDisplayName(Colors.color("&c&lBlock/Unblock crafting"));
		craftingtableMeta.setLore(Arrays.asList(Colors.color("&e- &fBlocks/Unblocks crafting for player")));
		craftingtable.setItemMeta(craftingtableMeta);
		inventory.setItem(14, craftingtable);

		//Cookie
		ItemStack cookie = new ItemStack(Material.COOKIE);
		ItemMeta cookieMeta = cookie.getItemMeta();
		cookieMeta.setDisplayName(Colors.color("&6&lInfinite Cookie"));
		cookieMeta.setLore(Arrays.asList(Colors.color("&e- &fGives a cookie for player")));
		cookie.setItemMeta(cookieMeta);
		inventory.setItem(15, cookie);

		//Diamond
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemMeta diamondMeta = diamond.getItemMeta();
		diamondMeta.setDisplayName(Colors.color("&b&lFake diamond"));
		diamondMeta.setLore(Arrays.asList(Colors.color("&e- &fSpawn fake diamond around player")));
		diamond.setItemMeta(diamondMeta);
		inventory.setItem(20, diamond);

		//Explode
		ItemStack explode = new ItemStack(Material.TNT);
		ItemMeta explodeMeta = explode.getItemMeta();
		explodeMeta.setDisplayName(Colors.color("&c&lExplode"));
		explodeMeta.setLore(Arrays.asList(Colors.color("&e- &fCreate explosion on player")));
		explode.setItemMeta(explodeMeta);
		inventory.setItem(21, explode);

		//FakeOp
		ItemStack fakeop = new ItemStack(Material.BEACON);
		ItemMeta fakeopMeta = fakeop.getItemMeta();
		fakeopMeta.setDisplayName(Colors.color("&6&lFakeop"));
		fakeopMeta.setLore(Arrays.asList(Colors.color("&e- &fSend player fake message")));
		fakeop.setItemMeta(fakeopMeta);
		inventory.setItem(23, fakeop);

		//Fire
		ItemStack fire = new ItemStack(Material.FLINT_AND_STEEL);
		ItemMeta fireMeta = fire.getItemMeta();
		fireMeta.setDisplayName(Colors.color("&6&lFire"));
		fireMeta.setLore(Arrays.asList(Colors.color("&e- &fFires the player")));
		fire.setItemMeta(fireMeta);
		inventory.setItem(24, fire);

		//Freeze
		ItemStack freeze = new ItemStack(Material.PACKED_ICE);
		ItemMeta freezeMeta = freeze.getItemMeta();
		freezeMeta.setDisplayName(Colors.color("&b&lFreeze"));
		freezeMeta.setLore(Arrays.asList(Colors.color("&e- &fFreezes the player")));
		freeze.setItemMeta(freezeMeta);
		inventory.setItem(29, freeze);

		//JumpLock
		ItemStack JumpLock = new ItemStack(Material.RABBIT_FOOT);
		ItemMeta JumpLockMeta = JumpLock.getItemMeta();
		JumpLockMeta.setDisplayName(Colors.color("&6&lJump Lock"));
		JumpLockMeta.setLore(Arrays.asList(Colors.color("&e- &fBlocks player's jumps")));
		JumpLock.setItemMeta(JumpLockMeta);
		inventory.setItem(30, JumpLock);

		//Launch
		ItemStack launch = new ItemStack(Material.FIREWORK_ROCKET);
		ItemMeta launchMeta = launch.getItemMeta();
		launchMeta.setDisplayName(Colors.color("&6&lLaunch"));
		launchMeta.setLore(Arrays.asList(Colors.color("&e- &fLaunches the player")));
		launch.setItemMeta(launchMeta);
		inventory.setItem(32, launch);

		//Mob
		ItemStack mob = new ItemStack(Material.CREEPER_HEAD);
		ItemMeta mobMeta = mob.getItemMeta();
		mobMeta.setDisplayName(Colors.color("&6&lCreeper"));
		mobMeta.setLore(Arrays.asList(Colors.color("&e- &fSpawns creeper behind the player"), Colors.color("&e- &fUse /troll mob <player> <mob> for more mobs")));
		mob.setItemMeta(mobMeta);
		inventory.setItem(33, mob);

		//Exit
		ItemStack exit = new ItemStack(Material.BARRIER);
		ItemMeta exitMeta = exit.getItemMeta();
		exitMeta.setDisplayName(Colors.color("&c&lExit"));
		exitMeta.setLore(Arrays.asList(Colors.color("&e- &fExit the menu")));
		exit.setItemMeta(exitMeta);
		inventory.setItem(49, exit);


	}
}
