package me.muszek_.troll.menusystem.menu;

import me.muszek_.troll.Colors;
import me.muszek_.troll.menusystem.Menu;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class GuiCommandMenu extends Menu {
	private final JavaPlugin plugin;

	public GuiCommandMenu(PlayerMenuUtility playerMenuUtility, JavaPlugin plugin) {
		super(playerMenuUtility);
		this.plugin = plugin;
	}

	@Override
	public String getMenuName() {
		Player target = playerMenuUtility.getTarget();
		return "Troll GUI - " + target.getDisplayName();
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
			case WOODEN_PICKAXE:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll blocktooluse " + player.getName());
				break;
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
			case STICK:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll knockbackstick " + player.getName());
				break;
			case BELL:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll annoysounds " + player.getName());
				break;
			case CHEST:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll dropinv " + player.getName());
				break;
			case PAPER:
				e.getWhoClicked().closeInventory();
				player.performCommand("troll reversechat " + target.getName());
				break;
		}

	}

	@Override
	public void setMenuItems() {

		//Wooden_pickaxe
		ItemStack woodenpickaxe = new ItemStack(Material.WOODEN_PICKAXE);
		ItemMeta woodenpickaxeMeta = woodenpickaxe.getItemMeta();
		woodenpickaxeMeta.setDisplayName(Colors.color("&6&lBlock Tools use"));
		woodenpickaxeMeta.setLore(Arrays.asList(Colors.color("&e- &fBlock tools use for player")));
		woodenpickaxe.setItemMeta(woodenpickaxeMeta);
		inventory.setItem(10, woodenpickaxe);

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
		cookieMeta.setDisplayName(Colors.color(Settings.ConfigKey.COOKIE_ITEM_NAME.get()));
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

		//Reversechat
		ItemStack Reversechat = new ItemStack(Material.PAPER);
		ItemMeta ReversechatMeta = Reversechat.getItemMeta();
		ReversechatMeta.setDisplayName(Colors.color("&6&lReversechat"));
		ReversechatMeta.setLore(Arrays.asList(Colors.color("&e- &fReverse players' massages for the player")));
		Reversechat.setItemMeta(ReversechatMeta);
		inventory.setItem(22, Reversechat);

		//Annoysounds
		ItemStack Annoysounds = new ItemStack(Material.BELL);
		ItemMeta AnnoysoundsMeta = Annoysounds.getItemMeta();
		AnnoysoundsMeta.setDisplayName(Colors.color("&6&lAnnoysounds"));
		AnnoysoundsMeta.setLore(Arrays.asList(Colors.color("&e- &fSend annoysounds to the player")));
		Annoysounds.setItemMeta(AnnoysoundsMeta);
		inventory.setItem(31, Annoysounds);

		//Dropinv
		ItemStack Dropinv = new ItemStack(Material.CHEST);
		ItemMeta DropinvMeta = Dropinv.getItemMeta();
		DropinvMeta.setDisplayName(Colors.color("&6&lDropinv"));
		DropinvMeta.setLore(Arrays.asList(Colors.color("&e- &fDrop items from player's inventory")));
		Dropinv.setItemMeta(DropinvMeta);
		inventory.setItem(13, Dropinv);

		//Knockbackstick
		ItemStack Knockbackstick = new ItemStack(Material.STICK);
		ItemMeta KnockbackstickMeta = Knockbackstick.getItemMeta();
		KnockbackstickMeta.setDisplayName(Colors.color("&6&lKnockback Stick"));
		KnockbackstickMeta.setLore(Arrays.asList(Colors.color("&e- &fGive you a knockback stick")));
		KnockbackstickMeta.addEnchant(Enchantment.KNOCKBACK, 10, true);
		Knockbackstick.setItemMeta(KnockbackstickMeta);
		inventory.setItem(48, Knockbackstick);

		//Exit
		ItemStack exit = new ItemStack(Material.BARRIER);
		ItemMeta exitMeta = exit.getItemMeta();
		exitMeta.setDisplayName(Colors.color("&c&lExit"));
		exitMeta.setLore(Arrays.asList(Colors.color("&e- &fExit the menu")));
		exit.setItemMeta(exitMeta);
		inventory.setItem(49, exit);

		//Panes
		Player player = playerMenuUtility.getOwner();

		new BukkitRunnable() {
			private boolean toggle = false;

			@Override
			public void run() {
				Material material = toggle ? Material.WHITE_STAINED_GLASS_PANE : Material.LIME_STAINED_GLASS_PANE;
				ItemStack glass1 = new ItemStack(material);
				ItemMeta meta = glass1.getItemMeta();
				meta.setDisplayName(Colors.color("&f"));
				glass1.setItemMeta(meta);
				int[] panes = {
						0, 9, 18, 35, 44, 53
				};
				for (int i : panes) {
					inventory.setItem(i, glass1);
				}


				Material material2 = toggle ? Material.LIME_STAINED_GLASS_PANE : Material.WHITE_STAINED_GLASS_PANE;
				ItemStack glass2 = new ItemStack(material2);
				ItemMeta meta2 = glass2.getItemMeta();
				meta2.setDisplayName(Colors.color("&f"));
				glass2.setItemMeta(meta2);
				int[] panes2 = {
						8, 17, 26, 27, 36, 45
				};
				for (int i : panes2) {
					inventory.setItem(i, glass2);
				}

				toggle = !toggle;
			}
		}.runTaskTimer(plugin, 0L, 20L);

	}
}
